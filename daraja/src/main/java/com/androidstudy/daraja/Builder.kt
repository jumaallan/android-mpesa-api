package com.androidstudy.daraja

import com.androidstudy.daraja.constants.TransactionType
import com.androidstudy.daraja.repo.DarajaRepository
import com.androidstudy.daraja.util.Environment

class Builder(private  var consumerKey: String, private  var consumerSecret: String) {

    private lateinit var businessShortCode: String
    private lateinit var passKey: String
    private lateinit var transactionType: TransactionType
    private lateinit var callbackUrl: String
    private lateinit var environment: Environment

    fun setPassKey(passKey: String): Builder {
        this.passKey = passKey
        return this
    }

    fun setTransactionType(transactionType: TransactionType): Builder {
        this.transactionType = transactionType
        return this
    }

    fun setCallbackUrl(callbackUrl: String): Builder {
        this.callbackUrl = callbackUrl
        return this
    }

    fun setBusinessShortCode(businessShortCode: String): Builder {
        this.businessShortCode = businessShortCode
        return this
    }

    fun setEnvironment(environment: Environment): Builder {
        this.environment = environment
        return this
    }

    fun build(): Daraja {
        val daraja = Daraja
        daraja.consumerKey = consumerKey
        daraja.consumerSecret = consumerSecret
        daraja.businessShortCode = businessShortCode
        daraja.passKey = passKey
        daraja.transactionType = transactionType
        daraja.callbackUrl = callbackUrl
        daraja.baseUrl = environment.url

        daraja.repo = DarajaRepository(daraja.consumerKey, daraja.consumerSecret, daraja.baseUrl)

        return daraja
    }

}