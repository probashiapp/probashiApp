package com.example.probashiapp;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;

import com.github.chrisbanes.photoview.PhotoView;

public class ShowPic_Activity extends AppCompatActivity {
    TextView textView;
    private PhotoView pic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_pic_);

        Intent intent = getIntent();
        Application application = intent.getParcelableExtra("Application");
        String type = intent.getStringExtra("Type");

        pic = findViewById(R.id.pic_pv);
        textView = findViewById(R.id.textview);
        if (type.equals("Pic")) {
            textView.setText("Profile Picture");
            GlideApp.with(this).load(application.agent.getProfilePhoto_url()).placeholder(R.drawable.placeholder).error(R.drawable.error).into(pic);
        } else if (type.equals("Nid")) {
            textView.setText("NID");
            GlideApp.with(this).load(application.agent.getNidPhoto_url()).placeholder(R.drawable.placeholder).error(R.drawable.error).into(pic);
        } else if (type.equals("Passport")) {
            textView.setText("Passport Picture");
            GlideApp.with(this).load(application.agent.getPassportPhoto_url()).placeholder(R.drawable.placeholder).error(R.drawable.error).into(pic);
        }


    }
}
