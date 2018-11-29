package com.example.bilalsalman.hogwartsexpress;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PassengerList extends AppCompatActivity {


    private ListView mListView;
    DatabaseReference dbref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_list);

        mListView = (ListView) findViewById(R.id.listcustview);
        dbref = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    protected void onStart() {
        super.onStart();
        //attaching value event listener
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                DataSnapshot contactSnapshot = dataSnapshot.child("Users");
                Iterable<DataSnapshot> contactChildren = contactSnapshot.getChildren();
                ArrayList<UserDataForFireBase> contacts= new ArrayList<>();

                for (DataSnapshot contact : contactChildren) {
                    UserDataForFireBase c = contact.getValue(UserDataForFireBase.class);
                    contacts.add(c);
                }

                //creating adapter
               userdisplayadapter artistAdapter = new userdisplayadapter(PassengerList.this, contacts);
                //attaching adapter to the listview
                mListView.setAdapter(artistAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}