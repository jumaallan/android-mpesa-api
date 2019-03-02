package com.androidstudy.mpesa.common;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.androidstudy.daraja.DarajaListener;


public class DarajaLiveData<T> extends LiveData<Resource<T>> implements DarajaListener<T> {

    public DarajaLiveData() {
        setValue(new Resource<>(Status.LOADING));
    }

    @Override
    public void onResult(@NonNull T data) {
        setValue(new Resource<T>(data));
    }

    @Override
    public void onError(String error) {
        setValue(new Resource<T>(new DarajaException(error)));
    }
}
