package com.myandayush.college.authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.myandayush.college.MainActivity;
import com.myandayush.college.R;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    private EditText regisyer_name, regisyer_email, regisyer_password, regisyer_confirmpassword;
    private Button regiester;
    private TextView loginRedirectText;
     LinearLayout progressBar,signuplayout;
    private String  name, email, password,confrimpass;
    private FirebaseAuth auth;
    private DatabaseReference reference;
    private DatabaseReference dbref;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        auth = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference();


        regisyer_name = findViewById(R.id.signup_name);
        regisyer_email = findViewById(R.id.signup_email);
        regisyer_password = findViewById(R.id.signup_password);
        regisyer_confirmpassword = findViewById(R.id.signup_confirm);
        regiester = findViewById(R.id.signup_button);
        progressBar = findViewById(R.id.progressbar);
        signuplayout = findViewById(R.id.signuplayout);
        loginRedirectText = findViewById(R.id.loginRedirectText);

        loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(v.getContext(), LoginActivity.class));
                finish();
            }
        });

        regiester.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateData();
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        if (auth.getCurrentUser() != null){
            openMain();
        }
    }

    private void openMain() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    private void validateData() {
        name = regisyer_name.getText().toString();
        email = regisyer_email.getText().toString();
        password = regisyer_password.getText().toString();
        confrimpass = regisyer_confirmpassword.getText().toString();


        if(name.isEmpty()){
            regisyer_name.setError("Required");
            regisyer_name.requestFocus();
        }
        else if(email.isEmpty()){
            regisyer_email.setError("Required");
            regisyer_email.requestFocus();
        }
        else if(password.isEmpty()){
            regisyer_password.setError("Required");
            regisyer_password.requestFocus();
        }
       else if(confrimpass.isEmpty()){
            regisyer_confirmpassword.setError("Required");
            regisyer_confirmpassword.requestFocus();
        }
       else if (!password.equals(confrimpass)){
           regisyer_confirmpassword.setError("Enter valid password");
           regisyer_confirmpassword.requestFocus();
       }
       else {
           createUser();
        }


    }

    private void createUser() {
        progressBar.setVisibility(View.VISIBLE);
        signuplayout.setVisibility(View.GONE);

        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){

                            UploadDat();
                        }
                        else{
                            progressBar.setVisibility(View.GONE);
                            signuplayout.setVisibility(View.GONE);
                            Toast.makeText(RegisterActivity.this, "Error : "+task.getException(), Toast.LENGTH_SHORT).show();

                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressBar.setVisibility(View.GONE);
                        signuplayout.setVisibility(View.GONE);
                        Toast.makeText(RegisterActivity.this, "Error"+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void UploadDat() {
        dbref = reference.child("users");
        String key = dbref.push().getKey();

        HashMap<String, String> user = new HashMap<>();
        user.put("key", key);
        user.put("name", name);
        user.put("email", email);
        user.put("password", password);
        user.put("confirm password", confrimpass);

        dbref.child(key).setValue(user)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            progressBar.setVisibility(View.GONE);
                            signuplayout.setVisibility(View.GONE);
                            Toast.makeText(RegisterActivity.this, "User Register", Toast.LENGTH_SHORT).show();
                            openMain();
                        }
                        else{
                            progressBar.setVisibility(View.GONE);
                            signuplayout.setVisibility(View.GONE);
                            Toast.makeText(RegisterActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressBar.setVisibility(View.GONE);
                        signuplayout.setVisibility(View.GONE);
                        Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}