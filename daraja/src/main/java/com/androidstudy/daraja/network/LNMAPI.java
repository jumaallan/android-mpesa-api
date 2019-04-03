package com.androidstudy.daraja.network;

import com.androidstudy.daraja.model.LNMExpress;
import com.androidstudy.daraja.model.PaymentResult;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LNMAPI {

    @POST("mpesa/stkpush/v1/processrequest")
    Call<PaymentResult> getLNMPesa(@Body LNMExpress lnmExpress);

}