package com.tresor.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    FinancialHistoryViewHolder(View itemView) {
        super(itemView);
        historyAmount = (TextView) itemView.findViewById(R.id.history_amount);
        historyHashtag = (TextView) itemView.findViewById(R.id.history_hastag);
        historyInfo = (TextView) itemView.findViewById(R.id.history_info);
        historyDate = (TextView) itemView.findViewById(R.id.history_date);
    }
}

}
