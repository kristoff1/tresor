package com.tresor.home.fragment;

import android.app.Fragment;
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
import com.tresor.home.model.FinancialHistoryModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kris on 5/27/17. Tokopedia
 */

public class ListFinancialHistoryFragment extends Fragment {

    private RecyclerView financialHistoryList;
    private RecyclerView.Adapter financialHistoryListAdapter;

    private BottomSheetDialog bottomSheetDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View mainView = inflater.inflate(R.layout.fragment_list_financial_history, container, false);
        financialHistoryList = (RecyclerView) mainView.findViewById(R.id.list_financial_history);
        financialHistoryList.setLayoutManager(new LinearLayoutManager(getActivity()));
        financialHistoryList.setHasFixedSize(true);
        financialHistoryListAdapter = new FinancialHistoryAdapter(getActivity(), financialHistoryModelList());
        financialHistoryList.setAdapter(financialHistoryListAdapter);

        bottomSheetDialog = new BottomSheetDialog(getActivity());
        View bottomSheetView = inflater.inflate(R.layout.add_new_data_bottom_sheet, null);
        bottomSheetDialog.setContentView(bottomSheetView);

        return mainView;
    }

    public void onHomeButtonFabClicked() {
        bottomSheetDialog.show();
    }

    private List<FinancialHistoryModel> financialHistoryModelList() {
        List<FinancialHistoryModel> list = new ArrayList<>();
        for(int i = 0; i<8; i++) {
            FinancialHistoryModel financialHistoryModel = new FinancialHistoryModel();
            financialHistoryModel.setAmount("Rp 50.000");
            financialHistoryModel.setDate("08.32 WIB February 17th 2017");
            financialHistoryModel.setHashtag("#Makan #Siang #Liburan");
            financialHistoryModel.setInfo("Martabak Telor Mang Udin the Conqueror 3 Paket");
            if(i > 4) {
                financialHistoryModel.setTheme(i - 5);
            } else financialHistoryModel.setTheme(i);
            list.add(financialHistoryModel);
        }
        return list;
    }
}
