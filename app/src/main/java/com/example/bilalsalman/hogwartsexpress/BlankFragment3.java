package com.example.bilalsalman.hogwartsexpress;



import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment3 extends Fragment implements View.OnClickListener{


    private CardView addemployeeid, employeesalaryid, deleteemployeeid;

    public BlankFragment3() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_blank_fragment3, container, false);

        addemployeeid = (CardView) v.findViewById(R.id.addemployeeId);
        employeesalaryid = (CardView) v.findViewById(R.id.employeesalaryId);

        addemployeeid.setOnClickListener(this);
        employeesalaryid.setOnClickListener(this);


        return v;
    }

    @Override
    public void onClick(View view) {
        Intent i;

        switch(view.getId())
        {
            case R.id.addemployeeId:
                i = new Intent(getActivity(),addemployeeactivity.class);
                startActivity(i);
                break;
            case R.id.employeesalaryId:
                i = new Intent(getActivity(),employeeviewactivity.class);
                startActivity(i);
                break;
        }
    }
}
