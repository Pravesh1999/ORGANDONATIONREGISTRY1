package com.example.prave.organdonationregistry;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.os.Bundle;
import android.widget.Toast;


public class Hospital extends AppCompatActivity  {
    public Spinner spinner;
    private static final String[] paths = {"None", "General services", "Specialty", "Super specialty "};
    EditText hosName;
    EditText hosID;
    EditText hosCity;
    EditText hosAddress;
    EditText hosPin;
    EditText hosPhone;
    EditText hosEmail;
    EditText hosPassword;
    CardView regHosButton;
    String HosName;
    String HosCity;
    String HosAddress;
    String HosPin;
    String HosPhone;
    String HosEmail;
    String HosPassword;
    String Spinner;
    int length;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        final myDBHandler dbHandler=new myDBHandler(Hospital.this);
        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Hospital.this, android.R.layout.simple_spinner_item, paths);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        hosName = findViewById(R.id.hosName);
        hosCity = findViewById(R.id.hosCity);
        hosAddress = findViewById(R.id.hosAddress);
        hosPin = findViewById(R.id.hosPin);
        length = hosPin.length();
        hosPhone = findViewById(R.id.hosPhone);
        hosEmail = findViewById(R.id.hosEmail);
        hosPassword = findViewById(R.id.hosPass);
        regHosButton = findViewById(R.id.regHosButton);
        regHosButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(checkData()){
                    HosName=hosName.getText().toString();
                    Spinner=spinner.getSelectedItem().toString();
                    HosCity=hosCity.getText().toString();
                    HosAddress=hosAddress.getText().toString();
                    HosPin=hosPin.getText().toString();
                    HosPhone=hosPhone.getText().toString();
                    HosEmail=hosEmail.getText().toString();
                    HosPassword=hosPassword.getText().toString();
                    dbHandler.addHospital(HosName,Spinner,HosCity,HosAddress,HosPin,HosPhone,HosEmail,HosPassword);
                }

            }
        });

    }

    boolean isEmail(EditText text) {
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(), HospitalLogin.class);
        startActivity(i);
        setContentView(R.layout.hos_login);
    }

    boolean checkData() {
        if (isEmpty(hosName)) {
            hosName.setError("Enter Hospital Name");
        }
        if (isEmpty(hosCity)) {
            hosCity.setError("Enter a valid City");
        }
        if (isEmpty(hosAddress)) {
            hosAddress.setError("Enter a valid address");
        }

        if (isEmail(hosEmail) == false) {
            hosEmail.setError("Email id not valid");
        }
        if (isEmpty(hosPhone)) {
            hosPhone.setError("Enter a valid phone number");
        }
        if (isEmpty(hosPin)) {
            hosPin.setError("Enter a valid Pin number");
        }
        if (isEmpty(hosPassword)) {
            hosPassword.setError("Enter a Password");
        }
        if(!isEmpty(hosName)&&!isEmpty(hosCity)&&!isEmpty(hosEmail)&&!isEmpty(hosPhone)&&!isEmpty(hosPin)&&!isEmpty(hosPassword)){
            return true;
        }
        else
            return false;


    }
}
