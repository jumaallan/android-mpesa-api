package com.twigafoods.daraja.network;

import com.twigafoods.daraja.model.C2BRegister;
import com.twigafoods.daraja.model.LNMExpress;
import com.twigafoods.daraja.model.LNMResult;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface API {

    @POST("mpesa/stkpush/v1/processrequest")
    Call<LNMResult> getLNMPesa(@Body LNMExpress lnmExpress);

    @POST("mpesa/c2b/v1/registerurl")
    Call<LNMResult> registerURL(@Body C2BRegister c2BRegister);
}
