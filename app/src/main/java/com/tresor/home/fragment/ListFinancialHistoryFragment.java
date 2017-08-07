package com.tresor.home.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tresor.R;
import com.tresor.home.adapter.FinancialHistoryAdapter;
import com.tresor.home.bottomsheet.AddPaymentBottomSheet;
import com.tresor.home.dialog.AddPaymentDialog;
import com.tresor.home.inteface.NewDataAddedListener;
import com.tresor.home.model.FinancialHistoryModel;
import com.tresor.home.model.SpendingDataModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kris on 5/27/17. Tokopedia
 */

public class ListFinancialHistoryFragment extends Fragment implements NewDataAddedListener {

    private RecyclerView financialHistoryList;
    private RecyclerView.Adapter financialHistoryListAdapter;
    private List<FinancialHistoryModel> financialList;
    private AddPaymentDialog addPaymentDialog;
    private BottomSheetDialog bottomSheetDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View mainView = inflater.inflate(R.layout.fragment_list_financial_history, container, false);
        financialHistoryList = (RecyclerView) mainView.findViewById(R.id.list_financial_history);
        financialHistoryList.setLayoutManager(new LinearLayoutManager(getActivity()));
        financialHistoryList.setHasFixedSize(true);
        financialList = financialHistoryModelList();
        financialHistoryListAdapter = new FinancialHistoryAdapter(getActivity(), spendingDataModel());
        financialHistoryList.setAdapter(financialHistoryListAdapter);

        return mainView;
    }

    public void onHomeButtonFabClicked() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        addPaymentDialog = new AddPaymentDialog();
        addPaymentDialog.initiateListener(this);
        addPaymentDialog.show(ft, "dialog");
        /*bottomSheetDialog = new AddPaymentBottomSheet(getActivity(), this);
        bottomSheetDialog.show();*/
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

    @Override
    public void onDataAdded(FinancialHistoryModel newData) {
        financialList.add(0, newData);
        financialHistoryListAdapter.notifyDataSetChanged();
    }
}
