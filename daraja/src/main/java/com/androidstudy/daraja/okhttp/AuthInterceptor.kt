package com.androidstudy.daraja.okhttp

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class AuthInterceptor : Interceptor {

    private lateinit var authToken: String

    constructor ( authToken: String) {
        this.authToken = authToken
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $authToken")
                .build()
        return chain.proceed(request)
    }

}