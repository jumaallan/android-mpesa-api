/*
 * Copyright 2021 Juma Allan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.androidstudy.daraja.util

import android.util.Base64
import java.text.SimpleDateFormat
import java.util.*

/**
 * Contains General Settings used in the library.
 */
object Settings {

    /**
     * Connection timeout duration
      */
    const val CONNECT_TIMEOUT: Long = 60 * 1000

    /**
     * Connection Read timeout duration
     */
    const val READ_TIMEOUT: Long = 60 * 1000

    /**
     * Connection write timeout duration
     */
    const val WRITE_TIMEOUT: Long = 60 * 1000

    // TODO('Raise exception')
    /**
     * The MSISDN sending the funds
     *
     * @param [phoneNumber]
     * @return a formatted String
     */
    fun formatPhoneNumber(phoneNumber: String): String? {
        if (phoneNumber.isBlank()) return null
        if (phoneNumber.length < 11 && phoneNumber.startsWith("0")) {
            // here we can just remove the inline variable instead of the p. Like you did with the rest
            // String p = phoneNumber.replaceFirst("^0", "254");
            // return p
            return phoneNumber.replaceFirst("^0".toRegex(), "254")
        }
        return if (phoneNumber.length == 13 && phoneNumber.startsWith("+")) phoneNumber.replaceFirst("^+".toRegex(), "") else phoneNumber
    }

    /**
     * The Timestamp of the Transaction
     */
    fun generateTimestamp(): String = SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(Date())

    /**
     * The password for Encrypting the Request
     */
    fun generatePassword(businessShortCode: String, passKey: String, timeStamp: String): String {
        val password = "$businessShortCode$passKey$timeStamp"
        return Base64.encodeToString(password.toByteArray(), Base64.NO_WRAP)
    }
}