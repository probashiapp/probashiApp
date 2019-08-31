package com.example.probashiapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class AppliedJobs_Activity extends AppCompatActivity {

    FirebaseFirestore db;
    FirebaseAuth mauth = FirebaseAuth.getInstance();
    private RecyclerView mRecyclerView;
    private AdAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applied_jobs_);

        final ArrayList<Ad> adArrayList = new ArrayList<>();

        db = FirebaseFirestore.getInstance();

        db.collection("Applications").whereEqualTo("applicant_id", mauth.getUid()).orderBy("apply_time", Query.Direction.DESCENDING).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (DocumentSnapshot dc : task.getResult()) {


                        Application application = dc.toObject(Application.class);


                        adArrayList.add(application.ad);


                    }
                    if (adArrayList.size() > 0) {
                        mRecyclerView = findViewById(R.id.recyclerView);
                        mRecyclerView.setHasFixedSize(true);
                        mLayoutManager = new LinearLayoutManager(AppliedJobs_Activity.this);
                        mAdapter = new AdAdapter(adArrayList);

                        mRecyclerView.setLayoutManager(mLayoutManager);
                        mRecyclerView.setAdapter(mAdapter);

                        mAdapter.setOnItemClickListener(new AdAdapter.OnitemClickListener() {
                            @Override
                            public void onItemClick(int position) {
                                Ad temp = adArrayList.get(position);
                                Intent intent = new Intent(AppliedJobs_Activity.this, AppliedJobsDetails_Activity.class);
                                intent.putExtra("Ad", temp);
                                startActivity(intent);

                            }
                        });

                    } else {
                        Toast.makeText(AppliedJobs_Activity.this, "No Live Ads found. Check your internet connection & try again.", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(AppliedJobs_Activity.this, "No Live Ads found. Check your internet connection & try again.", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
