package com.androidstudy.mpesa.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.androidstudy.mpesa.utils.ViewModelFactory
import com.androidstudy.mpesa.viewmodel.PaymentViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(PaymentViewModel::class)
    abstract fun bindPaymentViewModel(viewModel: PaymentViewModel?): ViewModel?

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory?): ViewModelProvider.Factory?
}