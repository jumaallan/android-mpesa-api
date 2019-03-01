package com.androidstudy.mpesa.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.androidstudy.mpesa.repo.PaymentRepository;

import java.util.List;

import javax.inject.Inject;

public class PaymentViewModel extends ViewModel {
    private final PaymentRepository paymentRepository;

    @Inject
    PaymentViewModel(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

}