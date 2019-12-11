package com.androidstudy.daraja.callback;


import androidx.annotation.NonNull;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DarajaCallback<T> implements Callback<T>{

    private final DarajaListener<T> listener;

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

        String code = response.code() + "";
        String error = "";
        try {
            error = code + " : "+response.errorBody().string();
        } catch (IOException e) {
            e.printStackTrace();
        }

        listener.onError(new DarajaException(error));
    }

    @Override
    public void onFailure(@NonNull Call<T> call, @NonNull Throwable t) {
        listener.onError(new DarajaException(t.getLocalizedMessage()));
    }
}
