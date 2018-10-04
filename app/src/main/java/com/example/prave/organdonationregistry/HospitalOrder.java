package com.example.prave.organdonationregistry;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;

public class HospitalOrder extends AppCompatActivity {
    CardView hosOrder;
    CardView hosNotify;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hos_options);
        hosOrder=findViewById(R.id.hos_order);
        hosNotify=findViewById(R.id.hos_notify);

    }

    @Override
    public void onBackPressed() {
        Intent i=new Intent(getApplicationContext(),HospitalLogin.class);
        startActivity(i);
        setContentView(R.layout.hos_login);
    }
}
