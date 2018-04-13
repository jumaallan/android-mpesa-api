package com.twigafoods.daraja.util;

public class Settings {
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
}
