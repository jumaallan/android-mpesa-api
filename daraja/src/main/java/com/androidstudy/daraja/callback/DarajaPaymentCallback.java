package com.androidstudy.daraja.callback;

import android.support.annotation.NonNull;

import com.androidstudy.daraja.model.PaymentResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DarajaPaymentCallback implements Callback<PaymentResult>{

    final DarajaPaymentListener listener;

    public DarajaPaymentCallback(final DarajaPaymentListener listener) {
        this.listener = listener;
    }

    @Override
    public void onResponse(@NonNull Call<PaymentResult> call, @NonNull Response<PaymentResult> response) {
        if (response.isSuccessful()) {
            PaymentResult result = response.body();
            if (result != null) {
                if (result.ResponseCode.equals("0")){
                    listener.onPaymentRequestComplete(result);
                }else{
                    String error = result.ResponseCode + " : " + result.ResponseDescription;
                    listener.onPaymentFailure(new DarajaException(error));
                }

                return;
            }
        }else {
            String code = response.code() + "";
            String error = code + " : " + response.errorBody().toString();

            listener.onPaymentFailure(new DarajaException(error));
        }
    }

    @Override
    public void onFailure(@NonNull Call<PaymentResult> call, @NonNull Throwable t) {
        listener.onNetworkFailure(new DarajaException(t.getLocalizedMessage()));
    }
}
