package com.androidstudy.daraja.callback;

import android.support.annotation.NonNull;

public interface DarajaPaymentListener {

    void onPaymentRequestComplete(
            String ResponseCode,
            String MerchantRequestID,
            String CheckoutRequestID,
            String ResponseDescription,
            String CustomerMessage
    );

    void onPaymentCancelled();

    void onPaymentFailure(DarajaException exception);

    void onNetworkFailure(DarajaException exception);
}
