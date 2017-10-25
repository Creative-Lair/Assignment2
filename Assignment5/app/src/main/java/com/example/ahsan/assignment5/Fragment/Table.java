package com.example.ahsan.assignment5.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ahsan.assignment5.R;

/**
 * Created by AHSAN on 10/25/2017.
 */

public class Table extends Fragment implements View.OnClickListener {
    /** Called when the activity is first created. */



    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_main, container, false);
        return view;
    }

    @Override
    public void onClick(View v) {

    }

//    private void initializeUI(View view)
//    {
//        DatePicker dp = (DatePicker) view.findViewById(R.id.datePicker);
//        Calendar cal = Calendar.getInstance();
//        int year = cal.get(Calendar.YEAR);
//        int month = cal.get(Calendar.MONTH);
//        int day = cal.get(Calendar.DAY_OF_MONTH);
//
//        add = (Button) view.findViewById(R.id.addstate);
//        list =(Button) view.findViewById(R.id.liststate);
//        cityname = (TextView) view.findViewById(R.id.cname);
//        dp.init(year,month,day,dateChangeHandler); // setup initial values and reg. handler
//        updateTime(year, month, day, view);
//        add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent myIntent = new Intent(getActivity(), UpdateAcitvity.class);
//                getActivity().startActivity(myIntent);
//            }
//        });
//        list.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent myIntent = new Intent(getActivity(), ListActivity.class);
//                getActivity().startActivity(myIntent);
//            }
//        });
//    }
//
//    private void updateTime(int year, int monthOfYear, int dayOfMonth,View view)
//    {
//        Preference pre = new Preference(getActivity());
//        TimeZone tz = TimeZone.getTimeZone(pre.getTZ());
//        GeoLocation geolocation = new GeoLocation(pre.getNames(), new Double(pre.getLat()), new Double(pre.getLon()), tz);
//        AstronomicalCalendar ac = new AstronomicalCalendar(geolocation);
//        ac.getCalendar().set(year, monthOfYear, dayOfMonth);
//        Date srise = ac.getSunrise();
//        Date sset = ac.getSunset();
//        cityname.setText(pre.getNames()+",AU");
//        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
//
//        TextView sunriseTV = (TextView) view.findViewById(R.id.sunriseTimeTV);
//        TextView sunsetTV = (TextView) view.findViewById(R.id.sunsetTimeTV);
//        Log.d("SUNRISE Unformatted", srise+"");
//
//        sunriseTV.setText(sdf.format(srise));
//        sunsetTV.setText(sdf.format(sset));
//    }
//
//    DatePicker.OnDateChangedListener dateChangeHandler = new DatePicker.OnDateChangedListener()
//    {
//        public void onDateChanged(DatePicker dp, int year, int monthOfYear, int dayOfMonth)
//        {
//            updateTime(year, monthOfYear, dayOfMonth,getView());
//        }
//    };
//    @Override
//    public void onClick(View v) {
//
//    }
}
