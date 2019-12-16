package com.androidstudy.daraja

import com.androidstudy.daraja.callback.DarajaCallback
import com.androidstudy.daraja.callback.DarajaListener
import com.androidstudy.daraja.callback.DarajaPaymentCallback
import com.androidstudy.daraja.callback.DarajaPaymentListener
import com.androidstudy.daraja.constants.TransactionType
import com.androidstudy.daraja.model.AccessToken
import com.androidstudy.daraja.repo.DarajaRepository


object Daraja {

    lateinit var consumerKey: String
    lateinit var consumerSecret: String
    lateinit var businessShortCode: String
    lateinit var passKey: String
    lateinit var transactionType: TransactionType
    lateinit var callbackUrl: String
    lateinit var baseUrl: String

    lateinit var repo: DarajaRepository

    fun builder(consumerKey: String, consumerSecret: String): Builder {
        return Builder(consumerKey, consumerSecret)
    }


    fun getAccessToken(listener: DarajaListener<AccessToken>): DarajaListener<AccessToken> {
        repo.getAccessToken().enqueue(DarajaCallback(listener))
        return listener
    }

    fun initiatePayment(token: String, phoneNumber: String, amount: String, accountReference: String, description: String, listener: DarajaPaymentListener): DarajaPaymentListener {
        repo.initiatePayment(token = token,
                phoneNumber = phoneNumber,
                amount = amount,
                accountReference = accountReference,
                description = description,
                businessShortCode = businessShortCode,
                passKey = passKey,
                transactionType = transactionType,
                callbackUrl = callbackUrl
        ).enqueue(DarajaPaymentCallback(listener))
        return listener
    }
}