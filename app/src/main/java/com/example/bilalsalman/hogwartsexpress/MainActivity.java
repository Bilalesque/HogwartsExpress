package com.example.bilalsalman.hogwartsexpress;

import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.view.View;
import android.content.Intent;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {

    Button signin,signup,About;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();


        signin = findViewById(R.id.signinHOME);
        signup = findViewById(R.id.signupHOME);
        About =  findViewById(R.id.about);


        signin.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
                Intent myIntent = new Intent(MainActivity.this, Login.class);
                startActivity(myIntent);
            }
        });

        signup.setOnClickListener(new OnClickListener(){
            public void onClick(View arg0){
                Intent myIntent = new Intent(MainActivity.this, SignUp.class);
                startActivity(myIntent);
            }
        });

        About.setOnClickListener(new OnClickListener(){
            public void onClick(View arg0){
                Intent i = new Intent(getApplicationContext(), aboutMain.class);
                startActivity(i);

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

    }

}
