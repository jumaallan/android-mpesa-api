package com.twigafoods.daraja;

import android.util.Log;

import com.twigafoods.daraja.model.AccessToken;
import com.twigafoods.daraja.model.LNMExpress;
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

    //Request the STK Push
    public static void sendSTKPush(Env env) {
        if (env == Env.SANDBOX) {
            //Use Sandbox Base URL
            ApiClient.setGetAccessToken(false);
            LNMExpress lnmExpress = new LNMExpress(
                    "174379",
                    "MTc0Mzc5YmZiMjc5ZjlhYTliZGJjZjE1OGU5N2RkNzFhNDY3Y2QyZTBjODkzMDU5YjEwZjc4ZTZiNzJhZGExZWQyYzkxOTIwMTgwNDEyMjAwNTEy",
                    "20180412200512",
                    "CustomerPayBillOnline",
                    "1",
                    "254708374149",
                    "174379",
                    "254797435901",
                    "http://109.74.205.95:4000/",
                    "001",
                    "My Money"
            );
            ApiClient.getRetrofitClient("", "", URLs.SANDBOX_BASE_URL).create(API.class).getLNMPesa(lnmExpress).enqueue(new Callback<LNMExpress>() {
                @Override
                public void onResponse(Call<LNMExpress> call, Response<LNMExpress> response) {
                }

                @Override
                public void onFailure(Call<LNMExpress> call, Throwable t) {

                }
            });
        }
    }

    //TODO :: MAKE THE ENV OPTION OPTIONAL ::: DEFAULT TO SANDBOX
    //TODO :: CHECK FOR INTERNET CONNECTION
    // LOGIC - 1. Get Token 2. Make Request
    //Generate the Auth Token
    public static void with(String CONSUMER_KEY, String CONSUMER_SECRET, Env env) {
        if (env == Env.SANDBOX) {
            //Use Sandbox Base URL
            ApiClient.setGetAccessToken(true);
            ApiClient.getRetrofitClient(CONSUMER_KEY, CONSUMER_SECRET, URLs.SANDBOX_BASE_URL).create(API.class).getAccessToken().enqueue(new Callback<AccessToken>() {
                @Override
                public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
                    ApiClient.setAuthToken(response.body().getAccess_token());
                    Log.d("TOKEN", response.body().getAccess_token());
                }

                @Override
                public void onFailure(Call<AccessToken> call, Throwable t) {

                }
            });
        } else {
            //Use Production Base URL
            ApiClient.setGetAccessToken(true);
            ApiClient.getRetrofitClient(CONSUMER_KEY, CONSUMER_SECRET, URLs.PRODUCTION_BASE_URL).create(API.class).getAccessToken().enqueue(new Callback<AccessToken>() {
                @Override
                public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
                    ApiClient.setAuthToken(response.body().getAccess_token());
                }

                @Override
                public void onFailure(Call<AccessToken> call, Throwable t) {

                }
            });
        }
    }
}
