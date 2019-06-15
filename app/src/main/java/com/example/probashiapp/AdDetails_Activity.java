package com.example.probashiapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AdDetails_Activity extends AppCompatActivity {

    private TextView title_tv,country_tv,vacancy_tv,jobtype_tv,visagrade_tv,basicpay_tv,workhour_tv,description_tv,jobsecurity_tv;
    private Button seejobcontract_bt;
    Ad ad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_details_);

        Intent intent = getIntent();
        ad = intent.getParcelableExtra("Ad");

        title_tv = findViewById(R.id.title_tv);
        country_tv = findViewById(R.id.country_tv);
        vacancy_tv = findViewById(R.id.vacancy_tv);
        jobtype_tv = findViewById(R.id.job_type_tv);
        jobsecurity_tv = findViewById(R.id.job_security_tv);
        visagrade_tv = findViewById(R.id.visa_grade_tv);
        basicpay_tv = findViewById(R.id.basic_pay_tv);
        workhour_tv = findViewById(R.id.work_hour_tv);
        description_tv = findViewById(R.id.description_tv);
        seejobcontract_bt = findViewById(R.id.seejobcontract_bt);

        title_tv.setText(ad.getTitle());
        country_tv.setText(ad.getCountry());
        vacancy_tv.setText(ad.getVacancy());
        jobtype_tv.setText(ad.getJob_type());
        jobsecurity_tv.setText(ad.getJob_security());
        visagrade_tv.setText(ad.getVisa_grade());
        basicpay_tv.setText(ad.getBasic_pay());
        workhour_tv.setText(ad.getWork_hour());
        description_tv.setText(ad.getDescription());

        seejobcontract_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(AdDetails_Activity.this,ShowContract_Activity.class);
                intent1.putExtra("Ad",ad);
                startActivity(intent1);
                finish();
            }
        });
    }
}
