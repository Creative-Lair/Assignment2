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

        editor.putString("name",names);
        editor.commit();
    }

    public String getNames(){

        return pref.getString("name","Melbourne" );

    }

    public void updateLat(String lat){
           editor.putString("latitude",lat);

        editor.commit();
    }


    public String getLat(){

        return  pref.getString("latitude","-37.50");



    }

    public void updateLon(String lon){
            editor.putString("longitude",lon);

        editor.commit();
    }


    public String getLon(){
        return   pref.getString("longitude","145.01");



    }

    public void updateTZ(String tz){
            editor.putString("timezone",tz);

        editor.commit();
    }


    public String getTZ(){
        return  pref.getString("timezone", TimeZone.getDefault().toString());

    }






}
