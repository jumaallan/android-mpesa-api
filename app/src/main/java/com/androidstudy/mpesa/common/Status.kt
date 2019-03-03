package com.androidstudy.mpesa.common

enum class Status {
    SUCCESS,
    ERROR,
    LOADING;

    val isLoading: Status
        get() = LOADING
}
