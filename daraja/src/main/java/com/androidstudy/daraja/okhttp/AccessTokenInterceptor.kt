package com.androidstudy.daraja.okhttp

import android.util.Base64
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class AccessTokenInterceptor : Interceptor {

    private lateinit var CONSUMER_KEY: String
    private lateinit var CONSUMER_SECRET: String

    constructor(CONSUMER_KEY: String, CONSUMER_SECRET: String) {
        this.CONSUMER_KEY = CONSUMER_KEY
        this.CONSUMER_SECRET = CONSUMER_SECRET
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val keys = "$CONSUMER_KEY:$CONSUMER_SECRET"
        val request = chain.request().newBuilder()
                .addHeader("Authorization", "Basic " + Base64.encodeToString(keys.toByteArray(), Base64.NO_WRAP))
                .build()
        return chain.proceed(request)
    }

}