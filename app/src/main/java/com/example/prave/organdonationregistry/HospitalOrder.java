package com.example.prave.organdonationregistry;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class HospitalOrder extends AppCompatActivity {
    CardView hosOrder;
    CardView hosNotify;
    CardView hosView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hos_options);
        hosOrder=findViewById(R.id.hos_order);
        hosNotify=findViewById(R.id.hos_notify);
        hosView=findViewById(R.id.hos_view);

        hosOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),HospitalOrder1.class);
                startActivity(i);
                setContentView(R.layout.hos_order);
            }
        });
        hosNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(getApplicationContext(),NotifyOPO.class);
                startActivity(i);
                setContentView(R.layout.notify_opo);
            }
        });
        hosView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),HospitalView.class);
                startActivity(i);
                setContentView(R.layout.hos_view);
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent i=new Intent(getApplicationContext(),HospitalLogin.class);
        startActivity(i);
        setContentView(R.layout.hos_login);
    }
}
