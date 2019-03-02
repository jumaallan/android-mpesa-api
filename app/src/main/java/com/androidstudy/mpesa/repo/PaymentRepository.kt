package com.androidstudy.mpesa.repo

import com.androidstudy.daraja.Daraja
import com.androidstudy.daraja.model.AccessToken
import com.androidstudy.daraja.model.LNMExpress
import com.androidstudy.daraja.model.LNMResult
import com.androidstudy.daraja.util.Env
import com.androidstudy.mpesa.Config
import com.androidstudy.mpesa.common.DarajaLiveData
import com.androidstudy.mpesa.utils.AppUtils

import javax.inject.Inject

class PaymentRepository @Inject
constructor() {

    private var daraja: Daraja = Daraja.with(
            Env.SANDBOX,
            Config.CONSUMER_KEY,
            Config.CONSUMER_SECRET,
            Config.CALLBACK_URL
    )


    val accessToken: DarajaLiveData<AccessToken>
        get() {
            val accessTokenLiveData = DarajaLiveData<AccessToken>()
            daraja.getToken(accessTokenLiveData)

            return accessTokenLiveData
        }

    fun pay(lnmExpress: LNMExpress): DarajaLiveData<LNMResult> {
        val listener = DarajaLiveData<LNMResult>()
        daraja.requestMPESAExpress(lnmExpress, listener)

        return listener
    }


    fun pay(phoneNumber: String, amount: Int, description: String): DarajaLiveData<LNMResult> {
        val listener = DarajaLiveData<LNMResult>()

        //change from lib
        val amountString = amount.toString() + ""

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

        daraja.requestMPESAExpress(lnmExpress, listener)

        return listener
    }


    fun buyGoods() {

    }

}
