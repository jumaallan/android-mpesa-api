/*
 * Copyright 2021 Juma Allan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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

    fun getAPI(baseUrl: String, authToken: String): LNMAPI {
        return if (lnmApi == null) {
            val client: OkHttpClient = getClientBuilder(baseUrl)
                .connectTimeout(Settings.CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(Settings.WRITE_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(Settings.READ_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(AuthInterceptor(authToken))
                .build()

            Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
                .create(LNMAPI::class.java)
        } else throw (Exception())
    }

    private fun getClientBuilder(baseUrl: String): OkHttpClient.Builder {
        return if (baseUrl == Environment.SANDBOX.url) {
            UnsafeOkHttpClient().unsafeOkHttpClient.addInterceptor(httpLoggingInterceptor)
        } else {
            OkHttpClient.Builder()
        }
    }

    fun getAuthAPI(consumeKey: String, consumerSecret: String, baseUrl: String): AuthAPI {
        return if (authAPI == null) {
            val client = getClientBuilder(baseUrl)
                .connectTimeout(Settings.CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(Settings.WRITE_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(Settings.READ_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(AccessTokenInterceptor(consumeKey, consumerSecret))
                .build()

            Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
                .create(AuthAPI::class.java)
        } else throw (Exception())
    }
}