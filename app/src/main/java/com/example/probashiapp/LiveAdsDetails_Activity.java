package com.example.probashiapp;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class LiveAdsDetails_Activity extends AppCompatActivity {

    Ad ad;
    FirebaseFirestore db;
    private TextView title_tv, country_tv, vacancy_tv, jobtype_tv, visagrade_tv, basicpay_tv, workhour_tv, description_tv, jobsecurity_tv;
    private RecyclerView mRecyclerView;
    private AgentsAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_ads_details_);

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

        final ArrayList<Application> applicationsArrayList = new ArrayList<>();
        final ArrayList<Agents> agentsArrayList = new ArrayList<>();

        db = FirebaseFirestore.getInstance();

        db.collection("Applications").whereEqualTo("ad_id", ad.ad_id).orderBy("apply_time", Query.Direction.DESCENDING).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (DocumentSnapshot dc : task.getResult()) {
                        Application application = dc.toObject(Application.class);
                        applicationsArrayList.add(application);
                        agentsArrayList.add(application.agent);
                    }

                    if (agentsArrayList.size() > 0) {
                        mRecyclerView = findViewById(R.id.recyclerView);
                        mRecyclerView.setHasFixedSize(true);
                        mLayoutManager = new LinearLayoutManager(LiveAdsDetails_Activity.this);
                        mAdapter = new AgentsAdapter(agentsArrayList);

                        mRecyclerView.setLayoutManager(mLayoutManager);
                        mRecyclerView.setAdapter(mAdapter);

                        mAdapter.setOnItemClickListener(new AgentsAdapter.OnitemClickListener() {
                            @Override
                            public void onItemClick(int position) {
                                Application temp = applicationsArrayList.get(position);
                                Intent intent = new Intent(LiveAdsDetails_Activity.this, ApplicantDetails_Activity.class);
                                intent.putExtra("Application", temp);
                                startActivity(intent);

                            }
                        });

                    } else {
                        Toast.makeText(LiveAdsDetails_Activity.this, "Oops! Something went wrong. Check your internet connection.", Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(LiveAdsDetails_Activity.this, "Applicants not Found. Check your internet connection.", Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}
