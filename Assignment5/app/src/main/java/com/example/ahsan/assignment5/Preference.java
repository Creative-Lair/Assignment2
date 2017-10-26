package com.example.ahsan.assignment5;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by AHSAN on 10/4/2017.
 */



public class Preference {

    private static final String PREF_NAME = "Assignment 5";

    private static final String KEY_NAME = "names";
    private static final String KEY_LAT = "latitude";
    private static final String KEY_LON = "longitude";
    private static final String KEY_TZ = "timezone";
    private static final String KEY_FIRST = "first";
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;
    int PRIVATE_MODE = 0;

    public Preference(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void updateNames(String names){

        editor.putString(KEY_NAME,names);
        editor.commit();
    }

    public String getNames(){

        return pref.getString(KEY_NAME,"Melbourne" );

    }

    public void updateLat(String lat){
           editor.putString(KEY_LAT,lat);

        editor.commit();
    }


    public String getLat(){

        return  pref.getString(KEY_LAT,"-37.50");



    }

    public void updateLon(String lon){
            editor.putString(KEY_LON,lon);

        editor.commit();
    }


    public String getLon(){
        return   pref.getString(KEY_LON,"145.01");



    }

    public void updateTZ(String tz){
            editor.putString(KEY_TZ,tz);

        editor.commit();
    }


    public String getTZ(){
        return  pref.getString(KEY_TZ, TimeZone.getDefault().toString());

    }

    public void updateFirst(String first)
    {
        editor.putString(KEY_FIRST,first);
        editor.commit();
    }

    public String getFirst()
    {
        return pref.getString(KEY_FIRST,"first");
    }

    public String getSmsBody(){
        return pref.getString("sms", "Text Message");
    }

    public void setSmsBody(String smsBody){
        editor.putString("sms",smsBody);
        editor.commit();
    }




}
