package com.twigafoods.daraja.util;

import android.util.Base64;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Settings {


    //The MSISDN sending the funds
    public static String formatPhoneNumber(String phoneNumber) {
        if (phoneNumber.equals("")) {
            return "";
        }
        if (phoneNumber.length() < 11 & phoneNumber.startsWith("0")) {
            String p = phoneNumber.replaceFirst("^0", "254");
            return p;
        }
        if (phoneNumber.length() == 13 && phoneNumber.startsWith("+")) {
            return phoneNumber.replaceFirst("^+", "");
        }
        return phoneNumber;
    }

    //The Timestamp of the Transaction
    public static String generateTimestamp() {
        return new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(new Date());
    }

    //The password for Encrypting the Request
    public static String generatePassword(String businessShortCode, String passkey, String timestamp) {
        String password = businessShortCode + passkey + timestamp;
        return Base64.encodeToString(password.getBytes(), Base64.NO_WRAP);
    }
}
