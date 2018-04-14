package com.twigafoods.mpesa;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.twigafoods.daraja.Daraja;
import com.twigafoods.daraja.util.Env;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Daraja.with("AkJy4AzYBuo17aPFffhazMxJ93yxxgKB", "ooU69NojM0GoyKth", Env.SANDBOX);

        //Constructor - Pass the data - Sanitize on the other side!
        Daraja.sendSTKPush(
                Env.SANDBOX,
                BUSINESS_SHORT_CODE,
                PASS_KEY,
                AMOUNT,
                PARTY_A,
                PARTY_B,
                PHONE_NUMBER,
                CALLBACK_URL,
                ACCOUNT_REFERENCE,
                TRANSACTION_REFERENCE
        );

        Button button = findViewById(R.id.button);
        // button.setOnClickListener(v -> Daraja.sendSTKPush(Env.SANDBOX));

    }
}
