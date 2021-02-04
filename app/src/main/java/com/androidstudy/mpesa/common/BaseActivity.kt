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