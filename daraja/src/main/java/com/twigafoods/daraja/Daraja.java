package com.twigafoods.daraja;

import android.content.Context;

import com.twigafoods.daraja.util.Env;

public class Daraja {

    public Daraja() {
    }

    public static void with(Context context, String consumerKey, String consumerSecret, Env env) {
//        ApiClient.getRetrofitClient("").
        if (env == Env.SANDBOX) {
            //Use Sandbox Base URL
        } else {
            //Use Production Base URL
        }

    }
}
