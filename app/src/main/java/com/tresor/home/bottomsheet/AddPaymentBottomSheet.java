package com.tresor.home.bottomsheet;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tresor.R;
import com.tresor.home.model.IconModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kris on 7/4/17. Tokopedia
 */

public class AddPaymentBottomSheet extends BottomSheetDialog{

    private LinearLayout amountLayout;
    private LinearLayout iconLayout;
    private LinearLayout infoLayout;
    private LinearLayout stepByStepLayout;
    private RecyclerView iconList;
    private RecyclerView.Adapter iconListAdapter;
    private TextView textCurrency;
    private EditText fieldAmount;
    private EditText fieldInfo;
    private Button finishButton;
    private Button nextButton;
    private Button skipButton;
    private View showAllChevron;

    public AddPaymentBottomSheet(@NonNull Context context) {
        super(context);
        initView(context);
    }

    private void initView(Context context) {
        View view = ((Activity)context).getLayoutInflater()
                .inflate(R.layout.add_new_data_bottom_sheet, null);
        setContentView(view);

        amountLayout = (LinearLayout) view.findViewById(R.id.amount_layout);
        infoLayout = (LinearLayout) view.findViewById(R.id.edit_info_layout);
        iconLayout = (LinearLayout) view.findViewById(R.id.icon_layout);
        stepByStepLayout = (LinearLayout) view.findViewById(R.id.step_by_step_layout);
        textCurrency = (TextView) view.findViewById(R.id.currency);
        fieldAmount = (EditText) view.findViewById(R.id.edit_text_insert_amount);
        fieldInfo = (EditText) view.findViewById(R.id.edit_text_insert_info);
        finishButton = (Button) view.findViewById(R.id.finish_button);
        nextButton = (Button) view.findViewById(R.id.next_button);
        skipButton = (Button) view.findViewById(R.id.skip_button);
        iconList = (RecyclerView) view.findViewById(R.id.icon_list);
        iconListAdapter = new IconAdapter(generatedIconList());
        iconList.setLayoutManager(new GridLayoutManager(context, 4));
        iconList.setAdapter(iconListAdapter);
        nextButton.setOnClickListener(onNextButtonListener());
        skipButton.setOnClickListener(onSkipButtonClickedListener());
    }

    private View.OnClickListener onNextButtonListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!infoLayout.isShown()) infoLayout.setVisibility(View.VISIBLE);
                else {
                    iconList.setVisibility(View.VISIBLE);
                    finishButton.setVisibility(View.VISIBLE);
                    stepByStepLayout.setVisibility(View.GONE);
                }
            }
        };
    }

    private View.OnClickListener onSkipButtonClickedListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                infoLayout.setVisibility(View.VISIBLE);
                iconList.setVisibility(View.VISIBLE);
                stepByStepLayout.setVisibility(View.GONE);
                finishButton.setVisibility(View.VISIBLE);
            }
        };
    }

    private List<IconModel> generatedIconList() {
        List<IconModel> iconModelList = new ArrayList<>();
        IconModel iconModel = new IconModel();
        iconModel.setIconImageId((R.mipmap.ic_cat_everything_else_big));
        iconModelList.add(iconModel);
        iconModel = new IconModel();
        iconModel.setIconImageId((R.mipmap.ic_cat_automotive_big));
        iconModelList.add(iconModel);
        iconModel = new IconModel();
        iconModel.setIconImageId((R.mipmap.ic_cat_clothing_big));
        iconModelList.add(iconModel);
        iconModel = new IconModel();
        iconModel.setIconImageId((R.mipmap.ic_cat_health_big));
        iconModelList.add(iconModel);
        iconModel = new IconModel();
        iconModel.setIconImageId((R.mipmap.ic_cat_kitchen_dining_big));
        iconModelList.add(iconModel);
        iconModel = new IconModel();
        iconModel.setIconImageId((R.mipmap.ic_cat_clothing_big));
        iconModelList.add(iconModel);
        iconModel = new IconModel();
        iconModel.setIconImageId((R.mipmap.ic_cat_health_big));
        iconModelList.add(iconModel);
        iconModel = new IconModel();
        iconModel.setIconImageId((R.mipmap.ic_cat_kitchen_dining_big));
        iconModelList.add(iconModel);
        return iconModelList;
    }
}
