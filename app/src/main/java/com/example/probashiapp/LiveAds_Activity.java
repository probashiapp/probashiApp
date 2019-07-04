package com.example.probashiapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import javax.annotation.Nullable;

public class LiveAds_Activity extends AppCompatActivity {

    FirebaseFirestore db;
    FirebaseAuth mauth = FirebaseAuth.getInstance();
    private RecyclerView mRecyclerView;
    private AdAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_ads_);


        final ArrayList<Ad> adArrayList = new ArrayList<>();

        db = FirebaseFirestore.getInstance();

        db.collection("Ads").whereEqualTo("agency_id", mauth.getUid()).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                for (DocumentSnapshot dc : queryDocumentSnapshots) {


                    Ad ad = dc.toObject(Ad.class);


                    adArrayList.add(ad);


                }
                if (adArrayList.size() > 0) {
                    mRecyclerView = findViewById(R.id.recyclerView);
                    mRecyclerView.setHasFixedSize(true);
                    mLayoutManager = new LinearLayoutManager(LiveAds_Activity.this);
                    mAdapter = new AdAdapter(adArrayList);

                    mRecyclerView.setLayoutManager(mLayoutManager);
                    mRecyclerView.setAdapter(mAdapter);

                    mAdapter.setOnItemClickListener(new AdAdapter.OnitemClickListener() {
                        @Override
                        public void onItemClick(int position) {
                            Ad temp = adArrayList.get(position);
                            Intent intent = new Intent(LiveAds_Activity.this, AdDetails_Activity.class);
                            intent.putExtra("Ad", temp);
                            startActivity(intent);

                        }
                    });

                } else {
                    Toast.makeText(LiveAds_Activity.this, "No Live Ads found. Check your internet connection & try again.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
