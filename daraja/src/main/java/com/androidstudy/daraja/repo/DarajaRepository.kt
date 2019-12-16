package com.androidstudy.daraja.repo

import android.util.Log
import com.androidstudy.daraja.constants.TransactionType
import com.androidstudy.daraja.model.AccessToken
import com.androidstudy.daraja.model.LNMExpress
import com.androidstudy.daraja.model.PaymentResult
import com.androidstudy.daraja.network.ApiClient
import com.androidstudy.daraja.network.ApiClient.getAuthAPI
import com.androidstudy.daraja.network.AuthAPI
import com.androidstudy.daraja.util.Settings
import retrofit2.Call

class DarajaRepository {
    lateinit var consumerKey: String
    lateinit var consumerSecret: String
    lateinit var baseUrl: String

    var authAPI: AuthAPI


    constructor(consumerKey: String, consumerSecret: String, baseUrl: String) {
        this.consumerKey = consumerKey
        this.consumerSecret = consumerSecret
        this.baseUrl = baseUrl

        authAPI = getAuthAPI(consumerKey, consumerSecret, baseUrl)
    }

    fun getAccessToken(): Call<AccessToken> {
        return getAuthAPI(consumerKey, consumerSecret, baseUrl).accessToken
    }


    //TODO('Refactor')
    fun initiatePayment(token: String, phoneNumber: String, amount: String, accountReference: String, description: String, businessShortCode: String,
                        passKey: String, transactionType: TransactionType, callbackUrl: String): Call<PaymentResult> {
        val sanitizedPhoneNumber = Settings.formatPhoneNumber(phoneNumber)
        val timeStamp = Settings.generateTimestamp()
        val generatedPassword = Settings.generatePassword(businessShortCode, passKey, timeStamp)

        val express = LNMExpress(
                businessShortCode = businessShortCode,
                password = generatedPassword,
                timestamp = timeStamp,
                amount = amount,
                transactionType = transactionType.transType,
                partyA = phoneNumber,
                partyB = businessShortCode,
                phoneNumber = sanitizedPhoneNumber.orEmpty(),
                callBackURL = callbackUrl,
                accountReference = accountReference,
                transactionDesc = description
        )

        return ApiClient.getAPI(baseUrl, token).getLNMPesa(express)
    }

}