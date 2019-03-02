package com.androidstudy.mpesa.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.androidstudy.daraja.model.AccessToken;
import com.androidstudy.daraja.model.LNMResult;
import com.androidstudy.mpesa.common.DarajaLiveData;
import com.androidstudy.mpesa.repo.PaymentRepository;

import javax.inject.Inject;

public class PaymentViewModel extends ViewModel {
    private final PaymentRepository paymentRepository;

    @Inject
    PaymentViewModel(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public DarajaLiveData<LNMResult> pay(String phone, int amount, String description) {
        return paymentRepository.pay(phone, amount, description);
    }

    public DarajaLiveData<AccessToken> accessToken() {
        return paymentRepository.getAccessToken();
    }

}