package com.tresor.home.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kris on 5/28/17. Tokopedia
 */

public class FinancialHistoryModel {

    private String amount;

    private int amountUnformatted;

    private boolean usesComma;

    private int currencyId;

    private List<String> hashtag = new ArrayList<>();

    private String info;

    private String date;

    private int theme;

    public String getAmount() {
        return amount;
    }

    public int getAmountUnformatted() {
        return amountUnformatted;
    }

    public void setAmountUnformatted(int amountUnformatted) {
        this.amountUnformatted = amountUnformatted;
    }

    public boolean isUsesComma() {
        return usesComma;
    }

    public void setUsesComma(boolean usesComma) {
        this.usesComma = usesComma;
    }

    public int getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(int currencyId) {
        this.currencyId = currencyId;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public List<String> getHashtag() {
        return hashtag;
    }

    public void setHashtag(List<String> hashtag) {
        this.hashtag = hashtag;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTheme() {
        return theme;
    }

    public void setTheme(int theme) {
        this.theme = theme;
    }
}
