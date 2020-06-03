package com.androidstudy.daraja.callback

import com.androidstudy.daraja.model.PaymentResult

interface DarajaPaymentListener {
    fun onPaymentRequestComplete(result: PaymentResult)
    fun onPaymentFailure(exception: DarajaException)
    fun onNetworkFailure(exception: DarajaException)
}