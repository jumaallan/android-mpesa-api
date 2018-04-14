package com.twigafoods.mpesa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.twigafoods.daraja.Daraja;
import com.twigafoods.daraja.util.Env;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Daraja.with();
    }
}
