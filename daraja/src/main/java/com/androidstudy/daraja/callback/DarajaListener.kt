package com.androidstudy.daraja.callback

interface DarajaListener<Result> {
    fun onResult(result: Result)
    fun onError(exception: DarajaException)
}