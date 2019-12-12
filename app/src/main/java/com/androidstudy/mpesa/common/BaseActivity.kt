package com.androidstudy.mpesa.common

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.androidstudy.mpesa.ui.ProgressDialogFragment
import com.androidstudy.mpesa.ui.ProgressDialogFragment.Companion.newInstance
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

/**
 * This Activity is to be inherited by any activity to initiate the injection.
 */
@SuppressLint("Registered")
open class BaseActivity : DaggerAppCompatActivity() {


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    fun <T : ViewModel> getViewModel(cls: Class<T>): T = ViewModelProviders.of(this, viewModelFactory)[cls]


    private lateinit var progressDialog: ProgressDialogFragment
    fun showLoading(title: String = "This will only take a sec", message: String = "Loading") {
        progressDialog = newInstance(title, message)
        progressDialog.isCancelable = false
        progressDialog.show(supportFragmentManager, "progress")
    }

    fun stopShowingLoading() {
        progressDialog.dismiss()
    }
}