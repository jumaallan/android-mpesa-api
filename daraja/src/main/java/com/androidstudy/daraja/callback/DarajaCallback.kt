package com.androidstudy.daraja.callback

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class DarajaCallback<T>(private val listener: DarajaListener<T>) : Callback<T> {

    override fun onResponse(call: Call<T>, response: Response<T>) {
        if (response.isSuccessful) {
            val data: T? = response.body()
            if (data != null) {
                listener.onResult(data); return
            }
        }

        val code = "${response.code()}"
        var error = ""
        try {
            error = "$code : ${response.errorBody()!!.string()}"
        } catch (e: IOException) {
            e.printStackTrace()
        }

        listener.onError(DarajaException(error))
    }

    override fun onFailure(call: Call<T>, t: Throwable) {
        listener.onError(DarajaException(t.localizedMessage))
    }

}