package com.example.probashiapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class Signup_Activity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private Button signup_bt;
    private EditText name_et,password_et,retypepassword_et,email_et,phone_et,address_et,city_et,nid_et;
    String name,password,retypepassword,phone,email,address,city,nid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_);

        signup_bt = findViewById(R.id.signup_bt);
        name_et = findViewById(R.id.name_et);
        password_et = findViewById(R.id.password_et);
        retypepassword_et = findViewById(R.id.retypepassword_et);
        email_et = findViewById(R.id.email_et);
        phone_et = findViewById(R.id.phone_et);
        address_et = findViewById(R.id.address_et);
        city_et = findViewById(R.id.city_et);
        nid_et = findViewById(R.id.nid_et);

        signup_bt.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        name = name_et.getText().toString();
        password = password_et.getText().toString();
        retypepassword = retypepassword_et.getText().toString();
        phone = phone_et.getText().toString();
        email = email_et.getText().toString();
        address = address_et.getText().toString();
        city = city_et.getText().toString();
        nid = nid_et.getText().toString();

        if(name.isEmpty()){
            Toast.makeText(this,"Enter your name",Toast.LENGTH_LONG).show();
            return;
        }

        else if(password.isEmpty()){
            Toast.makeText(this,"Enter your password",Toast.LENGTH_LONG).show();
            return;
        }

        else if(retypepassword.isEmpty()){
            Toast.makeText(this,"Enter your password",Toast.LENGTH_LONG).show();
            return;
        }

        else if(phone.isEmpty()){
            Toast.makeText(this,"Enter your phone no.",Toast.LENGTH_LONG).show();
            return;
        }

        else if(email.isEmpty()){
            Toast.makeText(this,"Enter your email",Toast.LENGTH_LONG).show();
            return;
        }

        else if(address.isEmpty()){
            Toast.makeText(this,"Enter your address",Toast.LENGTH_LONG).show();
            return;
        }

        else if(city.isEmpty()){
            Toast.makeText(this,"Enter your city",Toast.LENGTH_LONG).show();
            return;
        }

        else if(nid.isEmpty()){
            Toast.makeText(this,"Enter your NID",Toast.LENGTH_LONG).show();
            return;
        }

        mAuth = FirebaseAuth.getInstance();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            Agents agent = new Agents(name,password,retypepassword,phone,email,address,city,nid,null,null,null);
                            FirebaseFirestore db = FirebaseFirestore.getInstance();

                            db.collection("Agents").document(mAuth.getCurrentUser().getUid()).set(agent).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(Signup_Activity.this, "SignUp Successful .", Toast.LENGTH_LONG).show();

                                    //FirebaseUser user = mAuth.getCurrentUser();
                                    //updateUI(user);

                                    Intent intent = new Intent(Signup_Activity.this, Login_Activity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    mAuth.getCurrentUser().delete();
                                    Toast.makeText(Signup_Activity.this, "SignUp Failed. Could not connect to database. Try again later ." + e.getMessage(), Toast.LENGTH_LONG).show();

                                }
                            });


                        } else {
                            if (task.getException() != null)
                                Toast.makeText(Signup_Activity.this, "SignUp Failed ." + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });

    }
}
