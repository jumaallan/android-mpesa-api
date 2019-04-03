package com.androidstudy.daraja.callback;

import com.androidstudy.daraja.model.PaymentResult;

public interface DarajaPaymentListener {

    void onPaymentRequestComplete(PaymentResult result);

    void onPaymentCancelled();

    void onPaymentFailure(DarajaException exception);

    void onNetworkFailure(DarajaException exception);
}
