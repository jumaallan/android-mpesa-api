package com.androidstudy.daraja.callback;


import androidx.annotation.NonNull;

public interface DarajaListener<Result> {
    void onResult(@NonNull Result result);

    void onError(DarajaException exception);
}
