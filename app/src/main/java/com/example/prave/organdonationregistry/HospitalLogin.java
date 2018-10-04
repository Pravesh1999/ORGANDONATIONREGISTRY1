package com.example.prave.organdonationregistry;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

public class HospitalLogin extends AppCompatActivity {
    CardView hosLogin;
    CardView registerScreen;
    EditText hosuname;
    EditText hospwd;
    int counter = 3;
    String userName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hos_login);
        final myDBHandler dbHandler=new myDBHandler(HospitalLogin.this);
        hosLogin=findViewById(R.id.hos_login);
        registerScreen=findViewById(R.id.register_screen);
        hosuname=findViewById(R.id.hosuname);
        hospwd=findViewById(R.id.hospwd);
        hosLogin=findViewById(R.id.hos_login);
        hosLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String x=dbHandler.hosLogin(hosuname);
                if(x.equals(hospwd.getText().toString())){
                    Intent i=new Intent(getApplicationContext(),HospitalOrder.class);
                    startActivity(i);
                    setContentView(R.layout.hospital_order);
                }
                else {
                    Toast.makeText(getApplicationContext(),x,Toast.LENGTH_SHORT).show();
                    counter--;
                    if (counter == 0) {
                        hosLogin.setEnabled(false);
                    }
                }
            }

        });
        registerScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),Hospital.class);
                startActivity(i);
                setContentView(R.layout.layout);
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent i=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);
        setContentView(R.layout.activity_main);
    }
}
