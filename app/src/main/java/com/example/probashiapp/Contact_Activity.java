package com.example.probashiapp;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

public class Contact_Activity extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    TextView name_tv,email_tv,phone_tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_);

        Intent intent = getIntent();
        final Application application = intent.getParcelableExtra("Application");

        name_tv = findViewById(R.id.name_tv);
        email_tv = findViewById(R.id.email_tv);
        phone_tv = findViewById(R.id.phone_tv);

        name_tv.setText("Name: "+application.getAgent().getName());
        email_tv.setText("Email: "+application.getAgent().getEmail());
        phone_tv.setText("Phone: "+application.getAgent().getPhone());

        if(!application.contacted){
            application.contacted = true;
            db.collection("Applications").document(application.getApplication_id()).set(application).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    db.collection("Ads").document(application.getAd_id()).update("applications", FieldValue.arrayUnion(application.getApplicant_id()));
                }
            });
        }

    }

}
