package com.androidstudy.daraja.callback

import com.androidstudy.daraja.model.ErrorResponse
import com.androidstudy.daraja.model.PaymentResult
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class DarajaPaymentCallback : Callback<PaymentResult> {

    lateinit var listener: DarajaPaymentListener

    constructor    (listener: DarajaPaymentListener) {
        this.listener = listener
    }

    override fun onResponse(call: Call<PaymentResult>, response: Response<PaymentResult>) {
        if (response.isSuccessful) {
            val result: PaymentResult? = response.body()
            if (result != null) {
                if (result.ResponseCode == "0") {
                    listener.onPaymentRequestComplete(result)
                } else {
                    val error = "${result.ResponseCode} : ${result.ResponseDescription}"
                    listener.onPaymentFailure(DarajaException(error))
                }
                return
            }
        } else {
            try {
                val gson = GsonBuilder().create()
                val error = gson.fromJson(response.errorBody()?.string(), ErrorResponse::class.java)
                listener.onPaymentFailure(DarajaException(error))
            } catch (e: IOException) {
                e.printStackTrace()
                listener.onPaymentFailure(DarajaException("${response.code()}"))
            }
        }
    }

    override fun onFailure(call: Call<PaymentResult?>, t: Throwable) {
        listener.onNetworkFailure(DarajaException(t.localizedMessage))
    }
}