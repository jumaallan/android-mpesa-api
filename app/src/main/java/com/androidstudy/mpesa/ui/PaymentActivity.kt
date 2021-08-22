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
package com.androidstudy.mpesa.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.androidstudy.daraja.Daraja
import com.androidstudy.daraja.callback.DarajaException
import com.androidstudy.daraja.callback.DarajaListener
import com.androidstudy.daraja.callback.DarajaPaymentListener
import com.androidstudy.daraja.data.model.AccessToken
import com.androidstudy.daraja.data.model.PaymentResult
import com.androidstudy.daraja.util.Environment
import com.androidstudy.mpesa.R
import com.androidstudy.mpesa.utils.AppUtils
import com.androidstudy.mpesa.utils.Config
import kotlinx.android.synthetic.main.activity_payment.*
import kotlinx.android.synthetic.main.content_payment.*

class PaymentActivity : AppCompatActivity() {

    private lateinit var progressDialog: ProgressDialogFragment
    private lateinit var daraja: Daraja

    private val darajaListener = object : DarajaListener<AccessToken> {
        override fun onResult(result: AccessToken) {
            dismissProgressDialog()
            AppUtils.saveAccessToken(baseContext, result.access_token)
            bPay.setOnClickListener { pay() }
        }

        override fun onError(exception: DarajaException) {
            dismissProgressDialog()
            toast(exception.message ?: "An error occurred!")
            bPay.setOnClickListener { accessToken() }
        }
    }

    private val darajaPaymentListener = object : DarajaPaymentListener {
        override fun onPaymentRequestComplete(result: PaymentResult) {
            dismissProgressDialog()
            toast(result.ResponseDescription)
        }

        override fun onPaymentFailure(exception: DarajaException) {
            dismissProgressDialog()
            toast(exception.message ?: "Payment failed!")
        }

        override fun onNetworkFailure(exception: DarajaException) {
            dismissProgressDialog()
            toast(exception.message ?: "Network error!")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)
        setSupportActionBar(toolbar)

        title = "Payment"

        // initialize daraja
        daraja = getDaraja()

        accessToken()
    }

    private fun getDaraja(): Daraja {
        return Daraja.builder(Config.CONSUMER_KEY, Config.CONSUMER_SECRET)
            .setBusinessShortCode(Config.BUSINESS_SHORTCODE)
            .setPassKey(AppUtils.passKey)
            .setTransactionType(Config.ACCOUNT_TYPE)
            .setCallbackUrl(Config.CALLBACK_URL)
            .setEnvironment(Environment.SANDBOX)
            .build()
    }

    private fun pay() {
        val phoneNumber = etPhoneNumber.text.toString()
        val amountString = etAmount.text.toString()

        if (phoneNumber.isBlank() || amountString.isBlank()) {
            toast("You have left some fields blank")
            return
        }

        val amount = amountString.toInt()
        initiatePayment(phoneNumber, amount)
    }

    private fun initiatePayment(phoneNumber: String, amount: Int) {
        val token = AppUtils.getAccessToken(baseContext)
        if (token == null) {
            accessToken()
            toast("Your access token was refreshed. Retry again.")
        } else {
            // initiate payment
            showProgressDialog()
            daraja.initiatePayment(token, phoneNumber, amount.toString(), AppUtils.generateUUID(), "Payment", darajaPaymentListener)
        }
    }

    private fun accessToken() {
        // get access token
        showProgressDialog()
        daraja.getAccessToken(darajaListener)
    }

    private fun toast(text: String) = Toast.makeText(baseContext, text, Toast.LENGTH_LONG).show()

    private fun showProgressDialog(title: String = "This will only take a sec", message: String = "Loading") {
        progressDialog = ProgressDialogFragment.newInstance(title, message)
        progressDialog.isCancelable = false
        progressDialog.show(supportFragmentManager, "progress")
    }

    private fun dismissProgressDialog() {
        progressDialog.dismiss()
    }
}