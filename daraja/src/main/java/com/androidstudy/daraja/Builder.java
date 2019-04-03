package com.androidstudy.daraja;

import com.androidstudy.daraja.network.URLs;
import com.androidstudy.daraja.repo.DarajaRepository;
import com.androidstudy.daraja.util.Environment;
import com.androidstudy.daraja.constants.TransactionType;

public class Builder {
    private String consumerKey;
    private String consumerSecret;
    private String businessShortCode;
    private String passKey;
    private TransactionType transactionType;
    private String callbackUrl;
    private Environment environment;

    Builder(String consumerKey, String consumerSecret) {
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

    public Builder setEnvironment(Environment environment) {
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
        daraja.baseUrl = (this.environment == Environment.SANDBOX) ? URLs.SANDBOX_BASE_URL : URLs.PRODUCTION_BASE_URL;

        daraja.repo = new DarajaRepository(daraja.consumerKey, daraja.consumerSecret, daraja.baseUrl);

        return daraja;
    }
}
