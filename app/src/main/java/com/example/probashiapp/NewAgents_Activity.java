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
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class NewAgents_Activity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private AgentsAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    FirebaseFirestore db;
    Ad ad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_agents_);


        final ArrayList<Application> applicationsArrayList = new ArrayList<>();
        final ArrayList<Agents> agentsArrayList = new ArrayList<>();


        Intent intent = getIntent();
        ad = intent.getParcelableExtra("Ad");

        db = FirebaseFirestore.getInstance();

        db.collection("Applications").whereEqualTo("ad_id", ad.ad_id).whereEqualTo("contacted",false).orderBy("apply_time", Query.Direction.DESCENDING).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
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
                        mLayoutManager = new LinearLayoutManager(NewAgents_Activity.this);
                        mAdapter = new AgentsAdapter(agentsArrayList);

                        mRecyclerView.setLayoutManager(mLayoutManager);
                        mRecyclerView.setAdapter(mAdapter);

                        mAdapter.setOnItemClickListener(new AgentsAdapter.OnitemClickListener() {
                            @Override
                            public void onItemClick(int position) {
                                Application temp = applicationsArrayList.get(position);
                                Intent intent = new Intent(NewAgents_Activity.this, ApplicantDetails_Activity.class);
                                intent.putExtra("Application", temp);
                                startActivity(intent);

                            }
                        });

                    } else {
                        Toast.makeText(NewAgents_Activity.this, "Oops! Something went wrong. Check your internet connection.", Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(NewAgents_Activity.this, "Applicants not Found. Check your internet connection.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
