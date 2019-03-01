package com.androidstudy.mpesa.repo;

import com.androidstudy.daraja.Daraja;
import com.androidstudy.daraja.model.AccessToken;
import com.androidstudy.daraja.model.LNMExpress;
import com.androidstudy.daraja.util.Env;
import com.androidstudy.daraja.util.TransactionType;
import com.androidstudy.mpesa.Config;
import com.androidstudy.mpesa.common.DarajaLiveData;

public class PaymentRepository {

    Daraja daraja;

    public PaymentRepository() {
        daraja = Daraja.with(
                Env.SANDBOX,
                Config.CONSUMER_KEY,
                Config.CONSUMER_SECRET,
                Config.CALLBACK_URL
        );

    }

    public void payBill(String amount, String accessToken, String phoneNumber, String accountReference, String description){
        LNMExpress lnmExpress = new LNMExpress(
                Config.BUSINESS_SHORTCODE,
                accessToken,
                TransactionType.CustomerBuyGoodsOnline,
                amount,
                "254708374149",
                Config.BUSINESS_SHORTCODE,
                phoneNumber,
                Config.CALLBACK_URL,
                accountReference,
                description
        );
    }


    public DarajaLiveData<AccessToken> getAccessToken(){
        DarajaLiveData<AccessToken> accessTokenLiveData = new DarajaLiveData<>();
        daraja.getToken(accessTokenLiveData);

        return accessTokenLiveData;
    }

    public void buyGoods(){

    }

}
