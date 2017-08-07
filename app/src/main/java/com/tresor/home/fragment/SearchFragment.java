package com.tresor.home.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.aigestudio.wheelpicker.WheelPicker;
import com.aigestudio.wheelpicker.widgets.WheelDatePicker;
import com.aigestudio.wheelpicker.widgets.WheelDayPicker;
import com.aigestudio.wheelpicker.widgets.WheelMonthPicker;
import com.aigestudio.wheelpicker.widgets.WheelYearPicker;
import com.tresor.R;
import com.tresor.home.adapter.FinancialHistoryAdapter;
import com.tresor.home.model.FinancialHistoryModel;
import com.tresor.home.model.SpendingDataModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by kris on 6/23/17. Tokopedia
 */

public class SearchFragment extends Fragment {

    private RecyclerView searchList;
    private RecyclerView.Adapter searchListAdapter;

    private WheelDayPicker wheelDayPicker;
    private WheelPicker wheelMonthPicker;
    private WheelYearPicker wheelYearPicker;
    private List<String> monthList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View mainView = inflater.inflate(R.layout.fragment_home_search, container, false);
        monthList = new ArrayList<>(Arrays.asList((getResources().getStringArray(R.array.months))));
        wheelDayPicker = (WheelDayPicker) mainView.findViewById(R.id.day_picker);
        wheelMonthPicker = (WheelPicker) mainView.findViewById(R.id.month_picker);
        wheelYearPicker = (WheelYearPicker) mainView.findViewById(R.id.year_picker);
        wheelMonthPicker.setData(monthList);

        searchList = (RecyclerView) mainView.findViewById(R.id.filtered_list);
        searchList.setLayoutManager(new LinearLayoutManager(getActivity()));
        searchListAdapter = new FinancialHistoryAdapter(getActivity(), spendingDataModel());
        searchList.setAdapter(searchListAdapter);

        return mainView;
    }

    private SpendingDataModel spendingDataModel() {
        SpendingDataModel model = new SpendingDataModel();
        model.setDailyAllocation(0);
        model.setDailyAllocationString("Rp 0");
        model.setFinancialHistoryModelList(financialHistoryModelList());
        model.setHistory(true);
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
        for(int i = 0; i<4; i++) {
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
}
