package com.example.bilalsalman.hogwartsexpress;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class SignUp extends AppCompatActivity{
    FirebaseUser user;



    public static final int PICK_IMAGE = 100;

    String f,l,cn,em,cc,p,a;
    EditText fname,lname,cnic,email,cc_no,addr,phone;
    TextView sgnup;
    Button uploadimage;
    ImageView imageview;
    Uri imageUri,imagUri2;


    FirebaseAuth auth;
    DatabaseReference userdata;
    FirebaseStorage fstor;
    Button ok,cencel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        userdata = FirebaseDatabase.getInstance().getReference("Users");
        auth = FirebaseAuth.getInstance();
        fstor = FirebaseStorage.getInstance();

        //StorageReference stor = fstor.getReference().child(auth.getUid());

        ok= (Button) findViewById(R.id.OkSignup);
        cencel= (Button) findViewById(R.id.cancelSignup);

        uploadimage = findViewById((R.id.uploadimage));
        imageview = findViewById(R.id.imageView);

        uploadimage.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
              openGallery();
            }
        });

        ok.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(assignVariables()){
                    //enter the user id into database

                auth.createUserWithEmailAndPassword(em,a).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                createuser();
                                sendEmail();
                                Toast.makeText(SignUp.this, "Registration Successful!", Toast.LENGTH_SHORT).show();
                                Intent myIntent = new Intent(SignUp.this, customernavigation.class);
                                startActivity(myIntent);

                            }
                            else {
                                if (task.getException() instanceof FirebaseAuthUserCollisionException)
                                    Toast.makeText(SignUp.this, "Email ID already registered", Toast.LENGTH_SHORT).show();
                                else
                                    Toast.makeText(SignUp.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


               }
            }
        });

        cencel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(),SignUp.class);
                startActivity(i);
            }
        });



            }

    private void sendEmail() {

        user = FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null)
        {

            user.sendEmailVerification()
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(SignUp.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

        }
        else
            Toast.makeText(SignUp.this,"ASassa", Toast.LENGTH_SHORT).show();


    }

        private void createuser()
    {
        String id = userdata.push().getKey();
        UserDataForFireBase obj = new UserDataForFireBase(f,l,cn,em,cc,p,a,id);
        userdata.child(id).setValue(obj);
        if(hasImage(imageview))
        {
            StorageReference stor = fstor.getReference().child(em);
            UploadTask ut = stor.putFile(imageUri);
        }
    }
    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void openGallery(){

         Intent myIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
            startActivityForResult(myIntent, PICK_IMAGE);
    }

    private boolean assignVariables()
    {
        fname=(EditText)findViewById(R.id.fname);
        lname=(EditText)findViewById(R.id.lname);
        //cnic,email,cc_no,addr;

        cnic=(EditText)findViewById(R.id.cnic);
        email=(EditText)findViewById(R.id.email);
        cc_no=(EditText)findViewById(R.id.cc_no);
        phone=(EditText)findViewById(R.id.ph);
        addr=(EditText)findViewById(R.id.addr);


        f = fname.getText().toString().trim();
        l= lname.getText().toString().trim();
        cn= cnic.getText().toString().trim();
        em= email.getText().toString().trim();
        cc= cc_no.getText().toString().trim();
        p= phone.getText().toString().trim();
        a= addr.getText().toString().trim();


        if (f.isEmpty()) {
            fname.setError("FirstName is required");
            fname.requestFocus();
            return false;
        }

        if(!isEmailValid(em))
        {
            email.setError("Email address not valid");
            email.requestFocus();
            return false;
        }

        if (l.isEmpty()) {
            lname.setError("LastName is required");
            lname.requestFocus();
            return false;
        }

        if (cn.isEmpty()) {
            cnic.setError("CNIC is required");
            cnic.requestFocus();
            return false;
        }

        if (em.isEmpty()) {
            email.setError("Email is required");
            email.requestFocus();
            return false;
        }

        if (cc.isEmpty()) {
            cc_no.setError("CreditCard no is required");
            cc_no.requestFocus();
            return false;
        }

        if (p.isEmpty()) {
            phone.setError("Phone Number is required");
            phone.requestFocus();
            return false;
        }

        if (a.isEmpty()) {
            addr.setError("Password is required");
            addr.requestFocus();
            return false;
        }

            return true;


    }

    @Override
    protected void onActivityResult(int RequestCode, int ResultCode, Intent data)
    {
        super.onActivityResult(RequestCode,ResultCode,data);
        if(ResultCode == RESULT_OK && RequestCode == PICK_IMAGE)
        {
            imageUri = data.getData();
            //System.out.print(imageUri.getPath());
            imageview.setImageURI(imageUri);
            try {
                Bitmap img = MediaStore.Images.Media.getBitmap(getContentResolver(),imageUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean hasImage(@NonNull ImageView view) {
        Drawable drawable = view.getDrawable();
        boolean hasImage = (drawable != null);

        if (hasImage && (drawable instanceof BitmapDrawable)) {
            hasImage = ((BitmapDrawable)drawable).getBitmap() != null;
        }

        return hasImage;
    }
}
