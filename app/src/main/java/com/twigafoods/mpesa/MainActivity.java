package com.twigafoods.mpesa;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import com.twigafoods.daraja.Daraja;
import com.twigafoods.daraja.DarajaListener;
import com.twigafoods.daraja.model.AccessToken;
import com.twigafoods.daraja.model.LNMResult;

public class MainActivity extends AppCompatActivity implements DarajaListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Daraja daraja = Daraja.with("AkJy4AzYBuo17aPFffhazMxJ93yxxgKB", "ooU69NojM0GoyKth", this);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(v -> daraja.sendSTKPush(
                "174379",
                "AkJy4AzYBuo17aPFffhazMxJ93yxxgKB",
                "100",
                "0708374149",
                "174379",
                "0797435901",
                "http://api.twigafoods.com/mpesa/",
                "0001",
                "Twiga",
                this));
    }

    @Override
    public void onResult(@NonNull Object object) {
        if (object instanceof AccessToken) {
            Log.i(MainActivity.this.getClass().getSimpleName(), ((AccessToken) object).getAccess_token());
        } else if (object instanceof LNMResult) {
            Log.i(MainActivity.this.getClass().getSimpleName(), ((LNMResult) object).ResponseDescription);
        }
    }

    @Override
    public void onError(String error) {
        Log.e(MainActivity.this.getClass().getSimpleName(), error);
    }
}
