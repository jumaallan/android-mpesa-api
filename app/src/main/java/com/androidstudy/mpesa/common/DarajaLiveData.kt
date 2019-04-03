package com.androidstudy.mpesa.common

import android.arch.lifecycle.LiveData

import com.androidstudy.daraja.callback.DarajaListener
import com.androidstudy.daraja.callback.DarajaException


class DarajaLiveData<T> : LiveData<Resource<T>>(), DarajaListener<T> {

    init {
        value = Resource(Status.LOADING)
    }

    override fun onResult(data: T) {
        value = Resource(data)
    }

    override fun onError(exception: DarajaException) {
        value = Resource(DarajaException(exception.localizedMessage))
    }
}
