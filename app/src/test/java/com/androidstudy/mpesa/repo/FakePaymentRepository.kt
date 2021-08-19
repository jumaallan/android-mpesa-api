package com.androidstudy.mpesa.repo

import com.androidstudy.daraja.model.AccessToken
import com.androidstudy.mpesa.common.DarajaLiveData
import com.androidstudy.mpesa.common.DarajaPaymentLiveData

/**
 * Created by Andronicus Kim on 8/19/21.
 */
class FakePaymentRepository : Repository{

    //Flag to control network response
    private var shouldReturnNetworkError = false

    fun setShouldReturnNetworkError(value: Boolean) {
        shouldReturnNetworkError = value
    }

    override val accessToken: DarajaLiveData<AccessToken>
        get() = DarajaLiveData<AccessToken>()

    override fun initiatePayment(token: String, phoneNumber: String, amount: Int, description: String): DarajaPaymentLiveData{
        return DarajaPaymentLiveData()
    }
}