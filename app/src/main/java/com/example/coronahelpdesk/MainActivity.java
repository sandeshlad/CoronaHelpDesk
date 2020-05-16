package com.example.coronahelpdesk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
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

public class MainActivity extends AppCompatActivity {

    EditText name, email, password;
    Button register;

    private ProgressBar progressBar;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        name = findViewById(R.id.r_name);
        email = findViewById(R.id.r_email);
        password = findViewById(R.id.r_password);
        register = findViewById(R.id.r_register);

        progressBar = findViewById(R.id.progressbarRegister);


        firebaseAuth = FirebaseAuth.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String NAME = name.getText().toString().trim();
                String EMAIL = email.getText().toString().trim();

                String PASS = password.getText().toString().trim();

                if (NAME.isEmpty()) {
                    email.setError("Please enter Name");
                    email.requestFocus();
                    return;
                }


                if (EMAIL.isEmpty()) {
                    email.setError("Please enter email");
                    email.requestFocus();
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(EMAIL).matches()) {
                    email.setError("Email is not valid");
                    email.requestFocus();
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
                register.setVisibility(View.GONE);


                firebaseAuth.createUserWithEmailAndPassword(EMAIL, PASS).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(MainActivity.this, HomeActivity.class));

                            progressBar.setVisibility(View.GONE);
                            register.setVisibility(View.VISIBLE);
                        }
                    }
                })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                                progressBar.setVisibility(View.GONE);
                                register.setVisibility(View.VISIBLE);
                            }
                        });

            }
        });

    }

    public void openLogin(View view) {

        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);

    }
    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
