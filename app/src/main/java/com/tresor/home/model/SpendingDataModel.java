package com.tresor.home.model;

import java.util.List;

/**
 * Created by kris on 7/1/17. Tokopedia
 */

public class SpendingDataModel {

    private int totalSpending;

    private String totalSpendingString;

    private int dailyAllocation;

    private int todayAllocation;

    private String dailyAllocationString;

    private String todayAllocationString;

    private int todaySaving;

    private String todaySavingString;

    private boolean isHistory;

    private List<FinancialHistoryModel> financialHistoryModelList;

    public String getTotalSpendingString() {
        return totalSpendingString;
    }

    public void setTotalSpendingString(String totalSpending) {
        this.totalSpendingString = totalSpending;
    }

    public String getDailyAllocationString() {
        return dailyAllocationString;
    }

    public void setDailyAllocationString(String dailyAllocation) {
        this.dailyAllocationString = dailyAllocation;
    }

    public String getTodayAllocationString() {
        return todayAllocationString;
    }

    public void setTodayAllocationString(String todayAllocation) {
        this.todayAllocationString = todayAllocation;
    }

    public String getTodaySavingString() {
        return todaySavingString;
    }

    public void setTodaySavingString(String todaySaving) {
        this.todaySavingString = todaySaving;
    }

    public boolean isHistory() {
        return isHistory;
    }

    public void setHistory(boolean history) {
        isHistory = history;
    }

    public List<FinancialHistoryModel> getFinancialHistoryModelList() {
        return financialHistoryModelList;
    }

    public void setFinancialHistoryModelList(List<FinancialHistoryModel> financialHistoryModelList) {
        this.financialHistoryModelList = financialHistoryModelList;
    }

    public int getTotalSpending() {
        return totalSpending;
    }

    public void setTotalSpending(int totalSpending) {
        this.totalSpending = totalSpending;
    }

    public int getDailyAllocation() {
        return dailyAllocation;
    }

    public void setDailyAllocation(int dailyAllocation) {
        this.dailyAllocation = dailyAllocation;
    }

    public int getTodayAllocation() {
        return todayAllocation;
    }

    public void setTodayAllocation(int todayAllocation) {
        this.todayAllocation = todayAllocation;
    }

    public int getTodaySaving() {
        return todaySaving;
    }

    public void setTodaySaving(int todaySaving) {
        this.todaySaving = todaySaving;
    }
}
