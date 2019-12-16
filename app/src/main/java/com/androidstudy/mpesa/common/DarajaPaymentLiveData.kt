package com.androidstudy.mpesa.common

import androidx.lifecycle.LiveData
import com.androidstudy.daraja.callback.DarajaException
import com.androidstudy.daraja.callback.DarajaPaymentListener
import com.androidstudy.daraja.model.PaymentResult


class DarajaPaymentLiveData : LiveData<Resource<PaymentResult>>(), DarajaPaymentListener {
    override fun onPaymentRequestComplete(result: PaymentResult) {
        value = Resource(result)
    }

    override fun onPaymentFailure(exception: DarajaException) {
        value = Resource(DarajaException(exception.message))
    }

    override fun onNetworkFailure(exception: DarajaException) {
        value = Resource(DarajaException(exception.message))
    }

    init {
        value = Resource(Status.LOADING)
    }
}
