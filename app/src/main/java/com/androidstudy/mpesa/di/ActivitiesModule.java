package com.androidstudy.mpesa.di;

import com.androidstudy.mpesa.ui.MPESAExpressActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract class ActivitiesModule {

    @ContributesAndroidInjector
    abstract MPESAExpressActivity contributesMPESAExpressActivity();


}
