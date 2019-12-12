package com.androidstudy.mpesa

import com.androidstudy.daraja.constants.TransactionType

object Config {
    @JvmField
    var CONSUMER_KEY = "Uku3wUhDw9z0Otdk2hUAbGZck8ZGILyh"
    @JvmField
    var CONSUMER_SECRET = "JDjpQBm5HpYwk38b"
    @JvmField
    var CALLBACK_URL = "http://mycallbackurl.com/checkout.php"
    @JvmField
    var BUSINESS_SHORTCODE = "174379"
    @JvmField
    var ACCOUNT_TYPE = TransactionType.CustomerBuyGoodsOnline
}