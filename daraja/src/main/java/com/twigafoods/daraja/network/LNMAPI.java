package com.twigafoods.daraja.network;

import com.twigafoods.daraja.model.LNMExpress;
import com.twigafoods.daraja.model.LNMResult;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LNMAPI {

    @POST("mpesa/stkpush/v1/processrequest")
    Call<LNMResult> getLNMPesa(@Body LNMExpress lnmExpress);

}