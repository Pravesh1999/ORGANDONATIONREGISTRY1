package com.example.prave.organdonationregistry;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText uname;
    EditText pwd;
    CardView reg;
    CardView login;
    String user;
    DonorDetails details=new DonorDetails();

    @Override
    public void onBackPressed() {
        Intent i=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);
        uname=findViewById(R.id.uname);
        pwd=findViewById(R.id.pwd);
        reg=findViewById(R.id.register_screen);
        login=findViewById(R.id.login_screen);

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),DonorRegistry.class);
                startActivity(i);
                setContentView(R.layout.donor_form);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user=pwd.getText().toString();
                myDBHandler dbHandler=new myDBHandler(Login.this);
                String x=dbHandler.isUser(uname);
                if(x.equals(user)){

                    Intent i=new Intent(Login.this,DonorDetails.class);
                    Bundle b=new Bundle();
                    b.putString("PhoneNumber",uname.getText().toString());
                    i.putExtras(b);
                    startActivity(i);
                    setContentView(R.layout.donor_details);
                }
                else {
                    Toast.makeText(getApplicationContext(),"Login Error",Toast.LENGTH_SHORT).show();
                }



            }
        });


    }
}
