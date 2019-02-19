package com.example.student.test;

import android.content.Intent;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BusstopActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busstop);

       final Intent maps=new Intent(BusstopActivity.this,MapsActivity.class);
        final long[] mLastClickTime = {0};
        final Button location=(Button)findViewById(R.id.LocationButton);
        final Intent Route=new Intent(BusstopActivity.this,RouteActivity.class);
        final Button stop1=(Button)findViewById(R.id.stop1Button);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference();
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(maps);
            }
        });
        stop1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SystemClock.elapsedRealtime() - mLastClickTime[0] < 1000000){
                    return;
                }
                mLastClickTime[0] = SystemClock.elapsedRealtime();
                myRef.child("busstops").child(stop1.getText().toString()).child("count").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        String noofpeople = dataSnapshot.getValue().toString();
                        int count=Integer.parseInt(noofpeople);
                        count=count+1;
                        myRef.child("busstops").child(stop1.getText().toString()).child("count").setValue(count);
                        startActivity(Route);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });


    }

}
