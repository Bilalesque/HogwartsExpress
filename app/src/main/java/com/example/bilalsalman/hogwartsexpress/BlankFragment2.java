package com.example.bilalsalman.hogwartsexpress;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment2 extends Fragment implements View.OnClickListener {

    public BlankFragment2() {
        // Required empty public constructor
    }

    private CardView passengerList,bills,update;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.fragment_blank_fragment2, container, false);
        // Define Cards
        passengerList = (CardView) myView.findViewById(R.id.passengerlistId);
        bills = (CardView) myView.findViewById(R.id.checkbillId);

        // Add Click Listener to the Cards
        passengerList.setOnClickListener(this);
        bills.setOnClickListener(this);

        return myView;
    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch(v.getId())
        {
            case R.id.passengerlistId:
                i = new Intent(getActivity(), PassengerList.class);
                startActivity(i);
                break;
            case R.id.checkbillId:
                i = new Intent(getActivity(), passengerViewAll.class);
                startActivity(i);
                break;
        }
    }
}
