package com.example.probashiapp;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ApplicantDetails_Activity extends AppCompatActivity implements View.OnClickListener {

    private Button showpic_bt, shownid_bt, showpassport_bt, contact_bt;
    private TextView name, address, city, nid;
    private Application application;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applicant_details_);

        Intent intent = getIntent();
        application = intent.getParcelableExtra("Application");

        shownid_bt = findViewById(R.id.shownid_bt);
        showpassport_bt = findViewById(R.id.showpassport_bt);
        contact_bt = findViewById(R.id.contact_bt);
        showpic_bt = findViewById(R.id.showpic_bt);

        name = findViewById(R.id.name_tv);
        address = findViewById(R.id.address_tv);
        city = findViewById(R.id.city_tv);
        nid = findViewById(R.id.nid_tv);

        Agents agent = application.getAgent();
        name.setText("Name: " + agent.getName());
        address.setText("Address: " + agent.getAddress());
        city.setText("City: " + agent.getCity());
        nid.setText("NID: " + agent.getNid());

        shownid_bt.setOnClickListener(this);
        showpassport_bt.setOnClickListener(this);
        contact_bt.setOnClickListener(this);
        showpic_bt.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == showpic_bt.getId()) {
            Intent intent1 = new Intent(ApplicantDetails_Activity.this, ShowPic_Activity.class);
            intent1.putExtra("Application", application);
            intent1.putExtra("Type", "Pic");
            startActivity(intent1);
        } else if (view.getId() == shownid_bt.getId()) {
            Intent intent1 = new Intent(ApplicantDetails_Activity.this, ShowPic_Activity.class);
            intent1.putExtra("Application", application);
            intent1.putExtra("Type", "Nid");
            startActivity(intent1);
        } else if (view.getId() == showpassport_bt.getId()) {
            Intent intent1 = new Intent(ApplicantDetails_Activity.this, ShowPic_Activity.class);
            intent1.putExtra("Application", application);
            intent1.putExtra("Type", "Passport");
            startActivity(intent1);
        } else if (view.getId() == contact_bt.getId()) {
            Intent intent1 = new Intent(ApplicantDetails_Activity.this, Contact_Activity.class);
            intent1.putExtra("Application", application);
            startActivity(intent1);
        }

    }
}
