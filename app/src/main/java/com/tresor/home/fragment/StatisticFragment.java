package com.tresor.home.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.tresor.R;
import com.tresor.home.adapter.StatisticFlipperAdapter;
import com.tresor.home.model.HashTagStatisticModel;

import java.util.ArrayList;
import java.util.List;

import se.emilsjolander.flipview.FlipView;

/**
 * Created by kris on 6/1/17. Tokopedia
 */

public class StatisticFragment extends Fragment {

    private FlipView statisticFlipper;

    private Spinner graphSelector;

    private ArrayAdapter spinnerAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_statistic, container, false);
        statisticFlipper = (FlipView) view.findViewById(R.id.statistic_flipper);
        graphSelector = (Spinner) view.findViewById(R.id.graph_selector_spinner);
        spinnerAdapter = new ArrayAdapter(getActivity(),
                android.R.layout.simple_spinner_dropdown_item,
                spinnerChoices());
        graphSelector.setAdapter(spinnerAdapter);
        graphSelector.setOnItemSelectedListener(onSpinnerItemSelectedListener());
        statisticFlipper.setAdapter(new StatisticFlipperAdapter(getActivity(), hashTagStatisticModelList()));
        return view;
    }

    private List<HashTagStatisticModel> hashTagStatisticModelList() {
        List<HashTagStatisticModel> list = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            HashTagStatisticModel hashTagStatisticModel = new HashTagStatisticModel();
            switch (i) {
                case 0:
                    hashTagStatisticModel.setHashTag("#makan");
                    hashTagStatisticModel.setIdHashTag("0");
                    break;
                case 1:
                    hashTagStatisticModel.setHashTag("#malem");
                    hashTagStatisticModel.setIdHashTag("1");
                    break;
                case 2:
                    hashTagStatisticModel.setHashTag("#KFC");
                    hashTagStatisticModel.setIdHashTag("2");
                    break;
                case 3:
                    hashTagStatisticModel.setHashTag("#bolang");
                    hashTagStatisticModel.setIdHashTag("3");
                    break;
                default:
                    hashTagStatisticModel.setHashTag("Others");
                    break;
            }
            hashTagStatisticModel.setInformationList(dummyInformation(i));
            hashTagStatisticModel.setMonthlyAmountList(dummyAmount(i));
            hashTagStatisticModel.setTotalPeriodicAmount((double)((i+1) * 1000));
            list.add(hashTagStatisticModel);
        }
        return list;
    }

    private List<String> dummyInformation(int numberOfDummies) {
        List<String> dummy = new ArrayList<>();
        for (int i = 0; i < numberOfDummies + 2; i++) {
            dummy.add("Dummy Info");
        }
        return dummy;
    }

    private List<Double> dummyAmount(int dummyModifier) {
        List<Double> dummy = new ArrayList<>();
        for (int i = 0; i< dummyModifier + 2; i++) {
            dummy.add((double) 10000 * dummyModifier);
        }
        return dummy;
    }

    private AdapterView.OnItemSelectedListener onSpinnerItemSelectedListener() {
        return new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    statisticFlipper.flipTo(0);
                } else if (position == 1) {
                    statisticFlipper.flipTo(1);
                } else if (position == 2) {
                    statisticFlipper.flipTo(2);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
    }

    private ArrayList<String> spinnerChoices() {
        ArrayList<String> choiceList = new ArrayList<>();
        choiceList.add("Expense B yHashtag");
        choiceList.add("HashTag Comparison");
        choiceList.add("Spending");
        return choiceList;
    }

}
