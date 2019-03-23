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

    public static String getPassKey() {
        return "bfb279f9aa9bdbcf158e97dd71a467cd2e0c893059b10f78e6b72ada1ed2c919";
    }

    public static void saveAccessToken(Context context, String accessToken){
        SharedPreferences mSettings = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = mSettings.edit();

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR, 1);
        Date oneHourAfter = cal.getTime();

        editor.putString("accessToken", accessToken);
        editor.putString("expiry_date", oneHourAfter.toString());

        editor.apply();
    }

    public static String getAccessToken(Context context){
        SharedPreferences mSettings = PreferenceManager.getDefaultSharedPreferences(context);

        return mSettings.getString("accessToken", "");
    }

    //TODO(check for if access token expired)
    public static boolean expired(Context context){
        SharedPreferences mSettings = PreferenceManager.getDefaultSharedPreferences(context);

        return true;
    }




}
