package com.androidstudy.mpesa;

import com.androidstudy.mpesa.di.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class MpesaExpressApp extends DaggerApplication {

    @Override
    public void onCreate() {
        super.onCreate();


    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.create();
    }
}
