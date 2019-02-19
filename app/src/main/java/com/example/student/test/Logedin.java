package com.example.student.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Logedin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logedin);
        final Intent maps =new Intent(Logedin.this,MapsActivity.class);
        final Intent busstop= new Intent(Logedin.this,BusstopActivity.class);
        Button busstops=(Button)findViewById(R.id.BusStopButton);
        final Button location=(Button)findViewById(R.id.LocationButton);
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(maps);
            }
        });
        busstops.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        startActivity(busstop);
    }
});


    }
}
