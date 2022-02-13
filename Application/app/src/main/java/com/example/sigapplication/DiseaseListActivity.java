package com.example.sigapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.annotation.Nullable;
import java.util.ArrayList;

public class DiseaseListActivity extends Activity implements View.OnClickListener {
    private ArrayList<Disease> diseases;
    private ListView listView;
    private DiseaseAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        listView = (ListView) findViewById(R.id.disease_listview);

        Intent intent = getIntent();
        diseases = intent.getParcelableArrayListExtra("diseaseList");

        adapter = new DiseaseAdapter(this, R.layout.result_item, diseases);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), DiseaseDetailsActivity.class);

                intent.putExtra("diseaseName", diseases.get(position).getDiseaseName());
                intent.putExtra("description", diseases.get(position).getDescription());
                startActivity(intent);
            }
        });
    }

    public void onClick(View v) {
    }

}
