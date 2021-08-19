package com.androidstudy.mpesa.repo

import com.androidstudy.daraja.callback.DarajaException
import com.androidstudy.daraja.model.AccessToken
import com.androidstudy.daraja.model.PaymentResult
import com.androidstudy.mpesa.common.DarajaLiveData
import com.androidstudy.mpesa.common.DarajaPaymentLiveData

/**
 * Created by Andronicus Kim on 8/19/21.
 */
class FakePaymentRepository : Repository{

    //Flag to control network response
    private var shouldReturnNetworkError = false

    //Flag to control api response
    private var shouldReturnApiError = false

    fun setShouldReturnNetworkError(value: Boolean) {
        shouldReturnNetworkError = value
    }

    fun setShouldReturnApiError(value: Boolean){
        shouldReturnApiError = value
    }

    override val accessToken: DarajaLiveData<AccessToken>
        get() = DarajaLiveData<AccessToken>()

    override fun initiatePayment(token: String, phoneNumber: String, amount: Int, description: String): DarajaPaymentLiveData{
        val listener = DarajaPaymentLiveData()

        if (shouldReturnNetworkError){
            listener.onNetworkFailure(DarajaException(""))
        }else {
            if (shouldReturnApiError){
                listener.onPaymentFailure(DarajaException(""))
            }else{
                listener.onPaymentRequestComplete(PaymentResult("", "", "", "", ""))
            }
        }

        return listener
    }
}