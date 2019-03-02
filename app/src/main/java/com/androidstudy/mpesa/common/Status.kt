package com.androidstudy.mpesa.common

enum class Status {
    EMPTY,
    SUCCESS,
    ERROR,
    LOADING;

    val isLoading: Status
        get() = LOADING
}
