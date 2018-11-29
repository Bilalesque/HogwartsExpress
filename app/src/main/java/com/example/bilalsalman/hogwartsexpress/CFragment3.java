package com.example.bilalsalman.hogwartsexpress;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.Button;
import android.widget.Toast;
import android.widget.RadioGroup;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class CFragment3 extends Fragment {

    RadioButton cash,credit;
    private RadioGroup radioGroup;
    boolean c,cr;
    DatabaseReference dbref;
    Button agree,done;
    String Dest1,Source1;
    DatabaseReference userdata;
    Integer y,m,d,tb,te,t,c1;

    public CFragment3() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_cfragment3, container, false);



        radioGroup = (RadioGroup)v.findViewById(R.id.radio);
        cash = (RadioButton) v.findViewById(R.id.cash);
        credit = (RadioButton) v.findViewById(R.id.cc);
        agree = (Button) v.findViewById(R.id.agree);
        done = (Button) v.findViewById(R.id.Done);


        Bundle bundle = this.getArguments();
        if (bundle != null) {
            y = bundle.getInt("Year", 0); // Key, default value
            m = bundle.getInt( "Month", 0);
            d = bundle.getInt(  "Day", 0);
            tb = bundle.getInt( "totalb", 0);
            te = bundle.getInt( "totale",  0);
            t = bundle.getInt( "total", 0);
            c1 = bundle.getInt( "count", 0);

            Dest1 = bundle.getString("Dest",""); // Key, default value
            Source1 = bundle.getString( "source","");

        }

        userdata = FirebaseDatabase.getInstance().getReference("Tickets");

addemployeetofirebase();
        return v;
    }

    private void addemployeetofirebase() {

        String id = userdata.push().getKey();
        ticketData obj = new ticketData(y,m,d,tb,te,t,c1,Dest1,Source1,id);
        userdata.child(id).setValue(obj);

        Toast.makeText(getContext(), "Ticket Booked", Toast.LENGTH_SHORT).show();

    }


        }
