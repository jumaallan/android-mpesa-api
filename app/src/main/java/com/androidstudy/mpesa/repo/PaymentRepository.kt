package com.androidstudy.mpesa.repo


import com.androidstudy.daraja.Daraja
import com.androidstudy.daraja.model.AccessToken
import com.androidstudy.mpesa.common.DarajaLiveData
import com.androidstudy.mpesa.common.DarajaPaymentLiveData
import com.androidstudy.mpesa.utils.AppUtils
import javax.inject.Inject

class PaymentRepository @Inject
constructor() {

    @Inject lateinit var daraja : Daraja

    val accessToken: DarajaLiveData<AccessToken>
        get() {
            val accessTokenLiveData = DarajaLiveData<AccessToken>()
            daraja.getAccessToken(accessTokenLiveData)

            return accessTokenLiveData
        }

    fun initiatePayment(token : String, phoneNumber: String, amount: Int, description: String): DarajaPaymentLiveData {
        val listener = DarajaPaymentLiveData()

        daraja.initiatePayment(token, phoneNumber, amount.toString(), AppUtils.UUID(), description, listener)

        return listener
    }

}
