package com.tresor.home.activity;

import com.tresor.common.activity.addpayment.PaymentTemplate;
import com.tresor.home.inteface.HomeActivityListener;
import com.tresor.home.model.FinancialHistoryModel;

/**
 * Created by kris on 10/10/17. Tokopedia
 */

public class AddPaymentActivity extends PaymentTemplate {
    @Override
    protected FinancialHistoryModel initialModel() {
        return new FinancialHistoryModel();
    }

    @Override
    protected int imageChoosen() {
        return 0;
    }

    @Override
    protected int getMode() {
        return HomeActivityListener.ADD_NEW_PAYMENT_REQUEST_CODE;
    }
}
