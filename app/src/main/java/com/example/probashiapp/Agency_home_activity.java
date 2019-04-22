package com.example.probashiapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Agency_home_activity extends AppCompatActivity implements View.OnClickListener{

    private Button postnewad_bt,checkresponses_bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agency_home_activity);

        postnewad_bt = findViewById(R.id.searchnewjobs_bt);
        checkresponses_bt = findViewById(R.id.appliedjobs_bt);

        postnewad_bt.setOnClickListener(this);
        checkresponses_bt.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        if(view.getId() == checkresponses_bt.getId()){
            Intent intent = new Intent(Agency_home_activity.this,CheckResponses_Activity.class);
            startActivity(intent);
            finish();
        }
        else if(view.getId() == postnewad_bt.getId()){
            Intent intent = new Intent(Agency_home_activity.this,Post_ad_Activity.class);
            startActivity(intent);
            finish();
        }
    }
}
