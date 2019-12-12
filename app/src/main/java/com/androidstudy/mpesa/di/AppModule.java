package com.androidstudy.mpesa.di;

import com.androidstudy.daraja.Daraja;
import com.androidstudy.daraja.util.Environment;
import com.androidstudy.mpesa.Config;
import com.androidstudy.mpesa.MpesaExpressApp;
import com.androidstudy.mpesa.utils.AppUtils;

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

    @Provides
    @Singleton
    Daraja providesDaraja() {
        return Daraja.Builder(Config.CONSUMER_KEY, Config.CONSUMER_SECRET)
                .setBusinessShortCode(Config.BUSINESS_SHORTCODE)
                .setPassKey(AppUtils.getPassKey())
                .setTransactionType(Config.ACCOUNT_TYPE)
                .setCallbackUrl(Config.CALLBACK_URL)
                .setEnvironment(Environment.SANDBOX)
                .build();
    }

}
