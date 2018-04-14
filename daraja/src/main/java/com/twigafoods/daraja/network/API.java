package com.twigafoods.daraja.network;

import com.twigafoods.daraja.model.AccessToken;
import com.twigafoods.daraja.model.LNMExpress;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface API {

    @GET("oauth/v1/generate?grant_type=client_credentials")
    Call<AccessToken> getAccessToken();

    @POST("mpesa/stkpush/v1/processrequest")
    Call<LNMExpress> getLNMPesa(@Body LNMExpress lnmExpress);
}
