package com.androidstudy.daraja.callback

sealed class DarajaResult<out T> {
    data class Success<out T>(val value: T) : DarajaResult<T>()
    data class Failure(
        val isNetworkError: Boolean,
        val darajaException: DarajaException?
    ) : DarajaResult<Nothing>()
}
