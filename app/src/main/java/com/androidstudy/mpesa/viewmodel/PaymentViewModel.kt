package com.androidstudy.mpesa.viewmodel

import android.arch.lifecycle.ViewModel

import com.androidstudy.daraja.model.AccessToken
import com.androidstudy.daraja.model.LNMResult
import com.androidstudy.mpesa.common.DarajaLiveData
import com.androidstudy.mpesa.repo.PaymentRepository

import javax.inject.Inject

class PaymentViewModel @Inject
internal constructor(private val paymentRepository: PaymentRepository) : ViewModel() {

    fun pay(token : String, phone: String, amount: Int, description: String): DarajaLiveData<LNMResult> {
        return paymentRepository.pay(token, phone, amount, description)
    }

    fun accessToken(): DarajaLiveData<AccessToken> {
        return paymentRepository.accessToken
    }

}