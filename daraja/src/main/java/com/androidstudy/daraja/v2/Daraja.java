package com.androidstudy.daraja.v2;

import com.androidstudy.daraja.callback.DarajaCallback;
import com.androidstudy.daraja.DarajaListener;
import com.androidstudy.daraja.model.AccessToken;
import com.androidstudy.daraja.model.LNMExpress;
import com.androidstudy.daraja.model.LNMResult;
import com.androidstudy.daraja.network.ApiClient;
import com.androidstudy.daraja.network.URLs;
import com.androidstudy.daraja.util.Env;
import com.androidstudy.daraja.util.Settings;
import com.androidstudy.daraja.util.TransactionType;

public class Daraja {

    private String consumerKey;
    private String consumerSecret;
    private String businessShortCode;
    private String passKey;
    private TransactionType transactionType;
    private String callbackUrl;
    private String baseUrl;

    public static class Builder {
        private String consumerKey;
        private String consumerSecret;
        private String businessShortCode;
        private String passKey;
        private TransactionType transactionType;
        private String callbackUrl;
        private Env environment;

        public Builder(String consumerKey, String consumerSecret) {
            this.consumerKey = consumerKey;
            this.consumerSecret = consumerSecret;
        }

        public Builder setPassKey(String passKey) {
            this.passKey = passKey;

            return this;
        }

        public Builder setTransactionType(TransactionType transactionType) {
            this.transactionType = transactionType;

            return this;
        }

        public Builder setCallbackUrl(String callbackUrl) {
            this.callbackUrl = callbackUrl;

            return this;
        }

        public Builder setBusinessShortCode(String businessShortCode) {
            this.businessShortCode = businessShortCode;

            return this;
        }

        public Builder setEnvironment(Env environment) {
            this.environment = environment;

            return this;
        }

        public Daraja build() {
            Daraja daraja = new Daraja();
            daraja.consumerKey = this.consumerKey;
            daraja.consumerSecret = this.consumerSecret;
            daraja.businessShortCode = this.businessShortCode;
            daraja.passKey = this.passKey;
            daraja.transactionType = this.transactionType;
            daraja.callbackUrl = this.callbackUrl;
            daraja.baseUrl = (this.environment == Env.SANDBOX) ? URLs.SANDBOX_BASE_URL : URLs.PRODUCTION_BASE_URL;

            return daraja;
        }
    }


    private Daraja() {

    }

    public DarajaListener<AccessToken> getAccessToken(final DarajaListener<AccessToken> listener) {
        ApiClient
                .getAuthAPI(consumerKey, consumerSecret, baseUrl)
                .getAccessToken()
                .enqueue(new DarajaCallback(listener));

        return listener;
    }

    public DarajaListener<LNMResult> makePaymentRequest(
            String token,
            String phoneNumber,
            String amount,
            String accountReference,
            String description,
            final DarajaListener<LNMResult> listener) {

        String sanitizedPhoneNumber = Settings.formatPhoneNumber(phoneNumber);
        String timeStamp = Settings.generateTimestamp();
        String generatedPassword = Settings.generatePassword(businessShortCode, passKey, timeStamp);

        LNMExpress express = new LNMExpress(
                businessShortCode,
                generatedPassword,
                timeStamp,
                amount,
                transactionType.toString(),
                phoneNumber,
                businessShortCode,
                sanitizedPhoneNumber,
                callbackUrl,
                accountReference,
                description
        );

        ApiClient
                .getAPI(baseUrl, token)
                .getLNMPesa(express)
                .enqueue(new DarajaCallback(listener));

        return listener;
    }
}
