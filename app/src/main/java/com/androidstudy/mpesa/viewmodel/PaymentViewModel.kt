package com.androidstudy.mpesa.viewmodel

import android.arch.lifecycle.ViewModel

import com.androidstudy.daraja.model.AccessToken
import com.androidstudy.mpesa.common.DarajaLiveData
import com.androidstudy.mpesa.common.DarajaPaymentLiveData
import com.androidstudy.mpesa.repo.PaymentRepository

import javax.inject.Inject

class PaymentViewModel @Inject
internal constructor(private val paymentRepository: PaymentRepository) : ViewModel() {

    fun initiatePayment(token : String, phone: String, amount: Int, description: String): DarajaPaymentLiveData {
        return paymentRepository.initiatePayment(token, phone, amount, description)
    }

    fun accessToken(): DarajaLiveData<AccessToken> {
        return paymentRepository.accessToken
    }

}