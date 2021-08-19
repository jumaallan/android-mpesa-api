package com.androidstudy.mpesa.di

import com.androidstudy.mpesa.repo.PaymentRepository
import com.androidstudy.mpesa.repo.Repository
import dagger.Binds
import dagger.Module

/**
 * Created by Andronicus Kim on 8/20/21.
 */
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindRepository(paymentRepository: PaymentRepository) : Repository
}