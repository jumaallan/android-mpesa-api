package com.twigafoods.mpesa;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.twigafoods.daraja.Daraja;
import com.twigafoods.daraja.model.LNMExpress;
import com.twigafoods.daraja.util.Env;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Daraja.with("AkJy4AzYBuo17aPFffhazMxJ93yxxgKB", "ooU69NojM0GoyKth", Env.SANDBOX);

        LNMExpress lnmExpress = new LNMExpress(
                "174379",
                "MTc0Mzc5YmZiMjc5ZjlhYTliZGJjZjE1OGU5N2RkNzFhNDY3Y2QyZTBjODkzMDU5YjEwZjc4ZTZiNzJhZGExZWQyYzkxOTIwMTgwNDEyMjAwNTEy",
                "CustomerPayBillOnline",
                "1",
                "254708374149",
                "174379",
                "254797435901",
                "http://109.74.205.95:4000/",
                "001",
                "My Money"
        );

        Button button = findViewById(R.id.button);
        button.setOnClickListener(v -> Daraja.sendSTKPush(Env.SANDBOX));

    }
}
