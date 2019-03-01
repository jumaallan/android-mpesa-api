package com.androidstudy.mpesa.di;

import com.androidstudy.mpesa.MpesaExpressApp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    MpesaExpressApp app;

    void AppModule(MpesaExpressApp application) {
        app = application;
    }

    @Provides
    @Singleton
    MpesaExpressApp providesApplication() {
        return app;
    }

}
