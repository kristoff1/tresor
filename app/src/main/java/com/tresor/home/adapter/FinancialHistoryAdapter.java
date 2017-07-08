package com.tresor.home.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tresor.R;
import com.tresor.home.model.FinancialHistoryModel;
import com.tresor.home.model.SpendingDataModel;

import java.util.List;

/**
 * Created by kris on 5/28/17. Tokopedia
 */

public class FinancialHistoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private Context context;
    private SpendingDataModel spendingDataModel;
    private List<FinancialHistoryModel> financialHistoryModelList;

    private static final int NUMBER_OF_HEADER_ADAPTER = 1;

    private static final int HEADER_ADAPTER = 0;
    private static final int ITEM_ADAPTER = 1;

    public FinancialHistoryAdapter(Context context, SpendingDataModel spendingDataModel) {
        this.context = context;
        this.spendingDataModel = spendingDataModel;
        this.financialHistoryModelList = spendingDataModel.getFinancialHistoryModelList();
    }

    @Override
    public int getItemViewType(int position) {
        //TODO set 2 item ivew type one for total
        if(position == 0) return HEADER_ADAPTER;
        return ITEM_ADAPTER;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == HEADER_ADAPTER) {
            View adapterView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.history_header_adapter, parent, false);
            return new FinancialHistoryHeaderHolder(adapterView);
        } else {
            View adapterView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.financial_list_adapter, parent, false);
            return new FinancialHistoryViewHolder(adapterView);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case HEADER_ADAPTER:
                FinancialHistoryHeaderHolder headerHolder = (FinancialHistoryHeaderHolder) holder;
                SpendingDataModel spendingData = spendingDataModel;
                headerHolder.totalExpense.setText(spendingData.getTotalSpendingString());
                //TODO SIMPLIFY!!!!
                if(spendingData.isHistory()) {
                    headerHolder.allocatedSpendingLayout.setVisibility(View.GONE);
                    headerHolder.saveMoneyLayout.setVisibility(View.GONE);
                } else if(spendingData.getDailyAllocation() == 0
                        && spendingData.getTodayAllocation() == 0) {
                    headerHolder.allocatedSpendingLayout.setVisibility(View.GONE);
                    headerHolder.saveMoneyLayout.setVisibility(View.GONE);
                } else {
                    headerHolder.allocatedSpendingLayout.setVisibility(View.VISIBLE);
                    headerHolder.saveMoneyLayout.setVisibility(View.VISIBLE);
                    if(spendingData.getTodayAllocation() == 0)
                        headerHolder.totalAllocated.setText(spendingData.getDailyAllocationString());
                    else headerHolder.totalAllocated.setText(spendingData.getTodayAllocationString());
                    headerHolder.totalSaved.setText(spendingData.getTodaySavingString());
                }
                break;
            case ITEM_ADAPTER:
                FinancialHistoryViewHolder itemHolder = (FinancialHistoryViewHolder) holder;
                FinancialHistoryModel financialHistoryModel = financialHistoryModelList
                        .get(position - NUMBER_OF_HEADER_ADAPTER);
                itemHolder.historyAmount.setText(financialHistoryModel.getAmount());
                itemHolder.historyHashtag.setText(financialHistoryModel.getHashtag());
                itemHolder.historyInfo.setText(financialHistoryModel.getInfo());
                itemHolder.historyDate.setText(financialHistoryModel.getDate());
                setCardTheme(financialHistoryModel.getTheme(), itemHolder);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return financialHistoryModelList.size() + NUMBER_OF_HEADER_ADAPTER;
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

    class FinancialHistoryHeaderHolder extends RecyclerView.ViewHolder {
        private LinearLayout allocatedSpendingLayout;
        private LinearLayout saveMoneyLayout;
        private TextView totalAllocated;
        private TextView totalExpense;
        private TextView totalSaved;

        FinancialHistoryHeaderHolder(View itemView) {
            super(itemView);
            allocatedSpendingLayout = (LinearLayout) itemView.findViewById(R.id.allocated_spending_layout);
            saveMoneyLayout = (LinearLayout) itemView.findViewById(R.id.save_money_layout);
            totalAllocated = (TextView) itemView.findViewById(R.id.total_allocated);
            totalExpense = (TextView) itemView.findViewById(R.id.total_expense);
            totalSaved = (TextView) itemView.findViewById(R.id.total_saved);
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
