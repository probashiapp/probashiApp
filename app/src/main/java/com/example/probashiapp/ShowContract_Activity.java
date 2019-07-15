package com.example.probashiapp;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.github.chrisbanes.photoview.PhotoView;

public class ShowContract_Activity extends AppCompatActivity {

    PhotoView contract_pv;
    private Button apply_bt;
    Ad ad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_contract_);

        final Intent intent = getIntent();
        ad = intent.getParcelableExtra("Ad");

        contract_pv = findViewById(R.id.contract_pv);
        apply_bt = findViewById(R.id.apply_bt);

        apply_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(ShowContract_Activity.this,ProfilePhoto_Activity.class);
                intent1.putExtra("Ad",ad);
                startActivity(intent1);
                finish();
            }
        });


        GlideApp.with(this)
                .load(ad.getImageurl()).placeholder(R.drawable.placeholder).error(R.drawable.error)
                .into(contract_pv);
    }
}
