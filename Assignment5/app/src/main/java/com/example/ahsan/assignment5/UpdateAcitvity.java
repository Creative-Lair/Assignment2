package com.example.ahsan.assignment5;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class UpdateAcitvity extends AppCompatActivity {
    private TextView name;
    private TextView lat;
    private TextView lon;
    private TextView TZ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
         name = (TextView) findViewById(R.id.cityname);
         lat = (TextView) findViewById(R.id.lat);
         lon = (TextView) findViewById(R.id.lon);
         TZ = (TextView) findViewById(R.id.TZ);
        Button adder = (Button) findViewById(R.id.addcity);

        adder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText() != null && lat.getText() != null && lon.getText() != null && TZ.getText() != null)
                {
                    updateCity();
                    Intent myIntent = new Intent(UpdateAcitvity.this, MainActivity.class);
                    UpdateAcitvity.this.startActivity(myIntent);
                }
            }
        });


    }
    public void updateCity()
    {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(","+name.getText()+",");
            stringBuilder.append(lat.getText()+",");
            stringBuilder.append(lon.getText()+",");
            stringBuilder.append(TZ.getText());
            FileOutputStream fos = null;
            fos = openFileOutput("au_locations.csv", Context.MODE_APPEND);
            String data = stringBuilder.toString();
            fos.write(data.getBytes());
            fos.close();
            Toast.makeText(this, "data updated", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
