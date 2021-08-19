package com.androidstudy.mpesa.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.androidstudy.mpesa.MainCoroutineRule
import com.androidstudy.mpesa.repo.FakePaymentRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule

/**
 * Created by Andronicus Kim on 8/19/21.
 */
class PaymentViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var repository: FakePaymentRepository
    private lateinit var viewModel: PaymentViewModel

    @Before
    fun setUp() {
        repository = FakePaymentRepository()
        viewModel = PaymentViewModel(repository)
    }
}