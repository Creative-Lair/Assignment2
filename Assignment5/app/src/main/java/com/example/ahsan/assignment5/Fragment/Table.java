package com.example.ahsan.assignment5.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahsan.assignment5.AstronomicalCalendar;
import com.example.ahsan.assignment5.GeoLocation;
import com.example.ahsan.assignment5.Preference;
import com.example.ahsan.assignment5.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by AHSAN on 10/25/2017.
 */

public class Table extends Fragment implements View.OnClickListener {
    /** Called when the activity is first created. */

    TextView cityname,sunrise,sunset,date;

    Date sd,ed;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_table, container, false);
        DatePicker start = (DatePicker) view.findViewById(R.id.start);
        DatePicker end = (DatePicker) view.findViewById(R.id.end);
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        cityname = (TextView) view.findViewById(R.id.cname);
        start.init(year,month,day,dateChangeHandler); // setup initial values and reg. handler
        end.init(year,month,day,dateChangeHandler);
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH,month);
        cal.set(Calendar.DAY_OF_MONTH,day);
        updateTime(cal.getTime(),cal.getTime(),view);
        return view;
    }

    @Override
    public void onClick(View v) {

    }

    private void updateTime(Date startDate, Date endDate,View view)
    {
        Preference pre = new Preference(getActivity());
        TimeZone tz = TimeZone.getTimeZone(pre.getTZ());
        GeoLocation geolocation = new GeoLocation(pre.getNames(), new Double(pre.getLat()), new Double(pre.getLon()), tz);
        AstronomicalCalendar ac = new AstronomicalCalendar(geolocation);
        sd = startDate;
        ed = endDate;
        List<Date> dates = new ArrayList<>();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(sd);
        cityname.setText(pre.getNames()+",AU");
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");
        TableLayout ll = (TableLayout) view.findViewById(R.id.displayLinear);
        ll.removeAllViews();
        while (calendar.getTime().before(ed))
        {
            Date result = calendar.getTime();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            ac.getCalendar().set(year, month, day);
            TableRow row = (TableRow) view.inflate(getContext(),R.layout.table_row,null);
            sunrise = (TextView) row.findViewById(R.id.sunriseTimeTV);
            sunset = (TextView) row.findViewById(R.id.sunsetTimeTV);
            date = (TextView) row.findViewById(R.id.date);

            sunrise.setText(sdf.format(ac.getSunrise()));
            sunset.setText(sdf.format(ac.getSunset()));
            date.setText(sdf2.format(result));
            dates.add(result);
            ll.addView(row);
            calendar.add(Calendar.DATE, 1);
        }
    }

    DatePicker.OnDateChangedListener dateChangeHandler = new DatePicker.OnDateChangedListener()
    {
        public void onDateChanged(DatePicker dp, int year, int monthOfYear, int dayOfMonth)
        {
            int id = dp.getId();
            switch (id){
                case R.id.start:
                    Calendar cal = new GregorianCalendar();
                    cal.set(Calendar.YEAR, year);
                    cal.set(Calendar.MONTH,monthOfYear);
                    cal.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                    updateTime(cal.getTime(),ed,getView());
                    break;
                case R.id.end:
                    Calendar cal2 = new GregorianCalendar();
                    cal2.set(Calendar.YEAR, year);
                    cal2.set(Calendar.MONTH,monthOfYear);
                    cal2.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                    updateTime(sd,cal2.getTime(),getView());
                    break;
            }

        }
    };

}
