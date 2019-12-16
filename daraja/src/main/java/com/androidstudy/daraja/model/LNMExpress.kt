package com.androidstudy.daraja.model

import com.androidstudy.daraja.constants.TransactionType

class LNMExpress {
    lateinit var businessShortCode: String
    lateinit var passKey: String
    lateinit var password: String
    lateinit var timestamp: String
    lateinit var type: TransactionType
    lateinit var amount: String
    private lateinit var transactionType: String
    lateinit var partyA: String
    lateinit var partyB: String
    lateinit var phoneNumber: String
    lateinit var callBackURL: String
    lateinit var accountReference: String
    lateinit var transactionDesc: String

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
        this.type = transactionType
        this.amount = amount
        this.partyA = partyA
        this.partyB = partyB
        this.phoneNumber = phoneNumber
        this.callBackURL = callBackURL
        this.accountReference = accountReference
        this.transactionDesc = transactionDesc
    }

}