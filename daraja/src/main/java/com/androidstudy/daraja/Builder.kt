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
package com.androidstudy.daraja

import com.androidstudy.daraja.util.TransactionType
import com.androidstudy.daraja.data.repo.DarajaRepository
import com.androidstudy.daraja.util.Environment

/**
 * Keys Builder. Creates a [Daraja] payment load.
 * @param [consumerKey] for your application
 * @param [consumerSecret] for your application
 */

class Builder(private var consumerKey: String, private var consumerSecret: String) {

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