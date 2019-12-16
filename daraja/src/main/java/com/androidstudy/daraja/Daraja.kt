package com.androidstudy.daraja

import com.androidstudy.daraja.callback.DarajaCallback
import com.androidstudy.daraja.callback.DarajaListener
import com.androidstudy.daraja.callback.DarajaPaymentCallback
import com.androidstudy.daraja.callback.DarajaPaymentListener
import com.androidstudy.daraja.constants.TransactionType
import com.androidstudy.daraja.model.AccessToken
import com.androidstudy.daraja.repo.DarajaRepository


class Daraja {

    lateinit var consumerKey: String
    lateinit var consumerSecret: String
    lateinit var businessShortCode: String
    lateinit var passKey: String
    lateinit var transactionType: TransactionType
    lateinit var callbackUrl: String
    lateinit var baseUrl: String

    lateinit var repo: DarajaRepository

    fun Builder(consumerKey: String, consumerSecret: String): Builder {
        return Builder(consumerKey, consumerSecret)
    }


    fun getAccessToken(listener: DarajaListener<AccessToken>): DarajaListener<AccessToken> {
        repo.accessToken.enqueue(DarajaCallback(listener))
        return listener
    }

    fun initiatePayment(token: String, phoneNumber: String, amount: String, accountReference: String,
                        description: String, listener: DarajaPaymentListener): DarajaPaymentListener {
        repo.initiatePayment(token, phoneNumber, amount, accountReference, description, businessShortCode,
                passKey, transactionType, callbackUrl).enqueue(DarajaPaymentCallback(listener))
        return listener
    }
}