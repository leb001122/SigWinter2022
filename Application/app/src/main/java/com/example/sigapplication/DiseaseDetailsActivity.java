package com.example.sigapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DiseaseDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_details);

        Intent intent = getIntent();

        TextView titleTextView = (TextView) findViewById(R.id.disease_name);
        titleTextView.setText(intent.getStringExtra("diseaseName"));

        TextView detailsTextView = (TextView) findViewById(R.id.description);
        detailsTextView.setText(intent.getStringExtra("description"));
    }
}
