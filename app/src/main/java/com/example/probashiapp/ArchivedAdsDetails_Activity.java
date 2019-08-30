package com.example.probashiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ArchivedAdsDetails_Activity extends AppCompatActivity {
    Ad ad;

    private TextView title_tv, country_tv, vacancy_tv, jobtype_tv, visagrade_tv, basicpay_tv, workhour_tv, description_tv, jobsecurity_tv;
    private Button contacted_bt,applications_bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archived_ads_details_);

        Intent intent = getIntent();
        ad = intent.getParcelableExtra("Ad");


        title_tv = findViewById(R.id.name_tv);
        country_tv = findViewById(R.id.address_tv);
        vacancy_tv = findViewById(R.id.vacancy_tv);
        jobtype_tv = findViewById(R.id.job_type_tv);
        jobsecurity_tv = findViewById(R.id.job_security_tv);
        visagrade_tv = findViewById(R.id.visa_grade_tv);
        basicpay_tv = findViewById(R.id.basic_pay_tv);
        workhour_tv = findViewById(R.id.work_hour_tv);
        description_tv = findViewById(R.id.description_tv);


        title_tv.setText("Title: " + ad.getTitle());
        country_tv.setText("Country: " + ad.getCountry());
        vacancy_tv.setText("Vacancy: " + ad.getVacancy());
        jobtype_tv.setText("Job Type: " + ad.getJob_type());
        jobsecurity_tv.setText("Job Security: " + ad.getJob_security());
        visagrade_tv.setText("Visa Grade: " + ad.getVisa_grade());
        basicpay_tv.setText("Basic Pay: " + ad.getBasic_pay());
        workhour_tv.setText("Work Hour: " + ad.getWork_hour());
        description_tv.setText("Description: " + ad.getDescription());

        contacted_bt = findViewById(R.id.contacted_bt);

        contacted_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ArchivedAdsDetails_Activity.this, ContactedAgents_Activity.class);
                intent.putExtra("Ad", ad);
                startActivity(intent);
            }
        });

    }
}
