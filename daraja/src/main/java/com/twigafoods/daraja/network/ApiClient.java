package com.twigafoods.daraja.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit retrofit = null;
    private boolean isSandbox = true; //Set Sandbox to True
    private boolean isGetAccessToken;
    private String authToken;

    public static Retrofit getRetrofitClient(String baseUrl) {
        if (retrofit == null) {
            Retrofit.Builder builder = new Retrofit.Builder();
            builder.baseUrl(baseUrl);
            builder.addConverterFactory(GsonConverterFactory.create());
            retrofit = builder.build();
        }
        return retrofit;
    }

    //Called to set Production Mode
    public ApiClient setIsDebug(boolean isSandbox) {
        this.isSandbox = isSandbox;
        return this;
    }

    //Called to get the Access Token
    public ApiClient setGetAccessToken(boolean getAccessToken) {
        isGetAccessToken = getAccessToken;
        return this;
    }

    //Set Authentication Token
    public ApiClient setAuthToken(String authToken) {
        this.authToken = authToken;
        return this;
    }

    //Create a Service Instance
//    public API apiService() {
//        return getRetrofitClient().create(API.class);
//    }
}
