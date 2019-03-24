package com.androidstudy.callback;

import android.support.annotation.NonNull;

import com.androidstudy.daraja.DarajaListener;
import com.androidstudy.daraja.R;
import com.androidstudy.daraja.model.AccessToken;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DarajaCallback<T> implements Callback<T>{

    final DarajaListener<T> listener;

    public DarajaCallback(final DarajaListener<T> listener) {
        this.listener = listener;
    }

    @Override
    public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {
        if (response.isSuccessful()) {
            T data = response.body();
            if (data != null) {
                listener.onResult(data);
                return;
            }
        }
        listener.onError(String.valueOf(R.string.authentication_failed));
    }

    @Override
    public void onFailure(@NonNull Call<T> call, @NonNull Throwable t) {
        listener.onError(String.valueOf(R.string.authentication_failed) + t.getLocalizedMessage());
    }
}
