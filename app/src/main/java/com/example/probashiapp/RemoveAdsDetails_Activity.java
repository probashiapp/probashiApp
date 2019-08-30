package com.example.probashiapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.Date;

public class RemoveAdsDetails_Activity extends AppCompatActivity {

    Ad ad;

    private TextView title_tv, country_tv, vacancy_tv, jobtype_tv, visagrade_tv, basicpay_tv, workhour_tv, description_tv, jobsecurity_tv;
    private Button archivead_bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_ads_details_);

        final Intent intent = getIntent();
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

        archivead_bt = findViewById(R.id.archivead_bt);
        archivead_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(RemoveAdsDetails_Activity.this);
                builder.setTitle("Are you sure?").setMessage("Archived ads will be removed after 30 days").setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Date temp = Calendar.getInstance().getTime();
                        long date = temp.getTime();

                        ad.time = date;
                        ad.adStatus = "archived";
                        FirebaseFirestore db = FirebaseFirestore.getInstance();
                        db.collection("Ads").document(ad.ad_id).set(ad).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(RemoveAdsDetails_Activity.this,"Ad successfully archived",Toast.LENGTH_LONG);
                                    Intent intent1 = new Intent(RemoveAdsDetails_Activity.this,Agency_home_activity.class);
                                    startActivity(intent1);
                                    finish();
                                }
                                else Toast.makeText(RemoveAdsDetails_Activity.this,"Oops! Something went wrong. Please try again",Toast.LENGTH_LONG);
                            }
                        });

                    }
                }).setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

    }
}
