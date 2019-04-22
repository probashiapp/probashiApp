package com.example.probashiapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import javax.annotation.Nullable;

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

        db.collection("Ads").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                for (DocumentSnapshot dc : queryDocumentSnapshots) {


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
                            intent.putExtra("Ad",temp);
                            startActivity(intent);

                        }
                    });

                } else {
                    Toast.makeText(ShowJobs_Activity.this, "Oops! Something went wrong. Check your internet connection.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
