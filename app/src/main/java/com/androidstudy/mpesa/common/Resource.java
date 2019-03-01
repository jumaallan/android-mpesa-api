package com.androidstudy.mpesa.common;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;


@SuppressWarnings({"WeakerAccess", "ConstantConditions"})
public final class Resource<T> {
    @Nullable
    private final T data;
    @Nullable
    private final Exception error;
    Status status = Status.LOADING;

    public Resource(@NonNull T data) {
        this(data, null);
    }

    public Resource(@NonNull Status status) {
        this(null, null);
        this.status = status;
    }

    public Resource(@NonNull Exception exception) {
        this(null, exception);
        this.status = Status.ERROR;
    }

    private Resource(@Nullable T value, @Nullable Exception error) {
        this.data = value;
        this.error = error;

        if (error != null){
            status = Status.ERROR;
        }else if (data != null){
            if (data instanceof List){
                if (((List) data).size() == 0){
                    status = Status.EMPTY;
                }else {
                    status = status.SUCCESS;
                }
            }else {
                status = Status.SUCCESS;
            }
        }else {
            status = Status.LOADING;
        }
    }

    public boolean isSuccessful() {
        return data != null && error == null;
    }

    @NonNull
    public T data() {
        if (error != null) {
            throw new IllegalStateException("error is not null. Call isSuccessful() first.");
        }
        return data;
    }

    @NonNull
    public Exception error() {
        if (data != null) {
            throw new IllegalStateException("data is not null. Call isSuccessful() first.");
        }
        return error;
    }

    @NonNull
    public Status status() {
        return status;
    }


}
