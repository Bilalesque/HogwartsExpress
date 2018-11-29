package com.example.bilalsalman.hogwartsexpress;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment implements View.OnClickListener{


    public BlankFragment() {
        // Required empty public constructor
    }

    private CardView trainviewid, routeviewid, addtrainid, addrouteid, trainundermainid;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_blank, container, false);

        trainviewid = (CardView) v.findViewById(R.id.trainviewId);
        routeviewid = (CardView) v.findViewById(R.id.routeviewId);
        addtrainid = (CardView) v.findViewById(R.id.addtrainId);
        addrouteid = (CardView) v.findViewById(R.id.addrouteId);
        //trainundermainid = (CardView) v.findViewById(R.id.trainundermaintainanceId);

        trainviewid.setOnClickListener(this);
        routeviewid.setOnClickListener(this);
        addrouteid.setOnClickListener(this);
        addtrainid.setOnClickListener(this);

        return v;
    }


    @Override
    public void onClick(View view) {
        Intent i;

        switch(view.getId())
        {
            case R.id.trainviewId:
               i = new Intent(getActivity(),trainviewactivity.class);
               startActivity(i);
                break;
            case R.id.routeviewId:
                i = new Intent(getActivity(),routeviewactivity.class);
                startActivity(i);
                break;
            case R.id.addtrainId:
                i = new Intent(getActivity(),addtrainactivity.class);
                startActivity(i);
                break;
            case R.id.addrouteId:
                i = new Intent(getActivity(),addrouteactivity.class);
                startActivity(i);
                break;
        }



    }
}
