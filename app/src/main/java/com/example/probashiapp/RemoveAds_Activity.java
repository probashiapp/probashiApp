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

public class RemoveAds_Activity extends AppCompatActivity {

    FirebaseFirestore db;
    FirebaseAuth mauth = FirebaseAuth.getInstance();
    private RecyclerView mRecyclerView;
    private AdAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_ads_);
        final ArrayList<Ad> adArrayList = new ArrayList<>();

        db = FirebaseFirestore.getInstance();

        db.collection("Ads").whereEqualTo("agency_id", mauth.getUid()).whereEqualTo("adStatus","Live").orderBy("time", Query.Direction.DESCENDING).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
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
                        mLayoutManager = new LinearLayoutManager(RemoveAds_Activity.this);
                        mAdapter = new AdAdapter(adArrayList);

                        mRecyclerView.setLayoutManager(mLayoutManager);
                        mRecyclerView.setAdapter(mAdapter);

                        mAdapter.setOnItemClickListener(new AdAdapter.OnitemClickListener() {
                            @Override
                            public void onItemClick(int position) {
                                Ad temp = adArrayList.get(position);
                                Intent intent = new Intent(RemoveAds_Activity.this, RemoveAdsDetails_Activity.class);
                                intent.putExtra("Ad", temp);
                                startActivity(intent);

                            }
                        });

                    } else {
                        Toast.makeText(RemoveAds_Activity.this, "No Live Ads found. Check your internet connection & try again.", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(RemoveAds_Activity.this, "No Live Ads found. Check your internet connection & try again.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
