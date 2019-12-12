package com.androidstudy.mpesa.di

import com.androidstudy.mpesa.ui.PaymentActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class ActivitiesModule {
    @ContributesAndroidInjector
    abstract fun contributesPaymentActivity(): PaymentActivity?
}