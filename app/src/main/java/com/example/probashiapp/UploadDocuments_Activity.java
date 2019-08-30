package com.example.probashiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UploadDocuments_Activity extends AppCompatActivity implements View.OnClickListener {

    Button profile_bt, passport_bt, nid_bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_documents_);

        profile_bt = findViewById(R.id.profile_bt);
        passport_bt = findViewById(R.id.passport_bt);
        nid_bt = findViewById(R.id.nid_bt);

        profile_bt.setOnClickListener(this);
        passport_bt.setOnClickListener(this);
        nid_bt.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == profile_bt.getId()) {
            Intent intent = new Intent(UploadDocuments_Activity.this, DocumentProfileUpload_Activity.class);
            startActivity(intent);
        } else if (view.getId() == passport_bt.getId()) {
            Intent intent = new Intent(UploadDocuments_Activity.this, DocumentPassportUpload_Activity.class);
            startActivity(intent);
        } else if (view.getId() == nid_bt.getId()) {
            Intent intent = new Intent(UploadDocuments_Activity.this, DocumentNidUpload_Activity.class);
            startActivity(intent);
        }


    }
}
