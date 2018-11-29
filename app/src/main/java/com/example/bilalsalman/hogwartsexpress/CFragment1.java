package com.example.bilalsalman.hogwartsexpress;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class CFragment1 extends Fragment {

    SearchView dep,arr;
    //View view;\
    Button button;
    ArrayList<String> list;
    RecyclerView rv;
    DatabaseReference dbref;
    LocationAdapter locadapt;
    Spinner routeid;
    List<String> list1;
String RI,RI1;

    public CFragment1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_cfragment1, container, false);

        Intent i = new Intent(getActivity(),routeviewactivity.class);
        startActivity(i);

        list = new ArrayList<String>();

        //do shit here

        list1 = new ArrayList<String>();

        dep=view.findViewById(R.id.searchView);

        button = (Button) view.findViewById(R.id.nextbutton);

        list1.add("lahore");
        list1.add("karachi");
        list1.add("multan");
        list1.add("peshawar");
        list1.add("quetta");
        list1.add("islamabad");


        routeid = (Spinner) view.findViewById(R.id.spinner);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, list1);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        routeid.setAdapter(dataAdapter);

        routeid.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                RI = parent.getItemAtPosition(position).toString();
                //Toast.makeText(getBaseContext(), "Route ID: " + parent.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        rv = view.findViewById(R.id.deplist);

        dbref= FirebaseDatabase.getInstance().getReference();

        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        rv.addItemDecoration(new DividerItemDecoration(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL));

        dep.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(!query.isEmpty())
                    setAdapter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                if(!newText.isEmpty())
                {
                    setAdapter(newText);
                    RI1 = newText;
                }

                return false;
            }
        });



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Bundle bundle = new Bundle();
                bundle.putString("Dest", RI);
                bundle.putString("Source", RI1);




                Toast.makeText(getContext(), "Departure: "+ RI+" Source: "+RI1, Toast.LENGTH_LONG).show();




            }
        });



                return view;
    }

    private void setAdapter(final String query)
    {

        dbref.child("Routes").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                list.clear();
                rv.removeAllViews();

                for (DataSnapshot snap:dataSnapshot.getChildren())
                {
                    String hold=snap.child("start").getValue(String.class);
                    if (hold.contains(query)) {
                        if(!list.contains(hold))
                            list.add(hold);
                    }

                    hold=snap.child("stop1").getValue(String.class);
                    if (hold.contains(query)) {
                        if(!list.contains(hold))
                            list.add(hold);
                    }

                    hold=snap.child("stop2").getValue(String.class);
                    if (hold.contains(query)) {
                        if(!list.contains(hold))
                            list.add(hold);
                    }

                    hold=snap.child("stop3").getValue(String.class);
                    if (hold.contains(query)) {
                        if(!list.contains(hold))
                            list.add(hold);
                    }

                    hold=snap.child("stop4").getValue(String.class);
                    if (hold.contains(query)) {
                        if(!list.contains(hold))
                            list.add(hold);
                    }

                    hold=snap.child("stop5").getValue(String.class);
                    if (hold.contains(query)) {
                        if(!list.contains(hold))
                            list.add(hold);
                    }

                }

                locadapt=new LocationAdapter(getActivity().getApplicationContext(),list);
                rv.setAdapter(locadapt);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
