package com.androidstudy.mpesa.repo

import com.androidstudy.daraja.model.AccessToken
import com.androidstudy.mpesa.common.DarajaLiveData
import com.androidstudy.mpesa.common.DarajaPaymentLiveData

/**
 * Created by Andronicus Kim on 8/20/21.
 */
interface Repository {
    val accessToken: DarajaLiveData<AccessToken>

    fun initiatePayment(token: String, phoneNumber: String, amount: Int, description: String): DarajaPaymentLiveData
}