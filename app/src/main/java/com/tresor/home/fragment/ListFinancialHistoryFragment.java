package com.tresor.home.fragment;

import android.app.ActivityOptions;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tresor.R;
import com.tresor.home.activity.HomeActivity;
import com.tresor.home.adapter.FinancialHistoryAdapter;
import com.tresor.home.model.FinancialHistoryModel;
import com.tresor.profile.ProfilePageActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kris on 5/27/17. Tokopedia
 */

public class ListFinancialHistoryFragment extends Fragment {

    private RecyclerView financialHistoryList;
    private RecyclerView.Adapter financialHistoryListAdapter;

    private BottomSheetDialog bottomSheetDialog;

    private FloatingActionButton insertDataFab;

    private TextView goToHistoryButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View mainView = inflater.inflate(R.layout.fragment_list_financial_history, container, false);
        financialHistoryList = (RecyclerView) mainView.findViewById(R.id.list_financial_history);
        financialHistoryList.setLayoutManager(new LinearLayoutManager(getActivity()));
        financialHistoryListAdapter = new FinancialHistoryAdapter(getActivity(), financialHistoryModelList());
        financialHistoryList.setAdapter(financialHistoryListAdapter);

        insertDataFab = (FloatingActionButton) mainView.findViewById(R.id.history_floating_action_button);
        insertDataFab.setOnClickListener(onFabButtonClickedListener());

        bottomSheetDialog = new BottomSheetDialog(getActivity());
        View bottomSheetView = inflater.inflate(R.layout.add_new_data_bottom_sheet, null);
        bottomSheetDialog.setContentView(bottomSheetView);

        goToHistoryButton = (TextView) mainView.findViewById(R.id.go_to_history_button);
        goToHistoryButton.setOnClickListener(onGoToHistoryButton());

        return mainView;
    }

    private View.OnClickListener onFabButtonClickedListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.show();
            }
        };
    }

    private View.OnClickListener onGoToHistoryButton() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProfilePageActivity.class);
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options;
                    options = ActivityOptions.makeSceneTransitionAnimation(getActivity(), financialHistoryList, "financial list");
                    startActivity(intent, options.toBundle());
                } else startActivity(intent);
            }
        };
    }

    private List<FinancialHistoryModel> financialHistoryModelList() {
        List<FinancialHistoryModel> list = new ArrayList<>();
        for(int i = 0; i<8; i++) {
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
