package com.androidstudy.daraja.repo;

import com.androidstudy.daraja.constants.TransactionType;
import com.androidstudy.daraja.model.AccessToken;
import com.androidstudy.daraja.model.LNMExpress;
import com.androidstudy.daraja.model.PaymentResult;
import com.androidstudy.daraja.network.ApiClient;
import com.androidstudy.daraja.network.AuthAPI;
import com.androidstudy.daraja.util.Settings;

import retrofit2.Call;

public class DarajaRepository {

    String consumerKey;
    String consumerSecret;
    String baseUrl;

    AuthAPI authAPI;

    public DarajaRepository(String consumerKey, String consumerSecret, String baseUrl) {
        this.consumerKey = consumerKey;
        this.consumerSecret = consumerSecret;
        this.baseUrl = baseUrl;

        authAPI = ApiClient.getAuthAPI(consumerKey, consumerSecret, baseUrl);
    }

    public Call<AccessToken> getAccessToken() {
        return ApiClient
                .getAuthAPI(consumerKey, consumerSecret, baseUrl)
                .getAccessToken();
    }

    //TODO('Refactor')
    public Call<PaymentResult> initiatePayment(String token, String phoneNumber, String amount,
                                               String accountReference, String description, String businessShortCode, String passKey,
                                               TransactionType transactionType, String callbackUrl) {

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

        return ApiClient.getAPI(baseUrl, token).getLNMPesa(express);
    }

}
