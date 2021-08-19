package com.androidstudy.mpesa.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.androidstudy.mpesa.MainCoroutineRule
import com.androidstudy.mpesa.common.Status
import com.androidstudy.mpesa.getOrAwaitValueTest
import com.androidstudy.mpesa.repo.FakePaymentRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

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

    @Test
    fun `successful network call returns SUCCESS enum`(){
        val result = viewModel.initiatePayment("","",0,"").getOrAwaitValueTest()

        assertThat(result.status()).isEqualTo(Status.SUCCESS)
    }

    @Test
    fun `unsuccessful network call returns ERROR enum`(){
        //set network to return an error
        repository.setShouldReturnNetworkError(true)

        val result = viewModel.initiatePayment("","",0,"").getOrAwaitValueTest()

        assertThat(result.status()).isEqualTo(Status.ERROR)
    }
}