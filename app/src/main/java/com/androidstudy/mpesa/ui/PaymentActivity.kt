package com.androidstudy.mpesa.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil.setContentView
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
        if (token != null) viewModel.initiatePayment(token, phoneNumber, amount, "Payment").observe(this, Observer { response ->
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
        })
    }

    private fun toast(text: String) = Toast.makeText(baseContext, text, Toast.LENGTH_LONG).show()

    private fun accessToken() {
        viewModel.accessToken().observe(this, Observer { response ->
            response?.let {
                Log.e("oly", response.status().name)

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
        })
    }

}