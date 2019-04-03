package com.androidstudy.daraja.network;

import com.androidstudy.daraja.okhttp.AccessTokenInterceptor;
import com.androidstudy.daraja.okhttp.AuthInterceptor;
import com.androidstudy.daraja.okhttp.UnsafeOkHttpClient;
import com.androidstudy.daraja.util.Environment;
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

    static {
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    public static LNMAPI getAPI(String BASE_URL, String authToken) {
        if (LNMAPI == null) {
            OkHttpClient client = getClientBuilder(BASE_URL)
                    .connectTimeout(Settings.CONNECT_TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(Settings.WRITE_TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(Settings.READ_TIMEOUT, TimeUnit.SECONDS)
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

    private static OkHttpClient.Builder getClientBuilder(String base_url) {
        OkHttpClient.Builder builder;
        if (base_url.equals(Environment.SANDBOX.toString())){
            builder = new UnsafeOkHttpClient()
                    .getUnsafeOkHttpClient()
                    .addInterceptor(httpLoggingInterceptor);
        }else{
            builder = new OkHttpClient.Builder();
        }

       return builder;

    }

    public static AuthAPI getAuthAPI(String CONSUMER_KEY, String CONSUMER_SECRET, String BASE_URL) {
        if (authAPI == null) {
            OkHttpClient client = getClientBuilder(BASE_URL)
                    .connectTimeout(Settings.CONNECT_TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(Settings.WRITE_TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(Settings.READ_TIMEOUT, TimeUnit.SECONDS)
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
