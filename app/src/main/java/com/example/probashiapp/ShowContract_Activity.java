package com.example.probashiapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.chrisbanes.photoview.PhotoView;

public class ShowContract_Activity extends AppCompatActivity {

    PhotoView contract_pv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_contract_);

        Intent intent = getIntent();
        Ad ad = intent.getParcelableExtra("Ad");

        contract_pv = findViewById(R.id.contract_pv);


        GlideApp.with(this)
                .load(ad.getImageurl())
                .into(contract_pv);
    }
}
