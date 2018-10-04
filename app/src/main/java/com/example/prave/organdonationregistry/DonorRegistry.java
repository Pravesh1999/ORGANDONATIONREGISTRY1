package com.example.prave.organdonationregistry;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.location.Address;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class DonorRegistry extends AppCompatActivity {
    EditText firstName;
    EditText lastName;
    EditText gender;
    EditText address;
    EditText city;
    EditText email;
    EditText phoneNumber;
    EditText sample;
    EditText PhoneNumber2;
    EditText userName;
    Button registration;
    Switch aSwitch;
    String MobilePattern = "[0-9]{10}";
    String Namefirst;
    String Namelast;
    String Gender;
    String Address;
    String City;
    String Email;
    String PhoneNumber;
    String UserName;
    public static final String TABLE_DONOR="donor";
    public static final String COLUMN_ID="_id";
    public static final String COLUMN_FNAME="FNAME";
    public static final String COLUMN_LNAME="LNAME";
    public static final String COLUMN__gender="GENDER";
    public static final String COLUMN__ADDRESS="ADDRESS";
    public static final String COLUMN__CITY="CITY";
    public static final String COLUMN__Phonenumber="PHONENUMBER";
    public static final String COLUMN__EMAIL="EMAIL";
    public static final String COLUMN__Username="USERNAME";

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_DONOR + "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_FNAME + " TEXT, " +
            COLUMN_LNAME + " TEXT, " +
            COLUMN__gender + " TEXT, " +
            COLUMN__ADDRESS + " TEXT, " +
            COLUMN__CITY + " TEXT, " +
            COLUMN__Phonenumber + " TEXT, " +
            COLUMN__EMAIL + " TEXT, " +
            COLUMN__Username + " TEXT " +
            ");";

    @Override
    public void onBackPressed() {
        Intent i=new Intent(getApplicationContext(),Login.class);
        startActivity(i);
        setContentView(R.layout.login_screen);

    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.donor_form);
        final myDBHandler handler=new myDBHandler(DonorRegistry.this);
        final DonorDetails details=new DonorDetails();
        firstName=findViewById(R.id.firstName);
        lastName=findViewById(R.id.lastName);
        gender=findViewById(R.id.gender);
        address=findViewById(R.id.address);
        city=findViewById(R.id.city);
        email=findViewById(R.id.email);
        phoneNumber=findViewById(R.id.phoneNumber);
        registration=findViewById(R.id.donorReg);
        aSwitch=findViewById(R.id.switch3);
        userName=findViewById(R.id.userName);
        sample=findViewById(R.id.sample);

        registration.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {


                if(checkDataEntered()) {

                    Namefirst = firstName.getText().toString();
                    Namelast = lastName.getText().toString();
                    Gender = gender.getText().toString();
                    Address = address.getText().toString();
                    City = city.getText().toString();
                    Email = email.getText().toString();
                    PhoneNumber = phoneNumber.getText().toString();
                    UserName = userName.getText().toString();

                    handler.addDonor(Namefirst, Namelast, Gender, Address, City, PhoneNumber, Email, UserName);



                }



            }
        });






    }




    boolean isEmail(EditText text){
        CharSequence email=text.getText().toString();
        return (!TextUtils.isEmpty(email)&& Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    boolean isEmpty(EditText text){
        CharSequence str=text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    boolean  checkDataEntered() {
        if(isEmpty(firstName)){
            firstName.setError("First Name is required");
        }
        if(isEmpty(lastName)){
            lastName.setError("Last Name is required");
        }
        if(isEmpty(gender)){
            gender.setError("Enter gender");
        }
        if(isEmpty(address)){
            address.setError("Enter address");
        }
        if(isEmpty(city)){
            city.setError("City is required");
        }
        if(isEmail(email)==false){
            email.setError("Email id not valid");
        }
        if(isEmpty(phoneNumber)){
            phoneNumber.setError("Enter phone number");
        }
        if(!phoneNumber.getText().toString().matches(MobilePattern)){
            phoneNumber.setError("Phone Number invalid");
        }
        if(!aSwitch.isChecked()){
            aSwitch.setError("Check in order to continue");
        }
        if(isEmpty(firstName)&&isEmpty(lastName)&&isEmpty(gender)&&isEmpty(address)&&isEmpty(city)&&isEmpty(email)&&isEmpty(phoneNumber)&&!aSwitch.isChecked()){
            return false;
        }
        else
            return true;
    }


}
