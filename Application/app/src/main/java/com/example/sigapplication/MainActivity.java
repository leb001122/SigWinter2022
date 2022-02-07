package com.example.sigapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
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

    private ListView listView;
    private ArrayList<MyQuestion> questions;
    private MyQuestionAdapter adapter;
    Button btnAdd;
    DBHelper helper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = (Button) findViewById(R.id.btn_question_add);
        listView = (ListView) findViewById(R.id.listview);
        questions = new ArrayList<MyQuestion>();
        helper = new DBHelper(MainActivity.this, "sigdb.db", null, 1);
        db = helper.getWritableDatabase();
        helper.onCreate(db);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SymptomInput.class));
            }
        });


        //어댑터 초기화부분. questions와 어댑터를 연결해준다.
        adapter = new MyQuestionAdapter(getApplicationContext(), questions);
        listView.setAdapter(adapter);

        /*

        final ArrayList<String> midList = new ArrayList<>();
        ListView list = (ListView) findViewById(R.id.listview);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, midList);
        list.setAdapter(adapter);

*//*        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long id) {
                Toast.makeText(getApplicationContext(), midList[index], Toast.LENGTH_SHORT).show();
            }
        });
        *//*

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
        });*/
    }
}