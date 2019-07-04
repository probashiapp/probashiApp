package com.example.probashiapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.Date;

public class AgentID_Activity extends AppCompatActivity implements View.OnClickListener {
    private EditText agentid_et;
    private Button skip_bt, apply_bt;

    private FirebaseAuth mauth;
    private FirebaseFirestore db;
    private Ad newad;

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

            String agent_id = agentid_et.getText().toString();
            if (agent_id.isEmpty()) {
                Toast.makeText(AgentID_Activity.this, "Enter Agent_id or Select Skip.", Toast.LENGTH_LONG).show();
                return;
            }

            Date date = Calendar.getInstance().getTime();
            Application application = new Application(newad.ad_id, agent_id, mauth.getUid(), newad.agency_id, date);
            db.collection("Applications").add(application);

            Toast.makeText(AgentID_Activity.this, "Application Succesfully submitted. Wait for the agency to contact you.", Toast.LENGTH_LONG).show();
            Intent intent1 = new Intent(AgentID_Activity.this, Probashi_Home_Activity.class);
            startActivity(intent1);
            finish();
        }
    }
}
