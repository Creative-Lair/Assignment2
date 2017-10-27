package com.example.ahsan.assignment5.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ahsan.assignment5.Preference;
import com.example.ahsan.assignment5.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by AHSAN on 10/25/2017.
 */

public class Map extends Fragment {
    private GoogleMap mMap;
    private MapView mapView;
    private Preference preference;
    private String smsBody;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_map, container, false);
        mapView = (MapView) view.findViewById(R.id.fragment_view_map);
        preference = new Preference(getActivity());
        MapsInitializer.initialize(getActivity());
        if(mapView!=null){
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {
                    mMap = googleMap;

                    // Add a marker in Sydney and move the camera
                    LatLng sydney = new LatLng(Float.parseFloat(preference.getLat()), Float.parseFloat(preference.getLon()));
                    mMap.addMarker(new MarkerOptions().position(sydney).title(preference.getNames()+",AU"));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
                    smsBody = "http://maps.google.com?q="+ preference.getLat() + "," + preference.getLon();
                    URL domain = null;
                    try {
                        domain = new URL(smsBody);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    preference.setSmsBody(domain.toString());
                }
            });

        }
        return view;
    }

}
