package com.example.prave.organdonationregistry;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button click;
        CardView registerButton;
        CardView factsButton;
        CardView hospitalButton;
        CardView faq;
        CardView AboutUS;
        CardView Contact;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //when clicked on register button
        registerButton=findViewById(R.id.registerButton);
        //when facts button is pressed
        factsButton=findViewById(R.id.factsButton);
        hospitalButton=findViewById(R.id.hospitalButton);
        faq=findViewById(R.id.faq);
        AboutUS=findViewById(R.id.AboutUS);
        Contact=findViewById(R.id.contactUs);



        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),Login.class);
                startActivity(i);
                setContentView(R.layout.login_screen);

            }
        });
        factsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a=new Intent(getApplicationContext(),FactsFigures.class);
                startActivity(a);
                setContentView(R.layout.facts_figures);
            }
        });
        hospitalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent c=new Intent(getApplicationContext(),HospitalLogin.class);
                startActivity(c);
                setContentView(R.layout.hos_login);
            }
        });
        faq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent d=new Intent(getApplicationContext(),FAQ.class);
                startActivity(d);
                setContentView(R.layout.organ_faq);
            }
        });
        AboutUS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent e=new Intent(getApplicationContext(),AboutUs.class);
                startActivity(e);
                setContentView(R.layout.about_us);
            }
        });
        Contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent x=new Intent(getApplicationContext(),ContactUs.class);
                startActivity(x);
                setContentView(R.layout.contact_us);
            }
        });








    }
}
