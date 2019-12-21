package com.androidstudy.daraja.network

import com.androidstudy.daraja.okhttp.AccessTokenInterceptor
import com.androidstudy.daraja.okhttp.AuthInterceptor
import com.androidstudy.daraja.okhttp.UnsafeOkHttpClient
import com.androidstudy.daraja.util.Environment
import com.androidstudy.daraja.util.Settings
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {

    private var lnmApi: LNMAPI? = null
    private var authAPI: AuthAPI? = null
    private val httpLoggingInterceptor = HttpLoggingInterceptor()

    init {
        httpLoggingInterceptor.apply { httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY }
    }


    fun getAPI(BASE_URL: String, authToken: String): LNMAPI {
        return if (lnmApi == null) {
            val client: OkHttpClient = getClientBuilder(BASE_URL)
                    .connectTimeout(Settings.CONNECT_TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(Settings.WRITE_TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(Settings.READ_TIMEOUT, TimeUnit.SECONDS)
                    .addInterceptor(AuthInterceptor(authToken))
                    .build()

            Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()
                    .create(LNMAPI::class.java)
        } else throw (Exception())
    }

    private fun getClientBuilder(base_url: String): OkHttpClient.Builder {
        return if (base_url == Environment.SANDBOX.url) UnsafeOkHttpClient().unsafeOkHttpClient.addInterceptor(httpLoggingInterceptor)
        else OkHttpClient.Builder()
    }


    fun getAuthAPI(CONSUMER_KEY: String, CONSUMER_SECRET: String, BASE_URL: String): AuthAPI {
        return if (authAPI == null) {
            val client = getClientBuilder(BASE_URL)
                    .connectTimeout(Settings.CONNECT_TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(Settings.WRITE_TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(Settings.READ_TIMEOUT, TimeUnit.SECONDS)
                    .addInterceptor(AccessTokenInterceptor(CONSUMER_KEY, CONSUMER_SECRET))
                    .build()

            Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()
                    .create(AuthAPI::class.java)
        } else throw (Exception())
    }
}