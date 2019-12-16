package com.androidstudy.daraja.model

import com.androidstudy.daraja.constants.TransactionType

class LNMExpress {
    var businessShortCode: String
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