package com.twigafoods.daraja.network;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.twigafoods.daraja.util.Settings.CONNECT_TIMEOUT;
import static com.twigafoods.daraja.util.Settings.READ_TIMEOUT;
import static com.twigafoods.daraja.util.Settings.WRITE_TIMEOUT;

public class ApiClient {

    private static Retrofit retrofit = null;
    private boolean isSandbox = true; //Set Sandbox to True
    private HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();

    public Retrofit getRetrofitClient() {
        if (retrofit == null) {
            Retrofit.Builder builder = new Retrofit.Builder();
            if (isSandbox) {
                //Use Sandbox Base URL
                builder.baseUrl(URLs.SANDBOX_BASE_URL);
            } else {
                //Use Production Base URL
                builder.baseUrl(URLs.PRODUCTION_BASE_URL);
            }
            builder.addConverterFactory(GsonConverterFactory.create());
            retrofit = builder.build();
        }
        return retrofit;
    }

    public ApiClient setIsDebug(boolean isSandbox) {
        this.isSandbox = isSandbox;
        return this;
    }

    private OkHttpClient.Builder okHttpClient() {
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        okHttpClient
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor);

        return okHttpClient;
    }
}
