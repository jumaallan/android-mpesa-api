package com.androidstudy.mpesa.repo


import com.androidstudy.daraja.model.AccessToken
import com.androidstudy.daraja.model.LNMResult
import com.androidstudy.daraja.v2.Daraja
import com.androidstudy.mpesa.common.DarajaLiveData
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

    fun pay(token : String, phoneNumber: String, amount: Int, description: String): DarajaLiveData<LNMResult> {
        val listener = DarajaLiveData<LNMResult>()

        daraja.makePaymentRequest(
                token,
                phoneNumber,
                amount.toString(),
                AppUtils.UUID(),
                description,
                listener
        )

        return listener
    }

}
