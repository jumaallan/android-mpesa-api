package com.androidstudy.mpesa.di;

import com.androidstudy.mpesa.ui.PaymentActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract class ActivitiesModule {

    @ContributesAndroidInjector
    abstract PaymentActivity contributesPaymentActivity();


}
