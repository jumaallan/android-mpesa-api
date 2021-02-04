package com.androidstudy.daraja.model

import com.androidstudy.daraja.constants.TransactionType

class LNMExpress {

    private var BusinessShortCode: String
    private lateinit var PassKey: String
    private lateinit var Password: String
    private lateinit var Timestamp: String
    private lateinit var Type: TransactionType
    private var Amount: String
    private lateinit var TransactionType: String
    private var PartyA: String
    private var PartyB: String
    private var PhoneNumber: String
    private var CallBackURL: String
    private var AccountReference: String
    private var TransactionDesc: String

    constructor(businessShortCode: String, password: String, timestamp: String, amount: String, transactionType: String, partyA: String, partyB: String, phoneNumber: String, callBackURL: String, accountReference: String, transactionDesc: String) {
        BusinessShortCode = businessShortCode
        Password = password
        Timestamp = timestamp
        TransactionType = transactionType
        Amount = amount
        PartyA = partyA
        PartyB = partyB
        PhoneNumber = phoneNumber
        CallBackURL = callBackURL
        AccountReference = accountReference
        TransactionDesc = transactionDesc
    }

    constructor(businessShortCode: String, passKey: String, transactionType: TransactionType, amount: String, partyA: String, partyB: String, phoneNumber: String, callBackURL: String, accountReference: String, transactionDesc: String) {
        BusinessShortCode = businessShortCode
        PassKey = passKey
        Type = transactionType
        Amount = amount
        PartyA = partyA
        PartyB = partyB
        PhoneNumber = phoneNumber
        CallBackURL = callBackURL
        AccountReference = accountReference
        TransactionDesc = transactionDesc
    }

}