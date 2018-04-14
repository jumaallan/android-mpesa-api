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

        Button button = findViewById(R.id.button);
        button.setOnClickListener(v -> Daraja.sendSTKPush(
                Env.SANDBOX,
                "174379",
                "",
                "100",
                "0708374149",
                "174379",
                "0797435901",
                "http://api.twigafoods.com/mpesa/",
                "0001",
                "Twiga"
        ));

    }
}
