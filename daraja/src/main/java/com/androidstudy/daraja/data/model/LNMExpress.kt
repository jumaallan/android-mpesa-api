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
package com.androidstudy.daraja.data.model

import com.androidstudy.daraja.util.TransactionType

class LNMExpress {

    private var businessShortCode: String
    private lateinit var passKey: String
    private lateinit var password: String
    private lateinit var timestamp: String
    private lateinit var type: TransactionType
    private var amount: String
    private lateinit var transactionType: String
    private var partyA: String
    private var partyB: String
    private var phoneNumber: String
    private var callBackURL: String
    private var accountReference: String
    private var transactionDesc: String

    constructor(businessShortCode: String, password: String, timestamp: String, amount: String, transactionType: String, partyA: String, partyB: String, phoneNumber: String, callBackURL: String, accountReference: String, transactionDesc: String) {
        this.businessShortCode = businessShortCode
        this.password = password
        this.timestamp = timestamp
        this.transactionType = transactionType
        this.amount = amount
        this.partyA = partyA
        this.partyB = partyB
        this.phoneNumber = phoneNumber
        this.callBackURL = callBackURL
        this.accountReference = accountReference
        this.transactionDesc = transactionDesc
    }

    constructor(businessShortCode: String, passKey: String, transactionType: TransactionType, amount: String, partyA: String, partyB: String, phoneNumber: String, callBackURL: String, accountReference: String, transactionDesc: String) {
        this.businessShortCode = businessShortCode
        this.passKey = passKey
        type = transactionType
        this.amount = amount
        this.partyA = partyA
        this.partyB = partyB
        this.phoneNumber = phoneNumber
        this.callBackURL = callBackURL
        this.accountReference = accountReference
        this.transactionDesc = transactionDesc
    }
}