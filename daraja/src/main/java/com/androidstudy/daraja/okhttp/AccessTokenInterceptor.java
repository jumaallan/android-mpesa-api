package com.androidstudy.daraja.okhttp;

import android.util.Base64;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AccessTokenInterceptor implements Interceptor {

    private final String CONSUMER_KEY, CONSUMER_SECRET;

    public AccessTokenInterceptor(String CONSUMER_KEY, String CONSUMER_SECRET) {
        this.CONSUMER_KEY = CONSUMER_KEY;
        this.CONSUMER_SECRET = CONSUMER_SECRET;
    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        String keys = CONSUMER_KEY + ":" + CONSUMER_SECRET;
        Request request = chain.request().newBuilder()
                .addHeader("Authorization", "Basic " + Base64.encodeToString(keys.getBytes(), Base64.NO_WRAP))
                .build();
        return chain.proceed(request);
    }
}
