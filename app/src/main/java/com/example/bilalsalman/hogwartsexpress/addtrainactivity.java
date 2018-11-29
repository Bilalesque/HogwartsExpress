package com.example.bilalsalman.hogwartsexpress;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class addtrainactivity extends AppCompatActivity  implements View.OnClickListener{

  EditText manuName,OpName,NumCar,BusCap,EcoCsp;
  String MN,ON,NC,RI,BC,EC;
  DatabaseReference traindata;
  Spinner routeid;
  DatabaseReference dbref;
  List<String> list;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_addtrainactivity);
      list = new ArrayList<String>();
      traindata = FirebaseDatabase.getInstance().getReference("Train");
      dbref = FirebaseDatabase.getInstance().getReference();

      findViewById(R.id.OkbuttonID).setOnClickListener(this);
      findViewById(R.id.CancelbuttonID).setOnClickListener(this);


      dbref.child("Routes").addValueEventListener(new ValueEventListener() {
          @Override
          public void onDataChange(DataSnapshot dataSnapshot) {

              for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {
                  String areaName = areaSnapshot.child("id").getValue(String.class);
                  list.add(areaName);
              }
          }

          @Override
          public void onCancelled(DatabaseError databaseError) {

          }
      });

      list.add("");

      routeid = (Spinner) findViewById(R.id.RuutID);
      //Toast.makeText(getBaseContext(), "Route : " + list, Toast.LENGTH_LONG).show();

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(addtrainactivity.this, android.R.layout.simple_spinner_item, list);
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
  }

          @Override
  public void onClick(View v) {

    switch (v.getId()) {
      case R.id.OkbuttonID:
        if(checkvariables())
          addTRAINtofirebase();
        break;
        case R.id.CancelbuttonID:
        {
            finish();
            Intent intent = new Intent(addtrainactivity.this, addtrainactivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            break;
        }
    }
  }


  private boolean checkvariables() {


      manuName=(EditText)findViewById(R.id.ManufacturerID);
    OpName=(EditText)findViewById(R.id.OperatorID);
    NumCar=(EditText)findViewById(R.id.NumberOfCarriagesID);
    BusCap=(EditText)findViewById(R.id.businessCapID);
    EcoCsp=(EditText)findViewById(R.id.EcoCapID);

    MN= manuName.getText().toString().trim();
    ON= OpName.getText().toString().trim();
    NC= NumCar.getText().toString().trim();

      BC= BusCap.getText().toString().trim();
    EC= EcoCsp.getText().toString().trim();

    if (MN.isEmpty()) {
      manuName.setError("Manufacturer's Name is required");
      manuName.requestFocus();
      return false;
    }

    if (RI.isEmpty()) {

        Toast.makeText(getApplicationContext(), "Route ID is required", Toast.LENGTH_SHORT).show();
        routeid.requestFocus();
      return false;
    }

    if (ON.isEmpty()) {
      OpName.setError("Operator's Name is required");
      OpName.requestFocus();
      return false;
    }

    return true;

  }

  private void addTRAINtofirebase() {

    String id = traindata.push().getKey();
    AddTrain obj = new AddTrain(id,MN,ON,RI,BC,EC,NC);
    traindata.child(id).setValue(obj);
    Toast.makeText(addtrainactivity.this, "Train Added", Toast.LENGTH_SHORT).show();
    startActivity(new Intent(this, addtrainactivity.class));

  }
}