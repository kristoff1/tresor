package com.tresor.home.dialog;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.tresor.R;
import com.tresor.home.bottomsheet.IconAdapter;
import com.tresor.home.inteface.IconSelectetionListener;
import com.tresor.home.model.FinancialHistoryModel;
import com.tresor.home.model.IconModel;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import faranjit.currency.edittext.CurrencyEditText;

/**
 * Created by kris on 8/20/17. Tokopedia
 */

public class EditPaymentDialog extends DialogFragment implements IconSelectetionListener{

    private RecyclerView.Adapter iconListAdapter;
    private List<IconModel> generatedIcons;

    public static EditPaymentDialog createEditPaymentDialog(FinancialHistoryModel model) {
        EditPaymentDialog dialog = new EditPaymentDialog();
        Bundle bundle = new Bundle();
        bundle.putParcelable();
        dialog.setArguments();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater
                .inflate(R.layout.edit_data_dialog, container);
        CurrencyEditText fieldAmount = (CurrencyEditText) view
                .findViewById(R.id.edit_text_insert_amount);
        EditText fieldInfo = (EditText) view.findViewById(R.id.edit_text_insert_info);
        TextView cancelButton = (TextView) view.findViewById(R.id.cancel_button);
        TextView finishButton = (TextView) view.findViewById(R.id.finish_button);
        RecyclerView iconList = (RecyclerView) view.findViewById(R.id.icon_list);
        generatedIcons = generatedIconList();
        iconListAdapter = new IconAdapter(generatedIcons, this);
        iconList.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        iconList.setAdapter(iconListAdapter);
        return view;
    }

    @Override
    public void onIconClicked(int position, int iconId) {

    }

    private List<IconModel> generatedIconList() {
        List<IconModel> iconModelList = new ArrayList<>();
        IconModel iconModel = new IconModel();
        iconModel.setIconImageId(0);
        iconModelList.add(iconModel);
        iconModel = new IconModel();
        iconModel.setIconImageId(1);
        iconModelList.add(iconModel);
        iconModel = new IconModel();
        iconModel.setIconImageId(2);
        iconModelList.add(iconModel);
        iconModel = new IconModel();
        iconModel.setIconImageId(3);
        iconModelList.add(iconModel);
        iconModel = new IconModel();
        iconModel.setIconImageId(4);
        iconModelList.add(iconModel);
        iconModel = new IconModel();
        iconModel.setIconImageId(5);
        iconModelList.add(iconModel);
        iconModel = new IconModel();
        iconModel.setIconImageId(6);
        iconModelList.add(iconModel);
        iconModel = new IconModel();
        iconModel.setIconImageId(7);
        iconModelList.add(iconModel);
        return iconModelList;
    }

    private View.OnClickListener onCancelButtonClickedListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        };
    }

    private View.OnClickListener onFinishButtonClickedListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        };
    }

    private void populateHasTagList(String info, List<String> hashTagList) {
        String patternString = "(\\s|\\A)#(\\w+)";
        Pattern pattern = Pattern.compile(patternString);
        Matcher regexMatcher = pattern.matcher(info);
        while (regexMatcher.find()) {
            if (regexMatcher.group().length() != 0) {
                hashTagList.add(regexMatcher.group());
            }
        }
    }
}
