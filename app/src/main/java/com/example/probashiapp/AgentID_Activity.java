package com.example.probashiapp;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.Date;

public class AgentID_Activity extends AppCompatActivity implements View.OnClickListener {
    private EditText agentid_et;
    private Button skip_bt, apply_bt;

    private FirebaseAuth mauth;
    private FirebaseFirestore db;
    private Ad newad;
    private String agent_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_id_);

        Intent intent = getIntent();
        newad = intent.getParcelableExtra("Ad");

        agentid_et = findViewById(R.id.agentid_et);
        skip_bt = findViewById(R.id.skip_bt);
        apply_bt = findViewById(R.id.apply_bt);

        skip_bt.setOnClickListener(this);
        apply_bt.setOnClickListener(this);

        mauth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == apply_bt.getId()) {

            agent_id = agentid_et.getText().toString();
            if (agent_id.isEmpty()) {
                Toast.makeText(AgentID_Activity.this, "Enter Agent_id or Select Skip.", Toast.LENGTH_LONG).show();
                return;
            }

            db.collection("Agents").document(mauth.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        Agents agent = task.getResult().toObject(Agents.class);
                        Date date = Calendar.getInstance().getTime();
                        String id = db.collection("Applications").document().getId();
                        Application application = new Application(newad.ad_id, agent_id, mauth.getUid(), newad.agency_id,id, agent, newad, date,false);
                        db.collection("Applications").document(id).set(application).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(AgentID_Activity.this, "Application Succesfully submitted. Wait for the agency to contact you.", Toast.LENGTH_LONG).show();
                                    Intent intent1 = new Intent(AgentID_Activity.this, Probashi_Home_Activity.class);
                                    startActivity(intent1);
                                    finish();
                                } else {
                                    Toast.makeText(AgentID_Activity.this, "Failed to Submit Application. Please try Again.", Toast.LENGTH_LONG).show();
                                }
                            }
                        });

                    } else {
                        Toast.makeText(AgentID_Activity.this, "Failed to Submit Application. Please try Again.", Toast.LENGTH_LONG).show();
                    }

                }
            });


        }
        else if(view.getId() == skip_bt.getId()){
            db.collection("Agents").document(mauth.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        Agents agent = task.getResult().toObject(Agents.class);
                        Date date = Calendar.getInstance().getTime();
                        String id = db.collection("Applications").document().getId();
                        Application application = new Application(newad.ad_id, null, mauth.getUid(), newad.agency_id,id, agent, newad, date,false);
                        db.collection("Applications").document(id).set(application).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(AgentID_Activity.this, "Application Succesfully submitted. Wait for the agency to contact you.", Toast.LENGTH_LONG).show();
                                    Intent intent1 = new Intent(AgentID_Activity.this, Probashi_Home_Activity.class);
                                    startActivity(intent1);
                                    finish();
                                } else {
                                    Toast.makeText(AgentID_Activity.this, "Failed to Submit Application. Please try Again.", Toast.LENGTH_LONG).show();
                                }
                            }
                        });

                    } else {
                        Toast.makeText(AgentID_Activity.this, "Failed to Submit Application. Please try Again.", Toast.LENGTH_LONG).show();
                    }

                }
            });
        }
    }
}
