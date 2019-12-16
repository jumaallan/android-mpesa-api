package com.androidstudy.daraja.okhttp

import android.util.Base64
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class AccessTokenInterceptor(private val CONSUMER_KEY: String, private val CONSUMER_SECRET: String) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val keys = "$CONSUMER_KEY:$CONSUMER_SECRET"
        val request = chain.request().newBuilder()
                .addHeader("Authorization", "Basic " + Base64.encodeToString(keys.toByteArray(), Base64.NO_WRAP))
                .build()
        return chain.proceed(request)
    }

}