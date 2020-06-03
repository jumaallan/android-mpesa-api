package com.androidstudy.daraja.network

import com.androidstudy.daraja.model.AccessToken
import retrofit2.Call
import retrofit2.http.GET

interface AuthAPI {
    @get:GET("oauth/v1/generate?grant_type=client_credentials")
    val accessToken: Call<AccessToken>
}