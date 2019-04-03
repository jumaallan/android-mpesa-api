package com.androidstudy.mpesa.common

import android.arch.lifecycle.LiveData

import com.androidstudy.daraja.callback.DarajaException
import com.androidstudy.daraja.callback.DarajaPaymentListener
import com.androidstudy.daraja.model.PaymentResult


class DarajaPaymentLiveData : LiveData<Resource<PaymentResult>>(), DarajaPaymentListener {
    override fun onPaymentRequestComplete(result: PaymentResult) {
        value = Resource(result)
    }


    override fun onPaymentCancelled() {
        value = Resource(DarajaException("Payment was cancelled"))
    }

    override fun onPaymentFailure(exception: DarajaException) {
        value = Resource(DarajaException(exception.localizedMessage))
    }

    override fun onNetworkFailure(exception: DarajaException?) {
        value = Resource(DarajaException(exception!!.localizedMessage))
    }

    init {
        value = Resource(Status.LOADING)
    }
}
