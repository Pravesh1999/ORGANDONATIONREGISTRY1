package com.example.prave.organdonationregistry;

import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import static android.support.constraint.Constraints.TAG;


public class myDBHandler extends SQLiteOpenHelper {
    private  static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="orgondonation.db";
    private static final String TABLE_DONOR="donor";
    public static final String COLUMN_ID="_id";
    public static final String COLUMN_FNAME="FNAME";
    public static final String COLUMN_LNAME="LNAME";
    public static final String COLUMN__gender="GENDER";
    public static final String COLUMN__ADDRESS="ADDRESS";
    public static final String COLUMN__CITY="CITY";
    public static final String COLUMN__Phonenumber="PHONENUMBER";
    public static final String COLUMN__EMAIL="EMAIL";
    public static final String COLUMN__Username="USERNAME";
    public static final String COLUMN_ALIVE="ALIVE";
    public static final String COLUMN_TAKEN="taken";
    public static final String COLUMN_ORGAN="ORGAN";
    public static final String TABLE_HOSPITAl="Hospital";
    public static final String COLUMN__ID="_ID";
    public static final String COLUMN_HOSNAME="HOSNAME";
    public static final String COLUMN_TYPE="TYPE";
    public static final String COLUMN_HOSCITY="City";
    public static final String COLUMN_Address="Address";
    public static final String COLUMN_PIN="PIN";
    public static final String COLUMN_PHONE="PHONE";
    public static final String COLUMN_HOSEMAIL="Email";
    public static final String COLUMN_PASSWORD="Password";
    public static final String COLUMN_ORGANID="ORGANID";
    public static final String COLUMN_ORGANName="ORGANName";
    public static final String TABLE_ORGAN="Organ";
    public static final String COLUMN_Patient="patient";
    public static final String COLUMN_PatientPhone="patientphone";
    public static final String COLUMN_Organ="organ";
    public static final String TABLE_WAIT="Wait";
    public myDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_DONOR + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_FNAME + " TEXT, " +
                COLUMN_LNAME + " TEXT, " +
                COLUMN__gender + " TEXT, " +
                COLUMN__ADDRESS + " TEXT, " +
                COLUMN__CITY + " TEXT, " +
                COLUMN__Phonenumber + " TEXT, " +
                COLUMN__EMAIL + " TEXT, " +
                COLUMN__Username + " TEXT, " +
                COLUMN_ALIVE + " TEXT, " +
                COLUMN_ORGAN + " TEXT, " +
                COLUMN_TAKEN + " TEXT " +
                ");";
        db.execSQL(query);
        String wait = "CREATE TABLE " + TABLE_WAIT + "(" +
                COLUMN_Patient + " TEXT, " +
                COLUMN_PatientPhone + " TEXT, " +
                COLUMN_Organ + " TEXT " +
                ");";
        db.execSQL(wait);
        String CREATE_TABLE = "CREATE TABLE " + TABLE_HOSPITAl + "(" +
                COLUMN__ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_HOSNAME + " TEXT, " +
                COLUMN_TYPE + " TEXT, " +
                COLUMN_HOSCITY + " TEXT, " +
                COLUMN_Address + " TEXT, " +
                COLUMN_PIN + " INTEGER, " +
                COLUMN_PHONE + " TEXT, " +
                COLUMN_HOSEMAIL + " TEXT, " +
                COLUMN_PASSWORD + " TEXT " +
                ");";
        db.execSQL(CREATE_TABLE);
        String organ = "CREATE TABLE " + TABLE_ORGAN + "(" +
                COLUMN_ORGANID+ " INTEGER PRIMARY KEY , " +
                COLUMN_ORGANName + " TEXT " +

