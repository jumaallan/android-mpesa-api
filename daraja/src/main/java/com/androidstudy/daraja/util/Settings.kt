package com.androidstudy.daraja.util

import android.util.Base64
import java.text.SimpleDateFormat
import java.util.*

object Settings {
    //Connection timeout duration
    const val CONNECT_TIMEOUT: Long = 60 * 1000

    //Connection Read timeout duration
    const val READ_TIMEOUT: Long = 60 * 1000

    //Connection write timeout duration
    const val WRITE_TIMEOUT: Long = 60 * 1000

    //TODO('Raise exception')
    //The MSISDN sending the funds
    fun formatPhoneNumber(phoneNumber: String): String? {
        if (phoneNumber.isBlank()) return null
        if (phoneNumber.length < 11 && phoneNumber.startsWith("0")) {
            // here we can just remove the inline variable instead of the p. Like you did with the rest
            //String p = phoneNumber.replaceFirst("^0", "254");
            //return p
            return phoneNumber.replaceFirst("^0".toRegex(), "254")
        }
        return if (phoneNumber.length == 13 && phoneNumber.startsWith("+")) phoneNumber.replaceFirst("^+".toRegex(), "") else phoneNumber
    }

    //The Timestamp of the Transaction
    fun generateTimestamp(): String {
        return SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(Date())
    }

    //The password for Encrypting the Request
    fun generatePassword(businessShortCode: String, passKey: String, timeStamp: String): String {
        val password = "$businessShortCode$passKey$timeStamp"
        return Base64.encodeToString(password.toByteArray(), Base64.NO_WRAP)
    }
}