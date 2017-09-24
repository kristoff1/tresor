package com.tresor.statistic.dialog;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.tresor.R;
import com.tresor.common.utils.TresorExecutor;
import com.tresor.statistic.adapter.AnalyzeHashTagAdapter;
import com.tresor.statistic.adapter.AnalyzeHashTagAutoCompleteAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by kris on 9/10/17. Tokopedia
 */

public class AnalyzeHashTagSpendingDialog extends DialogFragment {

    private static final String HASH_TAG_LIST_KEY = "hash_tag_list_key";

    private AnalyzeHashTagAdapter analyzeHashTagAdapter;

    private CompositeDisposable compositeDisposable;

    private AnalyzeHashTagAutoCompleteAdapter arrayAdapter;

    private List<String> listOfHashTag;

    private textChangedListener listener;

    public static AnalyzeHashTagSpendingDialog createDialog(ArrayList<String> hashTagList) {
        AnalyzeHashTagSpendingDialog dialog = new AnalyzeHashTagSpendingDialog();
        Bundle bundle = new Bundle();
        bundle.putStringArrayList(HASH_TAG_LIST_KEY, hashTagList);
        dialog.setArguments(bundle);
        return dialog;
    }

    private interface textChangedListener {
        void onQueryTextChanged(String query);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.analyze_hashtag_dialog, container);
        listOfHashTag = new ArrayList<>();
        arrayAdapter = new AnalyzeHashTagAutoCompleteAdapter(getActivity());
        RecyclerView selectedHashTagRecyclerView = (RecyclerView)
                view.findViewById(R.id.selected_hash_tag_recycler_view);
        AutoCompleteTextView hashTagCompleteView = (AutoCompleteTextView)
                view.findViewById(R.id.add_new_hash_tag_auto_complete);
        hashTagCompleteView.setAdapter(arrayAdapter);
        analyzeHashTagAdapter = new AnalyzeHashTagAdapter(getArguments()
                .getStringArrayList(HASH_TAG_LIST_KEY));
        arrayAdapter.notifyDataSetChanged();
        initiateDisposable();
        selectedHashTagRecyclerView.setAdapter(analyzeHashTagAdapter);
        selectedHashTagRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        analyzeHashTagAdapter.notifyDataSetChanged();
        hashTagCompleteView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(listener != null) {
                    listener.onQueryTextChanged(s.toString());
                }
            }
        });
        return view;
    }

    private void initiateDisposable() {
        compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull final ObservableEmitter<String> subscriber) throws Exception {
                listener = new textChangedListener() {
                    @Override
                    public void onQueryTextChanged(String query) {
                        subscriber.onNext(query);
                    }
                };
            }
        }).debounce(500, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableObserver<String>() {
                    @Override
                    public void onNext(@NonNull String s) {
                        listOfHashTag.clear();
                        listOfHashTag.add("#makan " + s);
                        listOfHashTag.add("#gemuk " + s);
                        listOfHashTag.add("#kawai " + s);
                        arrayAdapter.updateData(listOfHashTag);
                        arrayAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.dispose();
    }
}
