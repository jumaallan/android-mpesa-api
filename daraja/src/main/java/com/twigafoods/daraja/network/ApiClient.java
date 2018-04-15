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
    private static final Object lock = new Object();

    private static API api = null;
    private static AuthAPI authAPI = null;
    private static HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
    private static String mAuthToken = "";

    static {
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    public static API getAPI(String BASE_URL) {
        if (api == null) {

            OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                    .addInterceptor(httpLoggingInterceptor)
                    .build();

            api = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()
                    .create(API.class);
        }
        return api;
    }

    public static AuthAPI getAuthAPI(String CONSUMER_KEY, String CONSUMER_SECRET, String BASE_URL) {
        if (authAPI == null) {

            OkHttpClient client = okHttpClient(CONSUMER_KEY, CONSUMER_SECRET);

            authAPI = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()
                    .create(AuthAPI.class);
        }
        return authAPI;
    }

    private static OkHttpClient okHttpClient(String CONSUMER_KEY, String CONSUMER_SECRET) {
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor);

        boolean isGetAccessToken = CONSUMER_KEY == null || CONSUMER_SECRET == null;
        if (isGetAccessToken) {
            okHttpClient.addInterceptor(new AccessTokenInterceptor(CONSUMER_KEY, CONSUMER_SECRET));
        }

        if (mAuthToken != null && !mAuthToken.isEmpty()) {
            okHttpClient.addInterceptor(new AuthInterceptor(mAuthToken));
        }

        return okHttpClient.build();
    }

    //Set Authentication Token
    public static void setAuthToken(String authToken) {
        ApiClient.mAuthToken = authToken;
        Log.d("TTT", ApiClient.mAuthToken);
    }
}
