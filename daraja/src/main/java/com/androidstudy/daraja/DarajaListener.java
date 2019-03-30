package com.androidstudy.daraja;

import android.support.annotation.NonNull;

import com.androidstudy.daraja.callback.DarajaException;

public interface DarajaListener<Result> {
    void onResult(@NonNull Result result);

    void onError(DarajaException exception);
}
