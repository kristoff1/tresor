package com.tresor.common.widget;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;

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
 * Created by kris on 9/22/17. Tokopedia
 */

public class DebouncingAutoCompleteTextView extends android.support.v7.widget.AppCompatAutoCompleteTextView{

    private textChangedListener textListener;

    public DebouncingAutoCompleteTextView(Context context) {
        super(context);
    }

    public DebouncingAutoCompleteTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DebouncingAutoCompleteTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void initListener(CompositeDisposable compositeDisposable,
                             DebouncingAutoCompleteListener listener) {
        modifiedCompositeDisposable(compositeDisposable, listener);
        addTextChangedListener(textWatcher());
    }

    private TextWatcher textWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(textListener != null) {
                    textListener.onQueryTextChanged(s.toString());
                }
            }
        };
    }

    private CompositeDisposable modifiedCompositeDisposable(
            CompositeDisposable compositeDisposable,
            DebouncingAutoCompleteListener listener) {
        compositeDisposable.add(Observable.create(onSubscribeDebounce())
                .debounce(500, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(debounceDisposableObserver(listener)));
        return compositeDisposable;
    }

    private ObservableOnSubscribe<String> onSubscribeDebounce() {
        return new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull final ObservableEmitter<String> subscriber) throws Exception {
                textListener = new textChangedListener() {
                    @Override
                    public void onQueryTextChanged(String query) {
                        subscriber.onNext(query);
                    }
                };
            }
        };
    }

    private DisposableObserver<String> debounceDisposableObserver(final DebouncingAutoCompleteListener
                                                                          listener) {
        return new DisposableObserver<String>() {
            @Override
            public void onNext(@NonNull String s) {
                listener.finishedTyping(s);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
    }

    private interface textChangedListener {
        void onQueryTextChanged(String query);
    }

    public interface DebouncingAutoCompleteListener {
        void finishedTyping(String query);

        void onTypingError(Throwable e);
    }

}