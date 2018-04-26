package com.twigafoods.daraja;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.twigafoods.daraja.model.AccessToken;
import com.twigafoods.daraja.model.C2BRegister;
import com.twigafoods.daraja.model.C2BRegisterResult;
import com.twigafoods.daraja.model.C2BSimulate;
import com.twigafoods.daraja.model.C2BSimulateResult;
import com.twigafoods.daraja.model.LNMExpress;
import com.twigafoods.daraja.model.LNMResult;
import com.twigafoods.daraja.network.ApiClient;
import com.twigafoods.daraja.network.URLs;
import com.twigafoods.daraja.constants.TransactionType;
import com.twigafoods.daraja.util.Env;
import com.twigafoods.daraja.util.Settings;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Daraja {
    private String BASE_URL;
    private String CONSUMER_KEY;
    private String CONSUMER_SECRET;

    @Nullable
    private AccessToken accessToken;

    private Daraja(Env env, String CONSUMER_KEY, String CONSUMER_SECRET) {
        this.CONSUMER_KEY = CONSUMER_KEY;
        this.CONSUMER_SECRET = CONSUMER_SECRET;
        this.BASE_URL = (env == Env.SANDBOX) ? URLs.SANDBOX_BASE_URL : URLs.PRODUCTION_BASE_URL;
    }

    //TODO :: CHECK FOR INTERNET CONNECTION
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
        ApiClient.getAuthAPI(CONSUMER_KEY, CONSUMER_SECRET, BASE_URL).getAccessToken().enqueue(new Callback<AccessToken>() {
            @Override
            public void onResponse(@NonNull Call<AccessToken> call, @NonNull Response<AccessToken> response) {
                if (response.isSuccessful()) {
                    AccessToken accessToken = response.body();
                    if (accessToken != null) {
                        Daraja.this.accessToken = accessToken;
                        listener.onResult(accessToken);
                        return;
                    }
                }
                listener.onError("Authentication Failed");
            }

            @Override
            public void onFailure(@NonNull Call<AccessToken> call, @NonNull Throwable t) {
                listener.onError("Authentication Failed: " + t.getLocalizedMessage());
            }
        });
    }

    /**
     * MPESAExpress - Formerly STKPush -> Pass the LNMPesa Object
     */
    public void requestMPESAExpress(LNMExpress lnmExpress, final DarajaListener<LNMResult> listener) {

        if (accessToken == null) {
            listener.onError("Not Authenticated");
            return;
        }

        String sanitizedPhoneNumber = Settings.formatPhoneNumber(lnmExpress.getPhoneNumber());
        String sanitizedPartyA = Settings.formatPhoneNumber(lnmExpress.getPartyA());
        String timeStamp = Settings.generateTimestamp();
        String generatedPassword = Settings.generatePassword(lnmExpress.getBusinessShortCode(), lnmExpress.getPassKey(), timeStamp);

        LNMExpress express = new LNMExpress(
                lnmExpress.getBusinessShortCode(),
                generatedPassword,
                timeStamp,
                TransactionType.TRANSACTION_TYPE_CUSTOMER_PAYBILL_ONLINE,
                lnmExpress.getAmount(),
                sanitizedPartyA,
                lnmExpress.getPartyB(),
                sanitizedPhoneNumber,
                lnmExpress.getCallBackURL(),
                lnmExpress.getAccountReference(),
                lnmExpress.getTransactionDesc()
        );

        ApiClient.getAPI(BASE_URL, accessToken.getAccess_token()).getLNMPesa(express).enqueue(new Callback<LNMResult>() {
            @Override
            public void onResponse(@NonNull Call<LNMResult> call, @NonNull Response<LNMResult> response) {
                if (response.isSuccessful()) {
                    LNMResult lnmResult = response.body();
                    if (lnmResult != null) {
                        listener.onResult(lnmResult);
                        return;
                    }
                }
                listener.onError("MPESAExpress Failed");
            }

            @Override
            public void onFailure(@NonNull Call<LNMResult> call, @NonNull Throwable t) {
                listener.onError("MPESAExpress Failed: " + t.getLocalizedMessage());
            }
        });
    }

    /**
     * C2B (CUSTOMER TO BUSINESS)
     * <p>
     * * Register URL -> Pass this Model
     * {
     * "ShortCode": " " ,
     * "ResponseType": " ",
     * "ConfirmationURL": " ",
     * "ValidationURL": " "
     * }
     */
    public void C2BRegisterURL(C2BRegister c2BRegister, final DarajaListener<C2BRegisterResult> listener) {
        if (accessToken == null) {
            listener.onError("Not Authenticated");
            return;
        }

        ApiClient.getAPI(BASE_URL, accessToken.getAccess_token()).registerURL(c2BRegister).enqueue(new Callback<C2BRegisterResult>() {
            @Override
            public void onResponse(@NonNull Call<C2BRegisterResult> call, @NonNull Response<C2BRegisterResult> response) {
                if (response.isSuccessful()) {
                    C2BRegisterResult c2BRegisterResult = response.body();
                    if (c2BRegisterResult != null) {
                        listener.onResult(c2BRegisterResult);
                        return;
                    }
                }
                listener.onError("C2B Register URL Failed");
            }

            @Override
            public void onFailure(@NonNull Call<C2BRegisterResult> call, @NonNull Throwable t) {
                listener.onError("C2B Register URL Failed: " + t.getLocalizedMessage());
            }
        });

    }

    /**
     * C2B (CUSTOMER TO BUSINESS)
     * <p>
     * * C2B Simulate Transaction -> Pass this Model
     * {
     * "ShortCode": "",
     * "CommandID": "CustomerPayBillOnline",
     * "Amount": "",
     * "Msisdn": "",
     * "BillRefNumber": ""
     * }
     */
    public void C2BSimulate(C2BSimulate c2BSimulate, final DarajaListener<C2BSimulateResult> listener) {
        if (accessToken == null) {
            listener.onError("Not Authenticated");
            return;
        }

        ApiClient.getAPI(BASE_URL, accessToken.getAccess_token()).simulateC2B(c2BSimulate).enqueue(new Callback<C2BSimulateResult>() {
            @Override
            public void onResponse(@NonNull Call<C2BSimulateResult> call, @NonNull Response<C2BSimulateResult> response) {
                if (response.isSuccessful()) {
                    C2BSimulateResult c2BSimulateResult = response.body();
                    if (c2BSimulateResult != null) {
                        listener.onResult(c2BSimulateResult);
                        return;
                    }
                }
                listener.onError("C2B Simulation Failed");
            }

            @Override
            public void onFailure(@NonNull Call<C2BSimulateResult> call, @NonNull Throwable t) {
                listener.onError("C2B Simulation Failed: " + t.getLocalizedMessage());
            }
        });

    }
}
