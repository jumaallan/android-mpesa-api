package com.androidstudy.mpesa.common;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

/**
 * This Activity is to be inherited by any activity to initiate the injection.
 */

@SuppressLint("Registered")
public class BaseActivity extends DaggerAppCompatActivity {

    @Inject
    public ViewModelProvider.Factory viewModelFactory;

    public <T extends ViewModel> T getViewModel(final Class<T> cls) {
        return ViewModelProviders.of(this, viewModelFactory).get(cls);
    }

}
