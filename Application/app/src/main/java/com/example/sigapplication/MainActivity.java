package com.example.sigapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

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

        final String[] mid = {"히어로즈", "24시", "로스트", "하이스쿨뮤지컬", "ㅁㅇㄴㄹㄴㅇ", "ㅁㄴㅇㄹㅁㄴㅇ"," ㅁㄴㅇㄹㅁㄴㅇㄹ", "ㅁㅁㅁㅁㅁㅁ"};
        ListView list = (ListView) findViewById(R.id.listview);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mid);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long id) {
                Toast.makeText(getApplicationContext(), mid[index], Toast.LENGTH_SHORT).show();
            }
        });
    }
}