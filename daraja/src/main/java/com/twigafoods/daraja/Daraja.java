package com.twigafoods.daraja;

import android.content.Context;

import com.twigafoods.daraja.network.ApiClient;
import com.twigafoods.daraja.network.URLs;
import com.twigafoods.daraja.util.Env;

public class Daraja {

    public Daraja() {
    }

    //TODO :: MAKE THE ENV OPTION OPTIONAL ::: DEFAULT TO SANDBOX
    public static void with(Context context, String CONSUMER_KEY, String CONSUMER_SECRET, Env env) {
        if (env == Env.SANDBOX) {
            //Use Sandbox Base URL
            ApiClient.getRetrofitClient(CONSUMER_KEY, CONSUMER_SECRET, URLs.SANDBOX_BASE_URL);
        } else {
            //Use Production Base URL
            ApiClient.getRetrofitClient(CONSUMER_KEY, CONSUMER_SECRET, URLs.PRODUCTION_BASE_URL);
        }
    }
}
