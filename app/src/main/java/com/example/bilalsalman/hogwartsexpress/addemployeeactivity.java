package com.example.bilalsalman.hogwartsexpress;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class addemployeeactivity extends AppCompatActivity implements View.OnClickListener{

    public static final int PICK_IMAGE = 100;

    String f,l,cn,em,pw,s,d;

    EditText fname,lname,cnic,email,pswd,phone,sal,dept;
    Button uploadimage;

    ImageView imageview;
    Uri imageUri,imagUri2;


    FirebaseAuth auth;
    DatabaseReference userdata;
    FirebaseStorage fstor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addemployeeactivity);

        userdata = FirebaseDatabase.getInstance().getReference("Employees");
        auth = FirebaseAuth.getInstance();
        fstor = FirebaseStorage.getInstance();
        imageview = findViewById(R.id.imageView2);

        findViewById(R.id.OkADDEMP).setOnClickListener(this);
        findViewById(R.id.cancelADDEMP).setOnClickListener(this);
        findViewById(R.id.uploadimageEMP).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.OkADDEMP:
                if(checkvariables())
                    addemployeetofirebase();
                break;

            case R.id.cancelADDEMP:
            {
                finish();
                Intent intent = new Intent(this, addemployeeactivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
            }
            case R.id.uploadimageEMP:
                openGallery();
                break;

        }
    }

    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean checkvariables() {

        fname=(EditText)findViewById(R.id.fnameEMP);
        lname=(EditText)findViewById(R.id.lnameEMP);
        //cnic,email,cc_no,addr;

        cnic=(EditText)findViewById(R.id.cnicEMP);
        email=(EditText)findViewById(R.id.emailEMP);
        phone=(EditText)findViewById(R.id.phEMP);
        sal=(EditText)findViewById(R.id.SalaryEMP);
        dept=(EditText)findViewById(R.id.DepEMP);


        f = fname.getText().toString().trim();
        l= lname.getText().toString().trim();
        cn= cnic.getText().toString().trim();
        em= email.getText().toString().trim();
        pw= pswd.getText().toString().trim();
        s= sal.getText().toString().trim();
        d= dept.getText().toString().trim();


        if (f.isEmpty()) {
            fname.setError("FirstName is required");
            fname.requestFocus();
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

        if(!isEmailValid(em))
        {
            email.setError("Email address not valid");
            email.requestFocus();
            return false;
        }

        if (em.isEmpty()) {
            email.setError("Email is required");
            email.requestFocus();
            return false;
        }

        if (pw.isEmpty()) {
            pswd.setError("Password is required");
            pswd.requestFocus();
            return false;
        }

        if (s.isEmpty()) {
            sal.setError("Salary is required");
            sal.requestFocus();
            return false;
        }

        if (d.isEmpty()) {
            dept.setError("Department is required");
            dept.requestFocus();
            return false;
        }

        return true;


    }

    private void addemployeetofirebase() {

        String id = userdata.push().getKey();
        EmployeeDataForFireBase obj = new EmployeeDataForFireBase(f,l,cn,em,pw,s,d,id);
        userdata.child(id).setValue(obj);
        if(hasImage(imageview))
        {
            StorageReference stor = fstor.getReference().child(em);
            UploadTask ut = stor.putFile(imageUri);
        }
        Toast.makeText(addemployeeactivity.this, "Employee Added succesfully...", Toast.LENGTH_SHORT).show();

    }


    private void openGallery(){

        Intent myIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(myIntent, PICK_IMAGE);
    }

    private boolean hasImage(@NonNull ImageView view) {
        Drawable drawable = view.getDrawable();
        boolean hasImage = (drawable != null);

        if (hasImage && (drawable instanceof BitmapDrawable)) {
            hasImage = ((BitmapDrawable)drawable).getBitmap() != null;
        }

        return hasImage;
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

}
