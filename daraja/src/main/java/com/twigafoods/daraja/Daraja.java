package com.twigafoods.daraja;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.twigafoods.daraja.model.AccessToken;
import com.twigafoods.daraja.model.LNMExpress;
import com.twigafoods.daraja.model.LNMResult;
import com.twigafoods.daraja.network.ApiClient;
import com.twigafoods.daraja.network.URLs;
import com.twigafoods.daraja.transaction.TransactionType;
import com.twigafoods.daraja.util.Env;
import com.twigafoods.daraja.util.Settings;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Daraja {
    private String url;
    private String consumerKey;
    private String consumerSecret;

    @Nullable
    private AccessToken accessToken;

    private Daraja(Env env, String consumerKey, String consumerSecret) {
        this.consumerKey = consumerKey;
        this.consumerSecret = consumerSecret;
        this.url = (env == Env.SANDBOX) ? URLs.SANDBOX_BASE_URL : URLs.PRODUCTION_BASE_URL;
    }

    //TODO :: CHECK FOR INTERNET CONNECTION
    // LOGIC - 1. Get Token 2. Make Request
    //Generate the Auth Token
    public static Daraja with(String consumerKey, String consumerSecret, DarajaListener<AccessToken> darajaListener) {
        return with(consumerKey, consumerSecret, Env.SANDBOX, darajaListener);
    }

    public static Daraja with(String CONSUMER_KEY, String CONSUMER_SECRET, Env env, DarajaListener<AccessToken> listener) {
        Daraja daraja = new Daraja(env, CONSUMER_KEY, CONSUMER_SECRET);
        daraja.auth(listener);
        return daraja;
    }

    private void auth(final DarajaListener<AccessToken> listener) {
        //Use Sandbox Base URL
        ApiClient.getAuthAPI(consumerKey, consumerSecret, url).getAccessToken().enqueue(new Callback<AccessToken>() {
            @Override
            public void onResponse(@NonNull Call<AccessToken> call, @NonNull Response<AccessToken> response) {
                if (response.isSuccessful()) {
                    AccessToken accessToken = response.body();
                    if (accessToken != null) {
                        ApiClient.setAuthToken(accessToken.getAccess_token());
                        Daraja.this.accessToken = accessToken;
                        listener.onResult(accessToken);
                        return;
                    }
                }
                listener.onError("Authentication failed");
            }

            @Override
            public void onFailure(@NonNull Call<AccessToken> call, @NonNull Throwable t) {
                listener.onError("Authentication failed: " + t.getLocalizedMessage());
            }
        });
    }

    //Request the STK Push
    public void sendSTKPush(String businessShortCode, String passKey, String amount, String partyA,
                            String partyB, String phoneNumber, String callBackURL, String accountReference,
                            String transactionDescription, final DarajaListener<LNMResult> listener) {

        if(accessToken == null) {
            listener.onError("Not authenticated");
            return;
        }

        String sanitizedPhoneNumber = Settings.formatPhoneNumber(phoneNumber);
        String timeStamp = Settings.generateTimestamp();
        String generatedPassword = Settings.generatePassword(businessShortCode, passKey, timeStamp);
        LNMExpress lnmExpress = new LNMExpress(
                businessShortCode,
                generatedPassword,
                timeStamp,
                TransactionType.TRANSACTION_TYPE_CUSTOMER_PAYBILL_ONLINE,
                amount,
                partyA,
                partyB,
                sanitizedPhoneNumber,
                callBackURL,
                accountReference,
                transactionDescription
        );

        //Use Sandbox Base URL
        ApiClient.getAPI(url).getLNMPesa(lnmExpress).enqueue(new Callback<LNMResult>() {
            @Override
            public void onResponse(@NonNull Call<LNMResult> call, @NonNull Response<LNMResult> response) {
                if (response.isSuccessful()) {
                    LNMResult lnmResult = response.body();
                    if (lnmResult != null) {
                        listener.onResult(lnmResult);
                        return;
                    }
                }
                listener.onError("Lipa na Mpesa failed");
            }

            @Override
            public void onFailure(@NonNull Call<LNMResult> call, @NonNull Throwable t) {
                listener.onError("Lipa na Mpesa failed: " + t.getLocalizedMessage());
            }
        });
    }
}
