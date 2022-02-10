package com.example.sigapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DiseaseDetailsActivity extends AppCompatActivity {

    private TextView titleTextView, descriptionTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_details);

        Intent intent = getIntent();

        titleTextView = (TextView) findViewById(R.id.disease_name);
        descriptionTextView = (TextView) findViewById(R.id.description);

        titleTextView.setText(intent.getStringExtra("diseaseName"));
        descriptionTextView.setText(intent.getStringExtra("description"));
    }
}
