/*
 * Copyright 2021 Juma Allan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.androidstudy.mpesa.repo

import com.androidstudy.daraja.Daraja
import com.androidstudy.daraja.model.AccessToken
import com.androidstudy.mpesa.common.DarajaLiveData
import com.androidstudy.mpesa.common.DarajaPaymentLiveData
import com.androidstudy.mpesa.utils.AppUtils
import javax.inject.Inject

class PaymentRepository @Inject constructor() : Repository{

    @Inject
    lateinit var daraja: Daraja

    override val accessToken: DarajaLiveData<AccessToken>
        get() {
            val accessTokenLiveData = DarajaLiveData<AccessToken>()
            daraja.getAccessToken(accessTokenLiveData)

            return accessTokenLiveData
        }


    override fun initiatePayment(token: String, phoneNumber: String, amount: Int, description: String): DarajaPaymentLiveData {
        val listener = DarajaPaymentLiveData()

        daraja.initiatePayment(
            token = token,
            phoneNumber = phoneNumber,
            amount = amount.toString(),
            accountReference = AppUtils.generateUUID(),
            description = description,
            listener = listener
        )

        return listener
    }
}