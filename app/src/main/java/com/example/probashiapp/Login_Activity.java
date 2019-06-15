package com.example.probashiapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Login_Activity extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth mAuth;
    private Button login_bt,signup_bt;
    private EditText email_et,password_et;
    private Spinner role;
    String Role;
    FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login_bt = findViewById(R.id.login_bt);
        signup_bt = findViewById(R.id.signup_bt);
        email_et = findViewById(R.id.email_et);
        password_et = findViewById(R.id.password_et);
        role = findViewById(R.id.role);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.roles_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        role.setAdapter(adapter);

        login_bt.setOnClickListener(this);
        signup_bt.setOnClickListener(this);
        role.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Role = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == login_bt.getId()){
            String email,password;
            email = email_et.getText().toString();
            password = password_et.getText().toString();


            if(email.isEmpty()){
                Toast.makeText(this,"Enter your email",Toast.LENGTH_LONG).show();
                return;
            }
            else if(password.isEmpty()){
                Toast.makeText(this,"Enter your password",Toast.LENGTH_LONG).show();
                return;
            }
            else if(Role.isEmpty()){
                Toast.makeText(this,"Select a Role",Toast.LENGTH_LONG).show();
                return;
            }

            mAuth = FirebaseAuth.getInstance();
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information




                                if(Role.equals("Probashi")) {
                                    db.collection("Agents").document(FirebaseAuth.getInstance().getCurrentUser().getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                            if(task.isSuccessful()){
                                                DocumentSnapshot document = task.getResult();
                                                if (document.exists()) {
                                                    Intent intent = new Intent(Login_Activity.this,Probashi_Home_Activity.class);
                                                    startActivity(intent);
                                                    finish();
                                                } else {
                                                    Toast.makeText(Login_Activity.this,"User does not exist!",Toast.LENGTH_LONG).show();
                                                }
                                            } else {
                                                Toast.makeText(Login_Activity.this,task.getException().toString(),Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });
                                }

                                else if(Role.equals("Agency")) {
                                    db.collection("Agency").document(FirebaseAuth.getInstance().getCurrentUser().getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                            if(task.isSuccessful()){
                                                DocumentSnapshot document = task.getResult();
                                                if (document.exists()) {
                                                    Intent intent = new Intent(Login_Activity.this, Agency_home_activity.class);
                                                    startActivity(intent);
                                                    finish();
                                                } else {
                                                    Toast.makeText(Login_Activity.this,"User does not exist!",Toast.LENGTH_LONG).show();
                                                }
                                            } else {
                                                Toast.makeText(Login_Activity.this,task.getException().toString(),Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });
                                }


                            } else {
                                // If sign in fails, display a message to the user.
                                if (task.getException() != null)
                                    Toast.makeText(Login_Activity.this, "Authentication Failed ." + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                            }


                            // ...
                        }
                    });

        }
        else if(view.getId() == signup_bt.getId()){
            Intent intent = new Intent(Login_Activity.this,Signup_Activity.class);
            startActivity(intent);
            finish();
        }
    }
}
