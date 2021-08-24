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

import com.androidstudy.daraja.network.okhttp.UnsafeOkHttpClient
import com.androidstudy.daraja.util.Environment
import com.androidstudy.daraja.util.Settings
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Provides an instance of retrofit to all classes that need it.
 */

object ApiClient {

    fun getAPI(baseUrl: String, interceptor: Interceptor): LNMAPI = getRetrofit(baseUrl, interceptor).create(LNMAPI::class.java)

    fun getAuthAPI(baseUrl: String, interceptor: Interceptor): AuthAPI = getRetrofit(baseUrl, interceptor).create(AuthAPI::class.java)

    private fun getRetrofit(baseUrl: String, interceptor: Interceptor): Retrofit {
        val httpLoggingInterceptor = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
        val builder = if (baseUrl == Environment.SANDBOX.url) {
            UnsafeOkHttpClient().unsafeOkHttpClient.addInterceptor(httpLoggingInterceptor)
        } else {
            OkHttpClient.Builder()
        }
        val client = builder.connectTimeout(Settings.CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(Settings.WRITE_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(Settings.READ_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
}