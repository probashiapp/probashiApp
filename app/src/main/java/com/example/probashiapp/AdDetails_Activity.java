package com.example.probashiapp;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class AdDetails_Activity extends AppCompatActivity implements View.OnClickListener {

    private TextView title_tv,country_tv,vacancy_tv,jobtype_tv,visagrade_tv,basicpay_tv,workhour_tv,description_tv,jobsecurity_tv;
    private Button seejobcontract_bt,apply_bt;
    Ad ad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_details_);

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
        seejobcontract_bt = findViewById(R.id.seejobcontract_bt);
        apply_bt = findViewById(R.id.apply_bt);

        title_tv.setText("Title: " + ad.getTitle());
        country_tv.setText("Country: " + ad.getCountry());
        vacancy_tv.setText("Vacancy: " + ad.getVacancy());
        jobtype_tv.setText("Job Type: " + ad.getJob_type());
        jobsecurity_tv.setText("Job Security: " + ad.getJob_security());
        visagrade_tv.setText("Visa Grade: " + ad.getVisa_grade());
        basicpay_tv.setText("Basic Pay: " + ad.getBasic_pay());
        workhour_tv.setText("Work Hour: " + ad.getWork_hour());
        description_tv.setText("Description: " + ad.getDescription());

        seejobcontract_bt.setOnClickListener(null);
        seejobcontract_bt.setText("Checking Status...");

        apply_bt.setOnClickListener(null);
        apply_bt.setText("Checking Status...");

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Applications").whereEqualTo("ad_id", ad.ad_id).whereEqualTo("applicant_id", FirebaseAuth.getInstance().getUid()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    if (task.getResult().isEmpty()) {
                        seejobcontract_bt.setOnClickListener(AdDetails_Activity.this);
                        seejobcontract_bt.setText("See Job Contract");

                        apply_bt.setOnClickListener(AdDetails_Activity.this);
                        apply_bt.setText("Apply");
                    } else {
                        seejobcontract_bt.setVisibility(View.GONE);
                        apply_bt.setText("Already Applied");
                    }
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == seejobcontract_bt.getId()) {
            Intent intent1 = new Intent(AdDetails_Activity.this, ShowContract_Activity.class);
            intent1.putExtra("Ad", ad);
            startActivity(intent1);
            finish();
        } else if (view.getId() == apply_bt.getId()) {
            Intent intent2 = new Intent(AdDetails_Activity.this, ProfilePhoto_Activity.class);
            intent2.putExtra("Ad", ad);
            startActivity(intent2);
            finish();
        }
    }
}
