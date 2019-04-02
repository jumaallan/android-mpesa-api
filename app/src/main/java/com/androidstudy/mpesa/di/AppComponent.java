package com.androidstudy.mpesa.di;

import com.androidstudy.mpesa.MpesaExpressApp;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        ViewModelModule.class,
        ActivitiesModule.class,
        AppModule.class
})
interface AppComponent extends AndroidInjector<DaggerApplication> {

    void inject(MpesaExpressApp app);

}
