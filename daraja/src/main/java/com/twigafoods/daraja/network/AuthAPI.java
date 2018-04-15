package com.twigafoods.daraja.network;

import com.twigafoods.daraja.model.AccessToken;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AuthAPI {

    @GET("oauth/v1/generate?grant_type=client_credentials")
    Call<AccessToken> getAccessToken();

}
