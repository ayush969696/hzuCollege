package com.myandayush.college.authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.myandayush.college.MainActivity;
import com.myandayush.college.R;

public class LoginActivity extends AppCompatActivity {

    private EditText userEmail, userpassword;
    private TextView signupText, openforgetPass;
    private Button loginButton;
    private ProgressBar progressbarLogin;
    private String email, password;
    private CardView linearlayourGone;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private FirebaseAuth auth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPreferences = this.getSharedPreferences("login", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        auth = FirebaseAuth.getInstance();

        if(sharedPreferences.getString("islogin", "false").equals("true")){
            openDash();
        }

        userEmail = findViewById(R.id.userEmail);
        userpassword = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);
        signupText = findViewById(R.id.signupText);
        openforgetPass = findViewById(R.id.forgetPassword);
        progressbarLogin = findViewById(R.id.progressbarLogin);
        linearlayourGone = findViewById(R.id.linearlayourGone);

        openforgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressbarLogin.setVisibility(View.VISIBLE);
                linearlayourGone.setVisibility(View.GONE);
                signupText.setVisibility(View.GONE);
                openforgetPass.setVisibility(View.GONE);
                Thread thread = new Thread(){
                    public void run(){
                        try {
                            sleep(2000);
                        }catch (Exception ex){
                            ex.printStackTrace();
                        }
                        finally {
                            Intent intent = new Intent(v.getContext(), ForgetPassword.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                }; thread.start();
            }
        });

        signupText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressbarLogin.setVisibility(View.VISIBLE);
                linearlayourGone.setVisibility(View.GONE);
                signupText.setVisibility(View.GONE);
                openforgetPass.setVisibility(View.GONE);

                Thread thread = new Thread(){
                    public void run(){
                        try {
                            sleep(2000);
                        }catch (Exception ex){
                            ex.printStackTrace();
                        }
                        finally {
                            Intent intent = new Intent(v.getContext(), RegisterActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                }; thread.start();
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateData();
            }
        });


    }
    private void validateData() {
        email = userEmail.getText().toString();
        password = userpassword.getText().toString();

        if(email.isEmpty()){
            userEmail.setError("Enter E-mail");
            userEmail.requestFocus(); // help this we can point tha cursor to the empty place
        } else if (password.isEmpty()) {
            userpassword.setError("Set Password");
            userpassword.requestFocus();
        }
        else {
           loginUser();
        }
    }

    private void loginUser() {
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            openDash();
                        }
                        else {
                            Toast.makeText(LoginActivity.this, "Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(LoginActivity.this, "Error : " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void openDash() {
        // here we have to code something like that if user is login then if he open again the app he does not need to login again
        progressbarLogin.setVisibility(View.VISIBLE);
        linearlayourGone.setVisibility(View.GONE);
        signupText.setVisibility(View.GONE);
        openforgetPass.setVisibility(View.GONE);
        Thread thread = new Thread(){
            public void run(){
                try {
                    sleep(4000);
                }catch (Exception ex){
                    ex.printStackTrace();
                }
                finally {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }; thread.start();
    }
}