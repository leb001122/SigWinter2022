package com.example.sigapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnAdd = (Button) findViewById(R.id.btn_question_add);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SymptomInput.class));
            }
        });

        final ArrayList<String> midList = new ArrayList<>();
        ListView list = (ListView) findViewById(R.id.listview);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, midList);
        list.setAdapter(adapter);

/*        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long id) {
                Toast.makeText(getApplicationContext(), midList[index], Toast.LENGTH_SHORT).show();
            }
        });
        */

        // item 제거
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int index, long id) {
                midList.remove(index);
                adapter.notifyDataSetChanged();
                return false;
            }
        });

        Button temp = findViewById(R.id.buttonadd);
        temp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                midList.add((midList.size()+1) + "번째");
                adapter.notifyDataSetChanged();
            }
        });
    }
}