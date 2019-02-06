package com.example.prave.organdonationregistry;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

public class DonorDetails extends AppCompatActivity {
    myDBHandler dbHandler=new myDBHandler(DonorDetails.this);
    DonorRegistry donorRegistry=new DonorRegistry();
    TextView details;
    String detail;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.donor_details);
        details=findViewById(R.id.details);
        Bundle b=getIntent().getExtras();
        String Phone=b.getString("PhoneNumber");
        Toast.makeText(getApplicationContext(),dbHandler.DatabasetoString(Phone),Toast.LENGTH_SHORT).show();

        details.setText(dbHandler.DatabasetoString(Phone));

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(getApplicationContext(),Login.class);
        startActivity(i);
        setContentView(R.layout.login_screen);
    }
}
