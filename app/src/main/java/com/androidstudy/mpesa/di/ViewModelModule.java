package com.androidstudy.mpesa.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.androidstudy.mpesa.utils.ViewModelFactory;
import com.androidstudy.mpesa.viewmodel.PaymentViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@SuppressWarnings("WeakerAccess")
@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(PaymentViewModel.class)
    abstract ViewModel bindPaymentViewModel(PaymentViewModel viewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);
}
