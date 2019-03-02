package com.androidstudy.mpesa.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class AppUtils {


    public static String UUID() {
        return UUID.randomUUID().toString();
    }

    public static String getToken() {
        return "";
    }

    public void saveAccessToken(Context context, String accessToken){
        SharedPreferences mSettings = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = mSettings.edit();

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR, 1);
        Date oneHourAfter = cal.getTime();

        editor.putString("accessToken", accessToken);
        editor.putString("expiry_date", oneHourAfter.toString());

        editor.apply();
    }
}
