package com.androidstudy.mpesa;

import com.androidstudy.daraja.util.TransactionType;

public class Config {

    public static String CONSUMER_KEY = "Uku3wUhDw9z0Otdk2hUAbGZck8ZGILyh";
    public static String CONSUMER_SECRET = "JDjpQBm5HpYwk38b";
    public static String CALLBACK_URL = "https://us-central1-api-project-204600793177.cloudfunctions.net/paymentNotification";

    public static String BUSINESS_SHORTCODE = "174379";

    public static TransactionType ACCOUNT_TYPE = TransactionType.CustomerBuyGoodsOnline;
}
