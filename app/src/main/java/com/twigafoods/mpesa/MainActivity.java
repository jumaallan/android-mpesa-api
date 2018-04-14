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
        button.setOnClickListener(v -> Daraja.sendSTKPush(Env.SANDBOX));

//        button.setOnClickListener(v -> {

//
//            ApiClient.getRetrofitClients(URLs.SANDBOX_BASE_URL).create(API.class).getLNMPesa(lnmExpress).enqueue(new Callback<LNMExpress>() {
//                @Override
//                public void onResponse(Call<LNMExpress> call, Response<LNMExpress> response) {
//                    Toast.makeText(MainActivity.this, "Twigiieee", Toast.LENGTH_SHORT).show();
//                }
//
//                @Override
//                public void onFailure(Call<LNMExpress> call, Throwable t) {
//                    Toast.makeText(MainActivity.this, "Twigiieee Failed", Toast.LENGTH_SHORT).show();
//                }
//            });
//        });

    }
}
