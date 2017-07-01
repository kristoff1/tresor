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
        searchListAdapter = new FinancialHistoryAdapter(getActivity(), financialHistoryModelList());
        searchList.setAdapter(searchListAdapter);

        return mainView;
    }

    private List<FinancialHistoryModel> financialHistoryModelList() {
        List<FinancialHistoryModel> list = new ArrayList<>();
        for(int i = 0; i<4; i++) {
            FinancialHistoryModel financialHistoryModel = new FinancialHistoryModel();
            financialHistoryModel.setAmount("Rp 50.000");
            financialHistoryModel.setDate("08.32 WIB February 17th 2017");
            financialHistoryModel.setHashtag("#Makan #Siang #Liburan");
            financialHistoryModel.setInfo("Martabak Telor Mang Udin the Conqueror 3 Paket");
            list.add(financialHistoryModel);
        }
        return list;
    }
}
