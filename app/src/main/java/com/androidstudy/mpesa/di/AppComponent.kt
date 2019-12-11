package com.androidstudy.mpesa.di

import com.androidstudy.mpesa.MpesaExpressApp
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, ViewModelModule::class, ActivitiesModule::class, AppModule::class])
interface AppComponent : AndroidInjector<DaggerApplication> {
    fun inject(app: MpesaExpressApp)
}