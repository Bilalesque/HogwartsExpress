package com.example.bilalsalman.hogwartsexpress;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.FragmentTransaction;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class Cfragment2 extends Fragment {

    EditText nep,nbp;
    Button update;
    Button update2;
    int ep=0,bp=0;
    Calendar myCalendar;
String Dest1,Source1;

    public Cfragment2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment





        View v = inflater.inflate(R.layout.fragment_cfragment2, container, false);


        Bundle bundle1 = this.getArguments();
        if (bundle1 != null) {
            Dest1 = bundle1.getString("Dest",""); // Key, default value
            Source1 = bundle1.getString( "source","");
        }


        myCalendar = Calendar.getInstance();
        nep = (EditText) v.findViewById(R.id.custeco);
        nbp = (EditText) v.findViewById(R.id.custbusinees);
        update = (Button) v.findViewById(R.id.button3);
        update2 = (Button) v.findViewById(R.id.button4);

        update2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(getActivity(), customernavigation.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                if(validate())
                {

                int totalb = 6000* bp;
                int totala = 3000 * ep;
                int total = totalb + totala;


                    Bundle bundle = new Bundle();
                    bundle.putInt("Year", Calendar.YEAR);
                    bundle.putInt("Month", Calendar.MONTH);
                    bundle.putInt("Day", Calendar.DAY_OF_MONTH);

                    bundle.putInt("totalb",totalb);
                    bundle.putInt("totale",totala);
                    bundle.putInt("total",total);
                    bundle.putInt("count",bp+ep);


                    bundle.putString("Dest",Dest1);
                    bundle.putString("Source",Source1);



                    CFragment3 nextFragment = new CFragment3();
                    nextFragment.setArguments(bundle);



                    Toast.makeText(getContext(), "Total Bill: "+ total, Toast.LENGTH_LONG).show();

                }

            }
        });



        return v;
    }

    private boolean validate()
    {

        String a= nbp.getText().toString().trim();
        String b= nep.getText().toString().trim();

        if(a.isEmpty() && b.isEmpty())
        {
            nbp.setError("Required");
            nbp.requestFocus();
            return false;
        }


        if(!a.isEmpty())
            bp= Integer.parseInt(a);
        if(!b.isEmpty())
            ep= Integer.parseInt(b);

            return true;

    }

}
