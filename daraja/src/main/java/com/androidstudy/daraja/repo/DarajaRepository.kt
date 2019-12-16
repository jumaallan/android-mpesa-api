package com.androidstudy.daraja.repo

import com.androidstudy.daraja.constants.TransactionType
import com.androidstudy.daraja.model.AccessToken
import com.androidstudy.daraja.model.LNMExpress
import com.androidstudy.daraja.model.PaymentResult
import com.androidstudy.daraja.network.ApiClient
import com.androidstudy.daraja.network.ApiClient.getAuthAPI
import com.androidstudy.daraja.util.Settings
import retrofit2.Call

class DarajaRepository(var consumerKey: String, var consumerSecret: String, var baseUrl: String) {

    val accessToken: Call<AccessToken> get() = getAuthAPI(consumerKey, consumerSecret, baseUrl).accessToken

    //TODO('Refactor')
    fun initiatePayment(token: String, phoneNumber: String, amount: String, accountReference: String, description: String, businessShortCode: String, passKey: String, transactionType: TransactionType, callbackUrl: String): Call<PaymentResult> {
        val sanitizedPhoneNumber = Settings.formatPhoneNumber(phoneNumber)
        val timeStamp = Settings.generateTimestamp()
        val generatedPassword = Settings.generatePassword(businessShortCode, passKey, timeStamp)

        LNMExpress(businessShortCode, passKey, transactionType, amount, phoneNumber, businessShortCode, phoneNumber, callbackUrl, accountReference, description)

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