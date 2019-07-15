package com.example.probashiapp;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class CheckResponses_Activity extends AppCompatActivity implements View.OnClickListener {

    private Button archived_bt, live_bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_responses_);

        archived_bt = findViewById(R.id.archived_bt);
        live_bt = findViewById(R.id.live_bt);

        archived_bt.setOnClickListener(this);
        live_bt.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == archived_bt.getId()) {
            Intent intent = new Intent(CheckResponses_Activity.this, ArchivedAds_Activity.class);
            startActivity(intent);
            finish();
        } else if (view.getId() == live_bt.getId()) {
            Intent intent = new Intent(CheckResponses_Activity.this, LiveAds_Activity.class);
            startActivity(intent);
            finish();
        }
    }
}
