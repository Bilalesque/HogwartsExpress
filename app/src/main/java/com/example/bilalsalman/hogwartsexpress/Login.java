package com.example.bilalsalman.hogwartsexpress;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.util.Patterns;
import android.support.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import android.view.View.OnClickListener;

public class Login extends AppCompatActivity implements View.OnClickListener{

    FirebaseAuth mAuth;
    boolean A = false;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.register).setOnClickListener(this);
        findViewById(R.id.signin).setOnClickListener(this);

    }


    private void userLogin() {

        EditText uname = findViewById(R.id.username);
        EditText pass = findViewById(R.id.pass);

        String email = uname.getText().toString();
        String pas = pass.getText().toString();

        if (email.isEmpty()) {
            uname.setError("Email is required");
            uname.requestFocus();
            return;
        }

        if (pas.isEmpty()) {
            pass.setError("Password is required");
            pass.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            uname.setError("Please enter a valid email");
            uname.requestFocus();
            return;
        }

        if(email.equals("admin@gmail.com") && pas.equals("admin123"))
            A = true;

        final boolean finalA = A;
        mAuth.signInWithEmailAndPassword(email, pas).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {

                        if(finalA == true)
                        {
                            finish();
                            Toast.makeText(getApplicationContext(),"Admin Logged In...", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Login.this, navigation.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);

                        }
                        else
                        {
                            finish();
                            Toast.makeText(getApplicationContext(),"Signing Im..", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Login.this, customernavigation.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);

                        }

                        } else {
                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });

    }

    @Override
    protected void onStart() {
        super.onStart();

        if (mAuth.getCurrentUser() != null) {
            finish();
           if(mAuth.getCurrentUser().getEmail().equals("admin@gmail.com"))
                startActivity(new Intent(this, navigation.class));
            else
                startActivity(new Intent(this, customernavigation.class));
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.register:
                finish();
                startActivity(new Intent(this, SignUp.class));
                break;

            case R.id.signin:
                userLogin();
                break;
        }
    }

}
