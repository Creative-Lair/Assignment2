package com.example.ahsan.assignment5;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.example.ahsan.assignment5.R.raw.au_locations;

public class ListActivity extends AppCompatActivity {

    private ListView listView;
    private ListAdapter adapter;
    List<Cities> city= new ArrayList<Cities>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        listView = (ListView) findViewById(R.id.listview);
        String FILENAME = "au_locations.csv";


        try {
            BufferedReader reader = new BufferedReader(
            new InputStreamReader(getResources().openRawResource(au_locations) ));

            String receiveString = "";
            StringBuilder stringBuilder = new StringBuilder();

            while ( (receiveString = reader.readLine()) != null ) {
                stringBuilder.append(receiveString);
            }
            FileOutputStream fos = null;
            fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            String data = stringBuilder.toString();
            fos.write(data.getBytes());
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
         catch (IOException e) {
            e.printStackTrace();
        }
        String string = readFromFile();
        //Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
        adapter = new ListAdapter(this,au_locations,city);
       listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Preference pre= new Preference(ListActivity.this);
                pre.updateNames(city.get(position).getName());
                pre.updateLat(city.get(position).getLat());
                pre.updateLon(city.get(position).getLon());
                pre.updateTZ(city.get(position).getTZ());
                Intent myIntent = new Intent(ListActivity.this, MainActivity.class);
                ListActivity.this.startActivity(myIntent);
            }
        });
    }

    private String readFromFile() {

        String ret = "";

        try {
            InputStream inputStream = this.openFileInput("au_locations.csv");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
                String[] newone = ret.split(",");
                System.out.println(newone.length+""+newone[newone.length-1]);
                for(int i=0;i<newone.length;i+=4)
                {

                    city.add(new Cities(newone[i],newone[i+1],newone[i+2],newone[i+3]));
                }
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return ret;
    }
}
