package com.androidstudy.daraja.v2;

import android.support.annotation.NonNull;

import com.androidstudy.callback.DarajaCallback;
import com.androidstudy.daraja.DarajaListener;
import com.androidstudy.daraja.R;
import com.androidstudy.daraja.constants.Transtype;
import com.androidstudy.daraja.model.AccessToken;
import com.androidstudy.daraja.model.LNMExpress;
import com.androidstudy.daraja.model.LNMResult;
import com.androidstudy.daraja.network.ApiClient;
import com.androidstudy.daraja.network.URLs;
import com.androidstudy.daraja.util.Env;
import com.androidstudy.daraja.util.Settings;
import com.androidstudy.daraja.util.TransactionType;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Daraja {

    /**
     * src : https://peternjeru.co.ke/safdaraja/ui/
     * 1. BusinessShortCode : This is the shortcode of the organization initiating the request and expecting the payment.
     * 2. Password This is the Base64-encoded value of the concatenation of the Shortcode + LNM Passkey + Timestamp, e.g. given the test values above, and using a timestamp of 20190323053601, the encoded password will be
     * 3. Timestamp : This is the same Timestamp used in the encoding above, in the format YYYMMDDHHmmss.
     * 4. TransactionType : The type of transaction being performed. These are the same values as the C2B command IDs (CustomerPayBillOnline and CustomerBuyGoodsOnline) and the same rules apply here. For now, only CustomerPayBillOnline is supported.
     * 5. Amount : Self explanatory.
     * 6. PartyA : The Debit party of the transaction/the party paying out in the transaction, hereby the phone number of the customer.
     * 7. PartyB : The credit party of the transaction/the party being paid in the transaction, hereby being the shortcode of the organization. This is the same value as the Business Shortcode
     * 8. PhoneNumber : Same as PartyA.
     * 9. CallBackURL : This is the endpoint where you want the results of the transaction delivered. Same rules for Register URL API callbacks apply
     * 10. AccountReference : This is the value the customer would have put as the account number on their phone if they had performed the transaction via phone.
     * 11. TransactionDesc : Short description of the transaction. Optional, but element must be present.
     * <p>
     * <p>
     * Config level
     * 1. BusinessShortCode = PartyB
     * 2. Password
     * 3. Timestamp
     * 4. TransactionType
     * 5. CallBackURL
     * <p>
     * Payment method :
     * 1. Amount
     * 2. PartyA = PhoneNumber
     * 3. AccountReference (Auto)
     * 4. TransactionDesc (Auto)
     **/


    private String consumerKey;
    private String consumerSecret;
    private String businessShortCode;
    private String passKey;
    private TransactionType transactionType;
    private String callbackUrl;
    private Env environment;
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
            daraja.environment = this.environment;
            daraja.baseUrl = (this.environment == Env.SANDBOX) ? URLs.SANDBOX_BASE_URL : URLs.PRODUCTION_BASE_URL;

            return daraja;
        }
    }


    private Daraja() {

    }

    /**
     * 2 methods
     * getToken() : DarajaListener
     * pay(phone, amount, accountReference, description) : DarajaListener
     */

    public DarajaListener<AccessToken> getAccessToken(final DarajaListener<AccessToken> listener) {
        ApiClient
                .getAuthAPI(consumerKey, consumerSecret, baseUrl)
                .getAccessToken()
                .enqueue(new DarajaCallback(listener));

        return listener;
    }

    public DarajaListener<LNMResult> makePaymentRequest(String token, String phoneNumber, String amount, String accountReference, String description, final DarajaListener<LNMResult> listener) {
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
