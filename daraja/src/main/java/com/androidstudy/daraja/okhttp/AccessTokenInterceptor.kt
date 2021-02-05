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
package com.androidstudy.daraja.okhttp

import android.util.Base64
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class AccessTokenInterceptor(
    private val CONSUMER_KEY: String,
    private val CONSUMER_SECRET: String
) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val keys = "$CONSUMER_KEY:$CONSUMER_SECRET"

        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Basic " + Base64.encodeToString(keys.toByteArray(), Base64.NO_WRAP))
            .build()

        return chain.proceed(request)
    }
}