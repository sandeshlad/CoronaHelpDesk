package com.example.coronahelpdesk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {


    EditText username, password;
    Button login;

    FirebaseAuth firebaseAuth;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.login_username);
        password = findViewById(R.id.login_password);

        login = findViewById(R.id.login);

        firebaseAuth = FirebaseAuth.getInstance();

        progressBar = findViewById(R.id.progressbarLogin);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String USERNAME = username.getText().toString().trim();
                String PASS = password.getText().toString().trim();

                if (USERNAME.isEmpty()) {
                    username.setError("Please enter email");
                    username.requestFocus();
                    return;
                }



                if (PASS.isEmpty()) {
                    password.setError("Please enter password");
                    password.requestFocus();
                    return;
                }

                if (PASS.length() < 6) {
                    password.setError("Minimum digit 6");
                    password.requestFocus();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                login.setVisibility(View.GONE);

                firebaseAuth.signInWithEmailAndPassword(USERNAME, PASS).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Success", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LoginActivity.this, HomeActivity.class));

                            progressBar.setVisibility(View.GONE);
                            login.setVisibility(View.VISIBLE);

                        }
                    }
                })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                                progressBar.setVisibility(View.GONE);
                                login.setVisibility(View.VISIBLE);
                            }
                        });

            }
        });

    }

    public void openRegister(View view) {

        Intent intent=new Intent(LoginActivity.this,MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}

