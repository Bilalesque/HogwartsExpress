package com.example.bilalsalman.hogwartsexpress;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

public class trainviewactivity extends AppCompatActivity {

    private ListView mListView;
    DatabaseReference dbref;
    ArrayList<AddTrain> contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainviewactivity);
        mListView = (ListView) findViewById(R.id.listtrainview);
        dbref = FirebaseDatabase.getInstance().getReference();

        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                AddTrain train = contacts.get(i);
                showUpdateDeleteDialog(train.getTainNumber(),train.getRouteID());
                return true;
            }
        });



    }


    @Override
    protected void onStart() {
        super.onStart();
        //attaching value event listener
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                DataSnapshot contactSnapshot = dataSnapshot.child("Train");
                Iterable<DataSnapshot> contactChildren = contactSnapshot.getChildren();
                contacts= new ArrayList<>();

                for (DataSnapshot contact : contactChildren) {
                    AddTrain c = contact.getValue(AddTrain.class);
                    contacts.add(c);
                }

                //creating adapter
                trainviewadapter artistAdapter = new trainviewadapter(trainviewactivity.this, contacts);
                //attaching adapter to the listview
                mListView.setAdapter(artistAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }


    private void showUpdateDeleteDialog(final String trainId, final String routeid) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.activity_delete_dialog_box, null);
        dialogBuilder.setView(dialogView);

        final Button buttonDelete = (Button) dialogView.findViewById(R.id.delbuttton);

        dialogBuilder.setTitle("Delete this Train: "+trainId);
        final AlertDialog b = dialogBuilder.create();
        b.show();


        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                deleteroute(trainId,routeid);
                b.dismiss();

            }
        });
    }

    private boolean deleteroute(String trainid, String routeId) {

        DatabaseReference routesremove = FirebaseDatabase.getInstance().getReference("Train").child(trainid);

        routesremove.removeValue();

      DatabaseReference trainremove = FirebaseDatabase.getInstance().getReference("Routes").child(routeId);

              trainremove.removeValue();
        Toast.makeText(getApplicationContext(), "Train Deleted.", Toast.LENGTH_LONG).show();

        return true;

    }





}
