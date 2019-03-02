package com.androidstudy.mpesa.common

import android.arch.lifecycle.LiveData

import com.androidstudy.daraja.DarajaListener


class DarajaLiveData<T> : LiveData<Resource<T>>(), DarajaListener<T> {
    init {
        value = Resource(Status.LOADING)
    }

    override fun onResult(data: T) {
        value = Resource(data)
    }

    override fun onError(error: String) {
        value = Resource(DarajaException(error))
    }
}
