package com.example.probashiapp;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class Agency_home_activity extends AppCompatActivity implements View.OnClickListener {

    private Button postnewad_bt, checkresponses_bt, logout_bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agency_home_activity);

        postnewad_bt = findViewById(R.id.postnewad_bt);
        checkresponses_bt = findViewById(R.id.checkresponses_bt);
        logout_bt = findViewById(R.id.logout_bt);

        postnewad_bt.setOnClickListener(this);
        checkresponses_bt.setOnClickListener(this);
        logout_bt.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        if (view.getId() == checkresponses_bt.getId()) {
            Intent intent = new Intent(Agency_home_activity.this, CheckResponses_Activity.class);
            startActivity(intent);
            finish();
        } else if (view.getId() == postnewad_bt.getId()) {
            Intent intent = new Intent(Agency_home_activity.this, Post_ad_Activity.class);
            startActivity(intent);
            finish();
        } else if (view.getId() == logout_bt.getId()) {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(Agency_home_activity.this, Login_Activity.class);
            startActivity(intent);
            finish();
        }
    }
}
