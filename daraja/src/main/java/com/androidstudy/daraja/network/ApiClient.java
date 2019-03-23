package com.androidstudy.daraja.network;

import com.androidstudy.daraja.okhttp.AccessTokenInterceptor;
import com.androidstudy.daraja.okhttp.AuthInterceptor;
import com.androidstudy.daraja.util.Env;
import com.androidstudy.daraja.util.Settings;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static LNMAPI LNMAPI = null;
    private static AuthAPI authAPI = null;
    private static HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();

    //TODO('Option to turn on or off for security')
    static {
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    public static LNMAPI getAPI(String BASE_URL, String authToken) {
        if (LNMAPI == null) {
            OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(Settings.CONNECT_TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(Settings.WRITE_TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(Settings.READ_TIMEOUT, TimeUnit.SECONDS)
                    //Using base url for now, ideally debug would work, or alternatively dev pass their okhttp client
                    .addInterceptor(BASE_URL.equals(Env.SANDBOX.toString())? httpLoggingInterceptor : null)
                    .addInterceptor(new AuthInterceptor(authToken))
                    .build();

            LNMAPI = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()
                    .create(LNMAPI.class);
        }
        return LNMAPI;
    }

    public static AuthAPI getAuthAPI(String CONSUMER_KEY, String CONSUMER_SECRET, String BASE_URL) {
        if (authAPI == null) {
            OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(Settings.CONNECT_TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(Settings.WRITE_TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(Settings.READ_TIMEOUT, TimeUnit.SECONDS)
                    .addInterceptor(httpLoggingInterceptor)
                    .addInterceptor(new AccessTokenInterceptor(CONSUMER_KEY, CONSUMER_SECRET))
                    .build();

            authAPI = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()
                    .create(AuthAPI.class);
        }
        return authAPI;
    }
}
