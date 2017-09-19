package com.tresor.home.fragment;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tresor.R;
import com.tresor.common.HashTagSuggestionAdapter;
import com.tresor.common.model.HashTagFilterModel;
import com.tresor.home.adapter.FinancialHistoryAdapter;
import com.tresor.home.dialog.AddPaymentDialog;
import com.tresor.home.dialog.EditPaymentDialog;
import com.tresor.home.inteface.NewDataAddedListener;
import com.tresor.home.model.FinancialHistoryModel;
import com.tresor.home.model.SpendingDataModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kris on 5/27/17. Tokopedia
 */

public class ListFinancialHistoryFragment extends Fragment
        implements HashTagSuggestionAdapter.onSuggestedHashTagClickedListener,
        FinancialHistoryAdapter.ListItemListener {

    private RecyclerView financialHistoryList;
    private FinancialHistoryAdapter financialHistoryListAdapter;
    private RecyclerView suggestedHashTagRecyclerView;
    private RecyclerView.Adapter suggestedHashTagAdapter;
    private List<FinancialHistoryModel> financialList;
    private BottomSheetDialog bottomSheetDialog;

    public static ListFinancialHistoryFragment createFragment() {
        return new ListFinancialHistoryFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View mainView = inflater.inflate(R.layout.fragment_list_financial_history, container, false);
        financialHistoryList = (RecyclerView) mainView.findViewById(R.id.list_financial_history);
        financialHistoryList.setLayoutManager(new LinearLayoutManager(getActivity()));
        financialHistoryList.setHasFixedSize(true);
        financialList = financialHistoryModelList();
        financialHistoryListAdapter = new FinancialHistoryAdapter(getActivity(),
                spendingDataModel(),
                this);
        financialHistoryList.setAdapter(financialHistoryListAdapter);
        financialHistoryList.setNestedScrollingEnabled(false);

        suggestedHashTagRecyclerView = (RecyclerView) mainView
                .findViewById(R.id.suggested_hash_tag_recycler_view);
        suggestedHashTagRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.HORIZONTAL));
        suggestedHashTagAdapter = new HashTagSuggestionAdapter(dummyHashtagListModel(), this);
        suggestedHashTagRecyclerView.setAdapter(suggestedHashTagAdapter);
        suggestedHashTagRecyclerView.setNestedScrollingEnabled(false);
        FloatingActionButton historicalFloatingActionButton = (FloatingActionButton)
                mainView.findViewById(R.id.history_floating_action_button);
        historicalFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onHomeButtonFabClicked();
            }
        });
        return mainView;
    }

    public void onHomeButtonFabClicked() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        AddPaymentDialog addPaymentDialog = new AddPaymentDialog();
        addPaymentDialog.show(ft, "dialog");
        /*bottomSheetDialog = new AddPaymentBottomSheet(getActivity(), this);
        bottomSheetDialog.show();*/
    }

    private List<FinancialHistoryModel> selectedFilter(String selectedHash) {
        List<FinancialHistoryModel> filteredList = new ArrayList<>();
        for (int i = 0; i<financialList.size(); i++) {
            if(financialList.get(i).getHashTagString().contains(selectedHash)) {
                filteredList.add(financialList.get(i));
            }
        }
        return filteredList;
    }

    private SpendingDataModel spendingDataModel() {
        SpendingDataModel model = new SpendingDataModel();
        model.setDailyAllocation(0);
        model.setDailyAllocationString("Rp 0");
        model.setFinancialHistoryModelList(financialList);
        model.setHistory(false);
        model.setTodayAllocation(0);
        model.setTodayAllocationString("Rp 0");
        model.setTodaySaving(0);
        model.setTodaySavingString("Rp 0");
        model.setTotalSpending(250000);
        model.setTotalSpendingString("Rp 250.000");
        return model;
    }

    private List<FinancialHistoryModel> financialHistoryModelList() {
        List<FinancialHistoryModel> list = new ArrayList<>();
        for(int i = 0; i<8; i++) {
            FinancialHistoryModel financialHistoryModel = new FinancialHistoryModel();
            financialHistoryModel.setAmountUnformatted(50000);
            financialHistoryModel.setAmount("Rp 50.000");
            financialHistoryModel.setDate("08.32 WIB February 17th 2017");
            List<String> hashTagList = new ArrayList<>();
            hashTagList.add("#Makan");
            hashTagList.add("#Siang");
            hashTagList.add("#Liburan");
            financialHistoryModel.setHashtag(hashTagList);
            financialHistoryModel
                    .setInfo("#Liburan #Makan Martabak Telor Mang Udin the Conqueror #Siang siang 3 Paket");
            if(i > 4) {
                financialHistoryModel.setTheme(i - 5);
            } else financialHistoryModel.setTheme(i);
            list.add(financialHistoryModel);
        }
        return list;
    }

    private List<HashTagFilterModel> dummyHashtagListModel() {
        List<HashTagFilterModel> hashTagFilterModels = new ArrayList<>();
        for(int i = 0; i < dummySuggestedHashTagList().size(); i++) {
            HashTagFilterModel model = new HashTagFilterModel();
            model.setHashTagString(dummySuggestedHashTagList().get(i));
            model.setSelected(false);
            hashTagFilterModels.add(model);
        }
        return hashTagFilterModels;
    }

    private List<String> dummySuggestedHashTagList() {
        List<String> dummyHashTag = new ArrayList<>();
        dummyHashTag.add("#Makan");
        dummyHashTag.add("#Siang");
        dummyHashTag.add("#Alalalalala");
        dummyHashTag.add("#Ajebajeb");
        dummyHashTag.add("#ClubbingNyeeeet");
        dummyHashTag.add("#LalalaFest");
        return dummyHashTag;
    }

    public void onDataAdded(FinancialHistoryModel newData) {
        financialList.add(0, newData);
        financialHistoryListAdapter
                .notifyItemInserted(FinancialHistoryAdapter.NUMBER_OF_HEADER_ADAPTER);
        financialHistoryListAdapter
                .notifyItemRangeInserted(
                        FinancialHistoryAdapter.NUMBER_OF_HEADER_ADAPTER,
                        financialList.size() + FinancialHistoryAdapter.NUMBER_OF_HEADER_ADAPTER
                );
        financialHistoryList.scrollToPosition(FinancialHistoryAdapter.NUMBER_OF_HEADER_ADAPTER);

        //TODO RELEASE IF ANIMATION CAUSES MUCH BUGS
        //financialHistoryListAdapter.notifyDataSetChanged();
    }

    @Override
    public void hashTagSelected(String selectedHashtag) {
        financialHistoryListAdapter.updateData(selectedFilter(selectedHashtag));
        financialHistoryListAdapter.notifyDataSetChanged();
        suggestedHashTagAdapter.notifyDataSetChanged();
    }

    @Override
    public void clearFilter() {
        financialHistoryListAdapter.updateData(financialList);
        financialHistoryListAdapter.notifyDataSetChanged();
        suggestedHashTagAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(FinancialHistoryModel itemModel) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag("edit_dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        EditPaymentDialog editPaymentDialog = EditPaymentDialog
                .createEditPaymentDialog(itemModel);
        editPaymentDialog.show(ft, "edit_dialog");
    }

    public void onItemEdited() {
        financialHistoryListAdapter.notifyDataSetChanged();
    }
}
