/*
 * Copyright 2021 Juma Allan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.androidstudy.mpesa.di

import com.androidstudy.daraja.Daraja
import com.androidstudy.daraja.util.Environment
import com.androidstudy.mpesa.Config
import com.androidstudy.mpesa.MpesaExpressApp
import com.androidstudy.mpesa.utils.AppUtils.passKey
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    private lateinit var app: MpesaExpressApp

    @Provides
    @Singleton
    fun providesApplication(): MpesaExpressApp = app

    @Provides
    @Singleton
    fun providesDaraja(): Daraja =
        Daraja.builder(Config.CONSUMER_KEY, Config.CONSUMER_SECRET)
            .setBusinessShortCode(Config.BUSINESS_SHORTCODE)
            .setPassKey(passKey)
            .setTransactionType(Config.ACCOUNT_TYPE)
            .setCallbackUrl(Config.CALLBACK_URL)
            .setEnvironment(Environment.SANDBOX)
            .build()
}