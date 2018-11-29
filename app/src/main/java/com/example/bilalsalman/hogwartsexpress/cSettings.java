package com.example.bilalsalman.hogwartsexpress;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class cSettings extends AppCompatActivity {

    EditText ops,nps;
    Button update;
    Button update2;
    String op,np,id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_settings);

        ops=(EditText)findViewById(R.id.updatepsold);
        nps=(EditText)findViewById(R.id.updatepsnew);


        update= (Button) findViewById(R.id.updatebutt);
        update2= (Button) findViewById(R.id.settingshome);


        update2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                finish();

                Intent i = new Intent(getBaseContext(), customernavigation.class);
                startActivity(i);

            }
        });



                update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (assignVariables()) {
                    //enter the user id into database

                    if(!op.isEmpty())
                    {

                        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        id=user.getEmail();
                        AuthCredential credential = EmailAuthProvider.getCredential(user.getEmail(), op);

                        user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {

                                            user.updatePassword(np).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful())
                                                    {
                                                        Toast.makeText(getApplicationContext(), "Password Updated", Toast.LENGTH_LONG).show();
                                                    }
                                                    else
                                                        {
                                                            Toast.makeText(getApplicationContext(), "Error Occurred", Toast.LENGTH_LONG).show();

                                                    }
                                                }
                                            });
                                        }
                                        else
                                            {
                                                Toast.makeText(getApplicationContext(), "Wrong Old Password provided.", Toast.LENGTH_LONG).show();

                                            }
                                    }
                                });

                    }

                }
            }
        });
    }

    private boolean assignVariables() {

        op= ops.getText().toString().trim();
        np= nps.getText().toString().trim();

        if(!op.isEmpty() && np.isEmpty())
        {
            ops.setError("Old Password is required");
            ops.requestFocus();
            return false;
        }
        else if(op.isEmpty() && !np.isEmpty() )
        {
            nps.setError("New password is required.");
            nps.requestFocus();
            return false;
        }
        else
            return true;

    }
}
