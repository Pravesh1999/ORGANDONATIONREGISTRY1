package com.example.prave.organdonationregistry;

import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.provider.ContactsContract;
import android.widget.EditText;
import android.widget.Toast;



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
                COLUMN__Username + " TEXT " +
                ");";
        db.execSQL(query);
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


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_DONOR);
        onCreate(db);

    }
    public void addDonor(String Fname,String Lname,String gender,String Address,String city,String Phone,String Email,String User){

        ContentValues values=new ContentValues();
        values.put(COLUMN_FNAME,Fname);
        values.put(COLUMN_LNAME,Lname);
        values.put(COLUMN__gender,gender);
        values.put(COLUMN__ADDRESS,Address);
        values.put(COLUMN__CITY,city);
        values.put(COLUMN__Phonenumber,Phone);
        values.put(COLUMN__EMAIL,Email);
        values.put(COLUMN__Username,User);

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

    public String DatabasetoString(String Phone){
        String dbString="";
        String FNAME="";
        SQLiteDatabase db=getWritableDatabase();
        String query="SELECT * FROM "+ TABLE_DONOR + " WHERE "+ COLUMN__Phonenumber + "=" + Phone;

        //Cursor
        Cursor c=db.rawQuery(query,null);
        c.moveToFirst();
            if(c.getString(c.getColumnIndex(COLUMN_FNAME))!=null && c.getString(c.getColumnIndex(COLUMN_LNAME))!=null && c.getString(c.getColumnIndex(COLUMN__gender))!=null && c.getString(c.getColumnIndex(COLUMN__CITY))!=null && c.getString(c.getColumnIndex(COLUMN__ADDRESS))!=null && c.getString(c.getColumnIndex(COLUMN__EMAIL))!=null && c.getString(c.getColumnIndex(COLUMN__Username))!=null){
                dbString=c.getString(c.getColumnIndex(COLUMN_FNAME))+"\t"+c.getString(c.getColumnIndex(COLUMN_LNAME))+"\t"+c.getString(c.getColumnIndex(COLUMN__gender))+"\t"+c.getString(c.getColumnIndex(COLUMN__CITY))+"\t"+c.getString(c.getColumnIndex(COLUMN__EMAIL))+"\t"+c.getString(c.getColumnIndex(COLUMN__ADDRESS))+"\t"+c.getString(c.getColumnIndex(COLUMN__Phonenumber));
                FNAME=c.getString(c.getColumnIndex(COLUMN_FNAME));
            }


        db.close();
        return dbString;


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
        return a;

    }
}
