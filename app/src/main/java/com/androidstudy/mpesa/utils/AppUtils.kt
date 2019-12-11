package com.androidstudy.mpesa.utils

import android.content.Context
import android.preference.PreferenceManager
import java.util.*

object AppUtils {
    fun UUID(): String {
        return UUID.randomUUID().toString()
    }

    @JvmStatic
    val passKey: String
        get() = "bfb279f9aa9bdbcf158e97dd71a467cd2e0c893059b10f78e6b72ada1ed2c919"

    fun saveAccessToken(context: Context?, accessToken: String?) {
        val mSettings = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = mSettings.edit()
        val cal = Calendar.getInstance()
        cal.add(Calendar.HOUR, 1)
        val oneHourAfter = cal.time
        editor.putString("accessToken", accessToken)
        editor.putString("expiry_date", oneHourAfter.toString())
        editor.apply()
    }

    fun getAccessToken(context: Context?): String? {
        val mSettings = PreferenceManager.getDefaultSharedPreferences(context)
        return mSettings.getString("accessToken", "")
    }

    //TODO(check for if access token expired)
    fun expired(context: Context?): Boolean {
        val mSettings = PreferenceManager.getDefaultSharedPreferences(context)
        return true
    }
}