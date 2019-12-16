package com.androidstudy.daraja.model

data class PaymentResult(
        var MerchantRequestID: String,
        var CheckoutRequestID: String,
        var ResponseCode: String,
        var ResponseDescription: String,
        var CustomerMessage: String
)