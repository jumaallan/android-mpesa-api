package com.twigafoods.daraja.network;

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
    private static LNMAPI LNMAPI = null;
    private static AuthAPI authAPI = null;
    private static HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();

    static {
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    public static LNMAPI getAPI(String BASE_URL, String authToken) {
        if (LNMAPI == null) {
            OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                    .addInterceptor(httpLoggingInterceptor)
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
                    .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
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
