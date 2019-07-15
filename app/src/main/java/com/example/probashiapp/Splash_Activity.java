package com.example.probashiapp;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

public class Splash_Activity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Button login_bt,signup_bt;
    private EditText email_et,password_et;
    private Spinner role;
    String Role;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onStart() {
        super.onStart();

        // Check if user is signed in (non-null) and update UI accordingly.
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(false)
                .build();
        db.setFirestoreSettings(settings);
        //updateUI(currentUser);
        if(currentUser!=null){
            db.collection("Agents").document(FirebaseAuth.getInstance().getCurrentUser().getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if(task.isSuccessful()){
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            Intent intent = new Intent(Splash_Activity.this,Probashi_Home_Activity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Intent intent = new Intent(Splash_Activity.this,Agency_home_activity.class);
                            startActivity(intent);
                            finish();
                        }
                    } else {
                        Toast.makeText(Splash_Activity.this,task.getException().toString(),Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
        else{
            Intent intent = new Intent(Splash_Activity.this,Login_Activity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_);
    }
}
