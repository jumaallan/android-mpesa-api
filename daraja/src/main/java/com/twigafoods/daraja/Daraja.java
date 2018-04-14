package com.twigafoods.daraja;

import android.content.Context;

import com.twigafoods.daraja.model.AccessToken;
import com.twigafoods.daraja.network.API;
import com.twigafoods.daraja.network.ApiClient;
import com.twigafoods.daraja.network.URLs;
import com.twigafoods.daraja.util.Env;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Daraja {

    public Daraja() {
    }

    //TODO :: MAKE THE ENV OPTION OPTIONAL ::: DEFAULT TO SANDBOX
    // LOGIC - 1. Get Token 2. Make Request
    public static void with(Context context, String CONSUMER_KEY, String CONSUMER_SECRET, Env env) {
        if (env == Env.SANDBOX) {
            //Use Sandbox Base URL
            ApiClient.setGetAccessToken(true);
            ApiClient.getRetrofitClient(CONSUMER_KEY, CONSUMER_SECRET, URLs.SANDBOX_BASE_URL).create(API.class).getAccessToken().enqueue(new Callback<AccessToken>() {
                @Override
                public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
                    ApiClient.setAuthToken(response.body().getAccess_token());
                }

                @Override
                public void onFailure(Call<AccessToken> call, Throwable t) {

                }
            });
        } else {
            //Use Production Base URL
            ApiClient.getRetrofitClient(CONSUMER_KEY, CONSUMER_SECRET, URLs.PRODUCTION_BASE_URL);
        }
    }
}