                ");";
        db.execSQL(organ);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_DONOR);
        onCreate(db);

    }
    public void addDonor(String Fname,String Lname,String gender,String Address,String city,String Phone,String Email,String User,String Organ){

        ContentValues values=new ContentValues();
        values.put(COLUMN_FNAME,Fname);
        values.put(COLUMN_LNAME,Lname);
        values.put(COLUMN__gender,gender);
        values.put(COLUMN__ADDRESS,Address);
        values.put(COLUMN__CITY,city);
        values.put(COLUMN__Phonenumber,Phone);
        values.put(COLUMN__EMAIL,Email);
        values.put(COLUMN__Username,User);
        values.put(COLUMN_ALIVE,0);
        values.put(COLUMN_TAKEN,0);
        values.put(COLUMN_ORGAN,Organ);
        SQLiteDatabase db=getWritableDatabase();
        db.insert(TABLE_DONOR,null,values);
        db.close();

    }
    public void addHospital(String HospitalName,String Type,String City,String Address,String Pin,String Phone,String Email,String Password){
        ContentValues values=new ContentValues();
        values.put(COLUMN_HOSNAME,HospitalName);
        values.put(COLUMN_TYPE,Type);
        values.put(COLUMN_HOSCITY,City);
        values.put(COLUMN_Address,Address);
        values.put(COLUMN_PIN,Pin);
        values.put(COLUMN_PHONE,Phone);
        values.put(COLUMN_HOSEMAIL,Email);
        values.put(COLUMN_PASSWORD,Password);

        SQLiteDatabase db=getWritableDatabase();
        db.insert(TABLE_HOSPITAl,null,values);
        db.close();
    }
    public void addWait(String name,String phone,String organ){
        ContentValues values=new ContentValues();
        values.put(COLUMN_Patient,name);
        values.put(COLUMN_PatientPhone,phone);
        values.put(COLUMN_Organ,organ);
        SQLiteDatabase db=getWritableDatabase();
        db.insert(TABLE_WAIT,null,values);
        db.close();
    }
    public void addOrgan(){
        ContentValues values=new ContentValues();
        values.put(COLUMN_ORGANID,1);
        values.put(COLUMN_ORGANName,"Liver");
        values.put(COLUMN_ORGANID,2);
        values.put(COLUMN_ORGANName,"kidney");
        values.put(COLUMN_ORGANID,3);
        values.put(COLUMN_ORGANName,"Heart");
        values.put(COLUMN_ORGANID,4);
        values.put(COLUMN_ORGANName,"Lungs");
        values.put(COLUMN_ORGANID,5);
        values.put(COLUMN_ORGANName,"Cornea");
        values.put(COLUMN_ORGANID,6);
        values.put(COLUMN_ORGANName,"Blood and platelets");

    }

    public String DatabasetoString(String Phone){
        String dbString="";

        SQLiteDatabase db=getWritableDatabase();
        String query="SELECT * FROM "+ TABLE_DONOR + " WHERE "+ COLUMN__Phonenumber + "=" + Phone;

        //Cursor
        Cursor c=db.rawQuery(query,null);
        c.moveToFirst();
            if(c.getString(c.getColumnIndex(COLUMN_FNAME))!=null && c.getString(c.getColumnIndex(COLUMN_LNAME))!=null && c.getString(c.getColumnIndex(COLUMN__gender))!=null && c.getString(c.getColumnIndex(COLUMN__CITY))!=null && c.getString(c.getColumnIndex(COLUMN__ADDRESS))!=null && c.getString(c.getColumnIndex(COLUMN__EMAIL))!=null && c.getString(c.getColumnIndex(COLUMN__Username))!=null && c.getString(c.getColumnIndex(COLUMN_ORGAN))!=null && c.getString(c.getColumnIndex(COLUMN_TAKEN))!=null){
                dbString=dbString+"\nFirstName:"+c.getString(c.getColumnIndex(COLUMN_FNAME))+"\nLastName:"+c.getString(c.getColumnIndex(COLUMN_LNAME))+"\nGender:"+c.getString(c.getColumnIndex(COLUMN__gender))+"\nCITY:"+c.getString(c.getColumnIndex(COLUMN__CITY))+"\nEmail:"+c.getString(c.getColumnIndex(COLUMN__EMAIL))+"\nADDRESS:"+c.getString(c.getColumnIndex(COLUMN__ADDRESS))+"\nPhoneNumber:"+c.getString(c.getColumnIndex(COLUMN__Phonenumber))+"\nALIVE(0-Alive 1-Dead):"+c.getString(c.getColumnIndex(COLUMN_ALIVE))+"\nORGAN:"+c.getString(c.getColumnIndex(COLUMN_ORGAN))+"\nTAKEN(0- Not Taken 1-Taken):"+c.getString(c.getColumnIndex(COLUMN_TAKEN));

            }


        db.close();
        return dbString;


    }
    public String isDonor(String pno){
        String dbDonor="";
        SQLiteDatabase db=getWritableDatabase();
        String donor=" SELECT " + COLUMN_FNAME + " FROM " + TABLE_DONOR + " WHERE " + COLUMN__Phonenumber + " = " + pno;
        Cursor c=db.rawQuery(donor,null);
        c.moveToFirst();
        if(c.getString(c.getColumnIndex(COLUMN_FNAME))!=null){
            dbDonor=c.getString(c.getColumnIndex(COLUMN_FNAME));
        }

        db.close();
        return dbDonor;

    }
    public  String isUser(EditText Username){
        SQLiteDatabase db=getWritableDatabase();
        String a="";
        String username;
        username=Username.getText().toString();
        String check=" SELECT " + COLUMN__Username + " FROM " + TABLE_DONOR + " WHERE " + COLUMN__Phonenumber + " = " + username;
        Cursor c=db.rawQuery(check,null);
        c.moveToFirst();
            if(c.getString(c.getColumnIndex(COLUMN__Username))!=null){
               a=c.getString(c.getColumnIndex(COLUMN__Username));
            }
       return a;
    }
    public String hosLogin(EditText Phone){
        SQLiteDatabase db=getWritableDatabase();
        String a="";
        String phone= Phone.getText().toString();
        String password=" SELECT " + COLUMN_PASSWORD + " FROM " + TABLE_HOSPITAl + " WHERE " + COLUMN_PHONE + " = " + phone;
        Cursor c=db.rawQuery(password,null);
        c.moveToFirst();
        if(c.getString(c.getColumnIndex(COLUMN_PASSWORD))!=null){
            a=c.getString(c.getColumnIndex(COLUMN_PASSWORD));
        }
        else if(c.getCount()==0){
            a="Invalid user";
        }
        return a;

    }
    public void updateDead(String Phone){
        SQLiteDatabase db=getWritableDatabase();
        String update=" UPDATE " + TABLE_DONOR +" SET " + COLUMN_ALIVE + " = " + " 1 " + " WHERE " + COLUMN__Phonenumber + " = " + Phone;
        Cursor c=db.rawQuery(update,null);
        c.moveToFirst();


    }
    public String hospitalView(String spinner){
        String details="";
        SQLiteDatabase db=getWritableDatabase();
        try{
            Cursor c=db.rawQuery("select * from donor where ORGAN='"+spinner+"'",null);
            c.moveToFirst();
            while(!c.isAfterLast()){
                if(c.getString(c.getColumnIndex(COLUMN_FNAME))!=null){
                    details=details+"\n"+c.getString(c.getColumnIndex(COLUMN_FNAME));
                    c.moveToNext();
                }
                else {
                    details=("No records found");
                }
            }

        }

        catch(SQLiteException e){
            Log.e(TAG, "SQLiteException:" + e.getMessage());
        }




        return details;

    }
    public String Allocate(String spinner){
        String allocate="";
        SQLiteDatabase db=getWritableDatabase();
        Cursor c=db.rawQuery("select * from donor where ALIVE=1 and taken=0 and ORGAN='"+spinner+"'" ,null);
        if(c.getCount()==0){
            allocate="No donors are available,your request is placed on hold";

        }
        else {
            c.moveToFirst();
            if(c.getString(c.getColumnIndex(COLUMN_FNAME))!=null){
                allocate=c.getString(c.getColumnIndex(COLUMN_FNAME));
            }
        }
        return allocate;
    }
    public String Waitlist(String spinner){
        String allocate="";
        SQLiteDatabase db=getWritableDatabase();
        try{
            Cursor c=db.rawQuery("select * from donor where ALIVE=1 and taken=0 and ORGAN='"+spinner+"'",null);
            c.moveToFirst();
            if(c.getCount()==0){
                allocate="WaitList";
            }
            else if(c.getString(c.getColumnIndex(COLUMN_FNAME))!=null){
                allocate=c.getString(c.getColumnIndex(COLUMN_FNAME));
            }


        }catch (SQLiteException e){
            Log.e(TAG, "SQLiteException:" + e.getMessage());

        }
        return allocate;

    }
    public void updateTaken(String Fname){
        SQLiteDatabase db=getWritableDatabase();

        Cursor c=db.rawQuery("UPDATE donor SET taken=1 where FNAME='"+Fname+"'",null);
        c.moveToFirst();


    }
    public String DatabaseStringOrgan(String Fname){
        String details="";
        SQLiteDatabase db=getWritableDatabase();
        Cursor c=db.rawQuery("select * from wait",null);
        c.moveToFirst();
        while (!c.isAfterLast()){
            if(c.getString(c.getColumnIndex(COLUMN_Patient))!=null && c.getString(c.getColumnIndex(COLUMN_PatientPhone))!=null && c.getString(c.getColumnIndex(COLUMN_Organ))!=null ){
                details=details+"\n"+c.getString(c.getColumnIndex(COLUMN_Patient))+"\n"+c.getString(c.getColumnIndex(COLUMN_PatientPhone))+"\n"+c.getString(c.getColumnIndex(COLUMN_Organ));
                c.moveToNext();
            }
            if(c.getCount()==0){
                details="No records found";
            }

        }

        return details;


    }




}
