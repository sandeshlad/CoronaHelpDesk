package com.example.coronahelpdesk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomeActivity extends AppCompatActivity {

    EditText name,phone,address,list;
    Button send;

    ProgressBar progressBar;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        name = findViewById(R.id.c_name);
        phone = findViewById(R.id.c_phone);
        address = findViewById(R.id.c_address);
        list = findViewById(R.id.c_list);

        send = findViewById(R.id.send);

        progressBar=findViewById(R.id.c_progressbar);

        databaseReference = FirebaseDatabase.getInstance().getReference("user");

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String NAME=name.getText().toString().trim();
                String PHONE=phone.getText().toString().trim();
                String ADDRESS=address.getText().toString().trim();
                String LIST=list.getText().toString().trim();

                String ID=databaseReference.push().getKey();
                ModelUser modelUser=new ModelUser(ID,NAME,PHONE,ADDRESS,LIST);

                progressBar.setVisibility(View.VISIBLE);
                send.setVisibility(View.GONE);

                databaseReference.child(ID).setValue(modelUser).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(getApplicationContext(),"Data Inserted Successfully",Toast.LENGTH_LONG).show();
                        progressBar.setVisibility(View.GONE);
                        send.setVisibility(View.VISIBLE);

                        name.setText("");
                        phone.setText("");
                        address.setText("");
                        list.setText("");

                    }
                })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                                Toast.makeText(HomeActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                                send.setVisibility(View.VISIBLE);

                            }
                        });




            }
        });
    }
}
