package com.androidstudy.mpesa.repo


import com.androidstudy.daraja.model.AccessToken
import com.androidstudy.daraja.model.LNMExpress
import com.androidstudy.daraja.model.LNMResult
import com.androidstudy.daraja.util.Env
import com.androidstudy.daraja.v2.Daraja
import com.androidstudy.mpesa.Config
import com.androidstudy.mpesa.common.DarajaLiveData
import com.androidstudy.mpesa.utils.AppUtils

import javax.inject.Inject

class PaymentRepository @Inject
constructor() {

    private var daraja: Daraja = Daraja.Builder(Config.CONSUMER_KEY, Config.CONSUMER_SECRET)
            .setBusinessShortCode(Config.BUSINESS_SHORTCODE)
            .setPassKey(AppUtils.getPassKey())
            .setTransactionType(Config.ACCOUNT_TYPE)
            .setCallbackUrl(Config.CALLBACK_URL)
            .setEnvironment(Env.SANDBOX)
            .build()


    val accessToken: DarajaLiveData<AccessToken>
        get() {
            val accessTokenLiveData = DarajaLiveData<AccessToken>()
            daraja.getAccessToken(accessTokenLiveData)

            return accessTokenLiveData
        }

    fun pay(token : String, phoneNumber: String, amount: Int, description: String): DarajaLiveData<LNMResult> {
        val listener = DarajaLiveData<LNMResult>()

        //TODO(Change from the lib)
        val amountString = amount.toString() + ""

        //TODO(Streamline this)
        val lnmExpress = LNMExpress(
                Config.BUSINESS_SHORTCODE,
                AppUtils.getPassKey(),
                Config.ACCOUNT_TYPE,
                amountString,
                phoneNumber,
                Config.BUSINESS_SHORTCODE,
                phoneNumber,
                Config.CALLBACK_URL,
                AppUtils.UUID(),
                description
        )

        daraja.makePaymentRequest(
                token,
                phoneNumber,
                amountString,
                AppUtils.UUID(),
                description,
                listener
        )

        return listener
    }

}
