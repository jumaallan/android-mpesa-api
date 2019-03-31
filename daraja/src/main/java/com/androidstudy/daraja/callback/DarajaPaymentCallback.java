package com.androidstudy.daraja.callback;

import android.support.annotation.NonNull;

import com.androidstudy.daraja.DarajaListener;
import com.androidstudy.daraja.model.LNMResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DarajaPaymentCallback implements Callback<LNMResult>{

    final DarajaPaymentListener listener;

    public DarajaPaymentCallback(final DarajaPaymentListener listener) {
        this.listener = listener;
    }

    @Override
    public void onResponse(@NonNull Call<LNMResult> call, @NonNull Response<LNMResult> response) {
        if (response.isSuccessful()) {
            LNMResult result = response.body();
            if (result != null) {
                if (result.ResponseCode.equals("0")){
                    listener.onPaymentRequestComplete(
                            result.ResponseCode,
                            result.MerchantRequestID,
                            result.CheckoutRequestID,
                            result.ResponseDescription,
                            result.CustomerMessage
                    );
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
    public void onFailure(@NonNull Call<LNMResult> call, @NonNull Throwable t) {
        listener.onNetworkFailure(new DarajaException(t.getLocalizedMessage()));
    }
}
