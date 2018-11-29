package com.example.bilalsalman.hogwartsexpress;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class addrouteactivity extends AppCompatActivity implements View.OnClickListener{


    EditText start, stop1, stop2, stop3, stop4, stop5, trackid, dest;
    String st,s1,s2,s3,s4,s5,tid,de;


    DatabaseReference routedata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addrouteactivity);

        routedata = FirebaseDatabase.getInstance().getReference("Routes");
        findViewById(R.id.addroutebutton2).setOnClickListener(this);
        findViewById(R.id.cancelroutebutton).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.addroutebutton2:
                if(checkvariables())
                    addroutetofirebase();
                break;

            case R.id.cancelroutebutton:
            {
                finish();
                Intent intent = new Intent(addrouteactivity.this, addrouteactivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
            }
        }
    }

    private boolean checkvariables() {

        start=(EditText)findViewById(R.id.routesource);
        stop1=(EditText)findViewById(R.id.routestop1);
        stop2=(EditText)findViewById(R.id.routestop2);
        stop3=(EditText)findViewById(R.id.routestop3);
        stop4=(EditText)findViewById(R.id.routestop4);
        stop5=(EditText)findViewById(R.id.routestop5);
        trackid=(EditText)findViewById(R.id.routetrackid);
        dest=(EditText)findViewById(R.id.routedest);

        s1=null;
        s2=null;
        s3=null;
        s4=null;
        s5=null;


        st = start.getText().toString().trim();
        s1= stop1.getText().toString().trim();
        s2= stop2.getText().toString().trim();
        s3= stop3.getText().toString().trim();
        s4= stop4.getText().toString().trim();
        s5= stop5.getText().toString().trim();
        tid= trackid.getText().toString().trim();
        de= dest.getText().toString().trim();

        if (st.isEmpty()) {
            start.setError("Start Location is required");
            start.requestFocus();
            return false;
        }

        if (tid.isEmpty()) {
            trackid.setError("TrackID is required");
            trackid.requestFocus();
            return false;
        }

        if (de.isEmpty()) {
            dest.setError("Destination is required");
            dest.requestFocus();
            return false;
        }



        return true;

    }

    private void addroutetofirebase() {

        String id = routedata.push().getKey();
        RouteObjForFireBase obj = new RouteObjForFireBase(st,s1,s2,s3,s4,s5,tid,de,id);
        routedata.child(id).setValue(obj);
        Toast.makeText(addrouteactivity.this, "Route Added", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, addrouteactivity.class));

    }
}
