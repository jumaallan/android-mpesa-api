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
import androidx.lifecycle.Observer
import com.androidstudy.mpesa.R
import com.androidstudy.mpesa.common.BaseActivity
import com.androidstudy.mpesa.common.Status
import com.androidstudy.mpesa.utils.AppUtils
import com.androidstudy.mpesa.viewmodel.PaymentViewModel
import kotlinx.android.synthetic.main.activity_payment.*
import kotlinx.android.synthetic.main.content_payment.*

class PaymentActivity : BaseActivity() {

    private lateinit var viewModel: PaymentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)
        setSupportActionBar(toolbar)

        title = "Payment"

        viewModel = getViewModel(PaymentViewModel::class.java)

        accessToken()
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
            viewModel.initiatePayment(token, phoneNumber, amount, "Payment").observe(
                this,
                Observer { response ->
                    response?.let {
                        when (response.status()) {
                            Status.LOADING -> showLoading()

                            Status.SUCCESS -> {
                                stopShowingLoading()
                                toast(response.data()!!.ResponseDescription)
                            }

                            Status.ERROR -> {
                                stopShowingLoading()
                                toast(response.error()?.message!!)
                            }
                        }
                    }
                }
            )
        }
    }

    private fun toast(text: String) = Toast.makeText(baseContext, text, Toast.LENGTH_LONG).show()

    private fun accessToken() {
        viewModel.accessToken().observe(
            this,
            Observer { response ->
                response?.let {
                    when (response.status()) {
                        Status.LOADING -> showLoading()

                        Status.SUCCESS -> {
                            stopShowingLoading()
                            AppUtils.saveAccessToken(baseContext, response.data()!!.access_token)
                            bPay.setOnClickListener { pay() }
                        }

                        Status.ERROR -> {
                            stopShowingLoading()
                            toast("error" + response.error()?.message)
                            bPay.setOnClickListener { accessToken() }
                        }
                    }
                }
            }
        )
    }
}