package com.example.ahsan.assignment5.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.ahsan.assignment5.AstronomicalCalendar;
import com.example.ahsan.assignment5.GeoLocation;
import com.example.ahsan.assignment5.Activity.ListActivity;
import com.example.ahsan.assignment5.Preference;
import com.example.ahsan.assignment5.R;
import com.example.ahsan.assignment5.Activity.UpdateAcitvity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Sunset extends Fragment implements View.OnClickListener {
    /** Called when the activity is first created. */
    private Button add,list;
    private TextView cityname;
    private String msgbody;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_main, container, false);
        initializeUI(view);
        return view;
    }

    private void initializeUI(View view)
    {
        DatePicker dp = (DatePicker) view.findViewById(R.id.datePicker);
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        add = (Button) view.findViewById(R.id.addstate);
        list =(Button) view.findViewById(R.id.liststate);
        cityname = (TextView) view.findViewById(R.id.cname);
        dp.init(year,month,day,dateChangeHandler); // setup initial values and reg. handler
        updateTime(year, month, day, view);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), UpdateAcitvity.class);
                getActivity().startActivity(myIntent);
            }
        });
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), ListActivity.class);
                getActivity().startActivity(myIntent);
            }
        });
    }

    private void updateTime(int year, int monthOfYear, int dayOfMonth,View view)
    {
        Preference pre = new Preference(getActivity());
        TimeZone tz = TimeZone.getTimeZone(pre.getTZ());
        GeoLocation geolocation = new GeoLocation(pre.getNames(), new Double(pre.getLat()), new Double(pre.getLon()), tz);
        AstronomicalCalendar ac = new AstronomicalCalendar(geolocation);
        ac.getCalendar().set(year, monthOfYear, dayOfMonth);
        Date srise = ac.getSunrise();
        Date sset = ac.getSunset();
        msgbody = pre.getNames() + ",AU\n";
        msgbody += ac.getCalendar().getTime() + "\n";
        cityname.setText(pre.getNames()+",AU");
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        TextView sunriseTV = (TextView) view.findViewById(R.id.sunriseTimeTV);
        TextView sunsetTV = (TextView) view.findViewById(R.id.sunsetTimeTV);
        Log.d("SUNRISE Unformatted", srise+"");

        msgbody += "Sunrise: " + sdf.format(srise) + "\n";
        msgbody += "Sunset: " + sdf.format(sset) + "\n";
        sunriseTV.setText(sdf.format(srise));
        sunsetTV.setText(sdf.format(sset));
        pre.setSmsBody(msgbody);
    }

    DatePicker.OnDateChangedListener dateChangeHandler = new DatePicker.OnDateChangedListener()
    {
        public void onDateChanged(DatePicker dp, int year, int monthOfYear, int dayOfMonth)
        {
            updateTime(year, monthOfYear, dayOfMonth,getView());
        }
    };
    @Override
    public void onClick(View v) {

    }
}
