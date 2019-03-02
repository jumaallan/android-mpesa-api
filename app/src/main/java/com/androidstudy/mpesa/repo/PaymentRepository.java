package com.androidstudy.mpesa.repo;

import android.content.Context;

import com.androidstudy.daraja.Daraja;
import com.androidstudy.daraja.model.AccessToken;
import com.androidstudy.daraja.model.LNMExpress;
import com.androidstudy.daraja.model.LNMResult;
import com.androidstudy.daraja.util.Env;
import com.androidstudy.daraja.util.TransactionType;
import com.androidstudy.mpesa.Config;
import com.androidstudy.mpesa.common.DarajaLiveData;
import com.androidstudy.mpesa.utils.AppUtils;

import javax.inject.Inject;

public class PaymentRepository {

    Daraja daraja;

    @Inject
    public PaymentRepository() {
        daraja = Daraja.with(
                Env.SANDBOX,
                Config.CONSUMER_KEY,
                Config.CONSUMER_SECRET,
                Config.CALLBACK_URL
        );

    }

    public DarajaLiveData<LNMResult> pay(LNMExpress lnmExpress){
        DarajaLiveData<LNMResult> listener = new DarajaLiveData<>();
        daraja.requestMPESAExpress(lnmExpress, listener);

        return listener;
    }


    public DarajaLiveData<LNMResult> pay(String phoneNumber, int amount, String description){
        DarajaLiveData<LNMResult> listener = new DarajaLiveData<>();

        //change from lib
        String amount_string = amount + "";

        LNMExpress lnmExpress = new LNMExpress(
                Config.BUSINESS_SHORTCODE,
                AppUtils.getPassKey(),
                Config.ACCOUNT_TYPE,
                amount_string,
                phoneNumber,
                Config.BUSINESS_SHORTCODE,
                phoneNumber,
                Config.CALLBACK_URL,
                AppUtils.UUID(),
                description
        );

        daraja.requestMPESAExpress(lnmExpress, listener);

        return listener;
    }


    public DarajaLiveData<AccessToken> getAccessToken(){
        DarajaLiveData<AccessToken> accessTokenLiveData = new DarajaLiveData<>();
        daraja.getToken(accessTokenLiveData);

        return accessTokenLiveData;
    }



    public void buyGoods(){

    }

}
