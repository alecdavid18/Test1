package com.example.student.test;

import android.annotation.SuppressLint;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;

public class RouteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route);
        final TextView distance=(TextView)findViewById(R.id.DistanceTextView);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference();

final float results[]=new float[1];

       myRef.addListenerForSingleValueEvent(new ValueEventListener() {
           @SuppressLint("SetTextI18n")
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               String lat1A=dataSnapshot.child("bus").child("bus1").child("lat").getValue().toString();
               String long1A=dataSnapshot.child("bus").child("bus1").child("long").getValue().toString();
               String lat1B=dataSnapshot.child("busstops").child("aluva").child("lattitude").getValue().toString();
               String long1B=dataSnapshot.child("busstops").child("aluva").child("longitude").getValue().toString();

               double latA=Double.parseDouble(lat1A);
               double longA=Double.parseDouble(long1A);
               double latB=Double.parseDouble(lat1B);
               double longB=Double.parseDouble(long1B);
               //double earthRadius = 3958.75;
               //double dLat = Math.toRadians(latA-latB);
              // double dLng = Math.toRadians(longA-longB);
              // double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                //       Math.cos(Math.toRadians(latB)) * Math.cos(Math.toRadians(latA)) *
                 //              Math.sin(dLng/2) * Math.sin(dLng/2);
               //double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
               //final double dist = earthRadius * c;
               //int distance1=(int)dist;
             // distance.setText(Integer.toString(distance1));
                Location.distanceBetween(latA,longA,latB,longB,results);
               String distinkm=Arrays.toString(results);



                distance.setText((distinkm));


           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {

           }
       });






    }
}
