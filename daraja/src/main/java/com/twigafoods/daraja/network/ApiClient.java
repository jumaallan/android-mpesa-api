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
    private boolean isGetAccessToken;
    private String authToken;

    private HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();

    public static Retrofit getRetrofitClient(String baseUrl) {
        if (retrofit == null) {
            Retrofit.Builder builder = new Retrofit.Builder();
            builder.baseUrl(baseUrl);
            builder.addConverterFactory(GsonConverterFactory.create());
            retrofit = builder.build();
        }
        return retrofit;
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
}
