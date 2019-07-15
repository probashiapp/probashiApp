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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ShowJobs_Activity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private AdAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private TextView pt_tv;
    FirebaseFirestore db;
    FirebaseAuth mauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_jobs_);

        final ArrayList<Ad> adArrayList = new ArrayList<>();

        db = FirebaseFirestore.getInstance();

        db.collection("Ads").orderBy("time", Query.Direction.DESCENDING).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (DocumentSnapshot dc : task.getResult()) {
                        Ad ad = dc.toObject(Ad.class);


                        adArrayList.add(ad);
                    }

                    if (adArrayList.size() > 0) {
                        mRecyclerView = findViewById(R.id.recyclerView);
                        mRecyclerView.setHasFixedSize(true);
                        mLayoutManager = new LinearLayoutManager(ShowJobs_Activity.this);
                        mAdapter = new AdAdapter(adArrayList);


                        mRecyclerView.setLayoutManager(mLayoutManager);
                        mRecyclerView.setAdapter(mAdapter);

                        mAdapter.setOnItemClickListener(new AdAdapter.OnitemClickListener() {
                            @Override
                            public void onItemClick(int position) {
                                Ad temp = adArrayList.get(position);
                                Intent intent = new Intent(ShowJobs_Activity.this, AdDetails_Activity.class);
                                intent.putExtra("Ad", temp);
                                startActivity(intent);

                            }
                        });

                    } else {
                        Toast.makeText(ShowJobs_Activity.this, "Oops! Something went wrong. Check your internet connection.", Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(ShowJobs_Activity.this, "No Jobs Found. Check your internet connection.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}