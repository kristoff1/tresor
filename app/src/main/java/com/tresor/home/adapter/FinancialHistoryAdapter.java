package com.tresor.home.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tresor.R;
import com.tresor.home.model.FinancialHistoryModel;

import java.util.List;

/**
 * Created by kris on 5/28/17. Tokopedia
 */

public class FinancialHistoryAdapter extends RecyclerView.Adapter<FinancialHistoryAdapter.FinancialHistoryViewHolder> {

    private Context context;
    private List<FinancialHistoryModel> financialHistoryModelList;

    public FinancialHistoryAdapter(Context context, List<FinancialHistoryModel> financialHistoryModelList) {
        this.context = context;
        this.financialHistoryModelList = financialHistoryModelList;
    }

    @Override
    public int getItemViewType(int position) {
        //TODO set 2 item ivew type one for total
        return super.getItemViewType(position);
    }

    @Override
    public FinancialHistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View adapterView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.financial_list_adapter, parent, false);

        return new FinancialHistoryViewHolder(adapterView);
    }

    @Override
    public void onBindViewHolder(FinancialHistoryViewHolder holder, int position) {
        FinancialHistoryModel financialHistoryModel = financialHistoryModelList.get(position);
        holder.historyAmount.setText(financialHistoryModel.getAmount());
        holder.historyHashtag.setText(financialHistoryModel.getHashtag());
        holder.historyInfo.setText(financialHistoryModel.getInfo());
        holder.historyDate.setText(financialHistoryModel.getDate());
        setCardTheme(financialHistoryModel.getTheme(), holder);
    }

    @Override
    public int getItemCount() {
        return financialHistoryModelList.size();
    }

    class FinancialHistoryViewHolder extends RecyclerView.ViewHolder {
        private TextView historyAmount;
        private TextView historyHashtag;
        private TextView historyInfo;
        private TextView historyDate;
        private CardView itemPlaceHolder;
        private ImageView spendingIcon;

        FinancialHistoryViewHolder(View itemView) {
            super(itemView);
            historyAmount = (TextView) itemView.findViewById(R.id.history_amount);
            historyHashtag = (TextView) itemView.findViewById(R.id.history_hastag);
            historyInfo = (TextView) itemView.findViewById(R.id.history_info);
            historyDate = (TextView) itemView.findViewById(R.id.history_date);
            itemPlaceHolder = (CardView) itemView.findViewById(R.id.item_place_holder);
            spendingIcon = (ImageView) itemView.findViewById(R.id.spending_icon);
        }
    }

    private void setCardTheme(int theme, FinancialHistoryViewHolder holder) {
        switch (theme) {
            case 0:
                holder.spendingIcon.setImageResource(R.mipmap.ic_cat_everything_else_big);
                break;
            case 1:
                holder.spendingIcon.setImageResource(R.mipmap.ic_cat_clothing_big);
                break;
            case 2:
                holder.spendingIcon.setImageResource(R.mipmap.ic_cat_kitchen_dining_big);
                break;
            case 3:
                holder.spendingIcon.setImageResource(R.mipmap.ic_cat_health_big);
                break;
            case 4:
                holder.spendingIcon.setImageResource(R.mipmap.ic_cat_automotive_big);
                break;
            default:
                holder.spendingIcon.setImageResource(R.mipmap.ic_cat_everything_else_big);
                break;
        }
    }

}
