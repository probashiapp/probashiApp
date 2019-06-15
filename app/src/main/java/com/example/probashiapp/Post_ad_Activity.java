package com.example.probashiapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Post_ad_Activity extends AppCompatActivity {
    private EditText title_et,country_et,vacancy_et,job_type_et,visa_grade_et,basic_pay_et,work_hour_et,description_et,job_security_et,package_price_et;
    private Button upload_bt;
    int pp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_ad_);

        title_et = findViewById(R.id.title_et);
        country_et = findViewById(R.id.country_et);
        vacancy_et = findViewById(R.id.vacancy_et);
        job_type_et = findViewById(R.id.job_type_et);
        job_security_et = findViewById(R.id.job_security_et);
        visa_grade_et = findViewById(R.id.visa_grade_et);
        basic_pay_et = findViewById(R.id.basic_pay_et);
        work_hour_et = findViewById(R.id.work_hour_et);
        description_et = findViewById(R.id.description_et);
        upload_bt = findViewById(R.id.upload_bt);
        package_price_et = findViewById(R.id.package_price_et);

        upload_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title,country,vacancy,job_type,job_security,visa_grade,basic_pay,work_hour,description,package_price;
                title = title_et.getText().toString();
                country = country_et.getText().toString();
                vacancy = vacancy_et.getText().toString();
                job_type = job_type_et.getText().toString();
                job_security = job_security_et.getText().toString();
                visa_grade = visa_grade_et.getText().toString();
                basic_pay = basic_pay_et.getText().toString();
                work_hour = work_hour_et.getText().toString();
                description = description_et.getText().toString();
                package_price = package_price_et.getText().toString();

                if(title.isEmpty()){
                    Toast.makeText(Post_ad_Activity.this,"Enter ad title",Toast.LENGTH_LONG).show();
                    return;
                }
                else if(country.isEmpty()){
                    Toast.makeText(Post_ad_Activity.this,"Enter country",Toast.LENGTH_LONG).show();
                    return;
                }
                else if(vacancy.isEmpty()){
                    Toast.makeText(Post_ad_Activity.this,"Enter vacancy",Toast.LENGTH_LONG).show();
                    return;
                }
                else if(job_type.isEmpty()){
                    Toast.makeText(Post_ad_Activity.this,"Enter job type",Toast.LENGTH_LONG).show();
                    return;
                }
                else if(job_security.isEmpty()){
                    Toast.makeText(Post_ad_Activity.this,"Enter job security",Toast.LENGTH_LONG).show();
                    return;
                }
                else if(visa_grade.isEmpty()){
                    Toast.makeText(Post_ad_Activity.this,"Enter visa grade",Toast.LENGTH_LONG).show();
                    return;
                }
                else if(basic_pay.isEmpty()){
                    Toast.makeText(Post_ad_Activity.this,"Enter basic pay in BDT",Toast.LENGTH_LONG).show();
                    return;
                }
                else if(work_hour.isEmpty()){
                    Toast.makeText(Post_ad_Activity.this,"Enter work hour",Toast.LENGTH_LONG).show();
                    return;
                }
                else if(description.isEmpty()){
                    Toast.makeText(Post_ad_Activity.this,"Enter ad description",Toast.LENGTH_LONG).show();
                    return;
                }
                else if(package_price.isEmpty()){
                    Toast.makeText(Post_ad_Activity.this,"Enter package price in BDT",Toast.LENGTH_LONG).show();
                    return;
                }


                 pp = Integer.parseInt(package_price);
                if(pp<=100000){
                    pp = (int) (pp*1.10);
                }
                else if(pp<=150000){
                    pp = (int) (pp*1.12);
                }
                else if(pp<=200000){
                    pp = (int) (pp*1.125);
                }
                else if(pp<=250000){
                    pp = (int) (pp*1.11);
                }
                else if(pp<=300000){
                    pp = (int) (pp*1.10);
                }
                else if(pp>300000){
                    pp = (int) (pp*1.08);
                }

                package_price= Integer.toString(pp);

                Ad newad = new Ad(title,country,vacancy,job_type,job_security,visa_grade,basic_pay,work_hour,description,package_price,"null","null","");

                Intent intent = new Intent(Post_ad_Activity.this,ContractUpload_Activity.class);
                intent.putExtra("Ad",newad);
                startActivity(intent);
                finish();
            }
        });
    }
}
