package com.example.probashiapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Probashi_Home_Activity extends AppCompatActivity implements View.OnClickListener {

    private Button searchnewjob_bt,appliedjobs_bt,passnid_bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_probashi__home_);

        searchnewjob_bt = findViewById(R.id.searchnewjobs_bt);
        appliedjobs_bt = findViewById(R.id.appliedjobs_bt);
        passnid_bt = findViewById(R.id.passnid_bt);

        searchnewjob_bt.setOnClickListener(this);
        appliedjobs_bt.setOnClickListener(this);
        passnid_bt.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == searchnewjob_bt.getId()){
            Intent intent = new Intent(Probashi_Home_Activity.this,ShowJobs_Activity.class);
            startActivity(intent);
            finish();
        }
    }
}
