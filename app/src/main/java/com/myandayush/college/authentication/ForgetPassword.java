package com.myandayush.college.authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.myandayush.college.MainActivity;
import com.myandayush.college.R;

public class ForgetPassword extends AppCompatActivity {

    private EditText forgetEmail;
    private Button resetbtn, cancel;
    private String email;
    private ProgressBar prgressbarOfReset;
    private FirebaseAuth auth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        setTitle("Reset Password");


        auth = FirebaseAuth.getInstance();

       forgetEmail = findViewById(R.id.forgetEmail);
       resetbtn = findViewById(R.id.btnReset);
       cancel = findViewById(R.id.btnCancel);
        prgressbarOfReset = findViewById(R.id.prgressbarOfReset);

       cancel.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               prgressbarOfReset.setVisibility(View.VISIBLE);
               Thread thread = new Thread(){
                   public void run(){
                       try {
                           sleep(1000);
                       }catch (Exception ex){
                           ex.printStackTrace();
                       }
                       finally {
                           Intent intent = new Intent(ForgetPassword.this, LoginActivity.class);
                           startActivity(intent);
                           finish();
                       }
                   }
               }; thread.start();
           }
       });

       resetbtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               validateData();
           }
       });

    }

    private void validateData() {
        email = forgetEmail.getText().toString();
        if (email.isEmpty()){
            forgetEmail.setError("REQUIRED");
        }
        else {
            forgetPAss();
        }
    }

    private void forgetPAss() {
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    prgressbarOfReset.setVisibility(View.VISIBLE);
                    Toast.makeText(ForgetPassword.this, "Check your Email", Toast.LENGTH_LONG).show();
                    Thread thread = new Thread(){
                        public void run(){
                            try {
                                sleep(3000);
                            }catch (Exception ex){
                                ex.printStackTrace();
                            }
                            finally {
                                Intent intent = new Intent(ForgetPassword.this, LoginActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }
                    }; thread.start();
                }
                else {
                    Toast.makeText(ForgetPassword.this, "Error : " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}