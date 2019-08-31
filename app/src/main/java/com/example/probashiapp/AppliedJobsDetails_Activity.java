package com.example.probashiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.widget.Button;
import android.widget.TextView;

public class AppliedJobsDetails_Activity extends AppCompatActivity {

    Ad ad;

    private TextView title_tv, country_tv, vacancy_tv, jobtype_tv, visagrade_tv, basicpay_tv, workhour_tv, description_tv, jobsecurity_tv, packageprice_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applied_jobs_details_);

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
        packageprice_tv = findViewById(R.id.packageprice_tv);

        String money = new String("৳");
        Integer integer =Integer.parseInt(ad.package_price)/100000;
        for(int i=1;i<integer;i++){
            money = money+" "+money;
        }
        packageprice_tv.setText(Html.fromHtml("Package Price: " + "<font color=\"#ff0000\">" + money + "</font><br><br>"));


        title_tv.setText("Title: " + ad.getTitle());
        country_tv.setText("Country: " + ad.getCountry());
        vacancy_tv.setText("Vacancy: " + ad.getVacancy());
        jobtype_tv.setText("Job Type: " + ad.getJob_type());
        jobsecurity_tv.setText("Job Security: " + ad.getJob_security());
        visagrade_tv.setText("Visa Grade: " + ad.getVisa_grade());
        basicpay_tv.setText("Basic Pay: " + ad.getBasic_pay());
        workhour_tv.setText("Work Hour: " + ad.getWork_hour());
        description_tv.setText("Description: " + ad.getDescription());
    }
}
