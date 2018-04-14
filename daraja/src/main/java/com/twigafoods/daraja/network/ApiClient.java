package com.twigafoods.daraja.network;

import android.util.Log;

import com.twigafoods.daraja.okhttp.AccessTokenInterceptor;
import com.twigafoods.daraja.okhttp.AuthInterceptor;

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
    private static boolean isGetAccessToken;
    private static HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
    private static String mAuthToken = "";

    public static Retrofit getRetrofitClient(String CONSUMER_KEY, String CONSUMER_SECRET, String BASE_URL) {
        if (retrofit == null) {
            Retrofit.Builder builder = new Retrofit.Builder();
            builder.baseUrl(BASE_URL);
            builder.addConverterFactory(GsonConverterFactory.create());
            OkHttpClient.Builder okhttpBuilder = okHttpClient();
            if (isGetAccessToken) {
                okhttpBuilder.addInterceptor(new AccessTokenInterceptor(CONSUMER_KEY, CONSUMER_SECRET));
            }
            if (mAuthToken != null && !mAuthToken.isEmpty()) {
                okhttpBuilder.addInterceptor(new AuthInterceptor(mAuthToken));
            }
            builder.client(okhttpBuilder.build());
            retrofit = builder.build();
        }
        return retrofit;
    }

    private static OkHttpClient.Builder okHttpClient() {
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        okHttpClient
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor);

        return okHttpClient;
    }

    //Called to get the Access Token
    public static void setGetAccessToken(boolean getAccessToken) {
        isGetAccessToken = getAccessToken;
    }

    //Set Authentication Token
    public static void setAuthToken(String authToken) {
        ApiClient.authToken = authToken;
        Log.d("TTT", ApiClient.authToken);
    }
}
