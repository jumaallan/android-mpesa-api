package com.androidstudy.daraja;

import com.androidstudy.daraja.callback.DarajaCallback;
import com.androidstudy.daraja.callback.DarajaListener;
import com.androidstudy.daraja.callback.DarajaPaymentCallback;
import com.androidstudy.daraja.callback.DarajaPaymentListener;
import com.androidstudy.daraja.model.AccessToken;
import com.androidstudy.daraja.repo.DarajaRepository;
import com.androidstudy.daraja.constants.TransactionType;

public class Daraja {

    String consumerKey;
    String consumerSecret;
    String businessShortCode;
    String passKey;
    TransactionType transactionType;
    String callbackUrl;
    String baseUrl;

    DarajaRepository repo;

    public static Builder Builder(String consumerKey, String consumerSecret){
        return new Builder(consumerKey, consumerSecret);
    }

    protected Daraja() {
    }

    public DarajaListener<AccessToken> getAccessToken(final DarajaListener<AccessToken> listener) {
        repo.getAccessToken().enqueue(new DarajaCallback(listener));
        return listener;
    }

    public DarajaPaymentListener initiatePayment(String token, String phoneNumber, String amount,
            String accountReference, String description, final DarajaPaymentListener listener) {

      repo.initiatePayment(token, phoneNumber, amount, accountReference, description,
              businessShortCode, passKey, transactionType, callbackUrl)
                .enqueue(new DarajaPaymentCallback(listener));

        return listener;
    }
}
