package com.example.sigapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity implements DiseaseTitlesFragment.OnTitleSelectedListener{

    final String[][] contents = new String[3][2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_result);


        contents[0][0] = "감기";
        contents[0][1] = "감기가 뭐냐면 말이야...";
        contents[1][0] = "감기2";
        contents[1][1] = "감기2가 뭐냐면 말이야...";
        contents[2][0] = "감기3";
        contents[2][1] = "감기3가 뭐냐면 말이야...";

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, new ArrayList());

        adapter.add(contents[0][0]);
        adapter.add(contents[1][0]);
        adapter.add(contents[2][0]);

        DiseaseTitlesFragment titlesFragment = (DiseaseTitlesFragment) getSupportFragmentManager().findFragmentById(R.id.titles_fragment);
        titlesFragment.setListAdapter(adapter);


    }

    public void onTitleSelected(int position) {
        Intent intent = new Intent();
        intent.setClass(this, DiseaseDetailsActivity.class);

        intent.putExtra("title", contents[position][0]);
        intent.putExtra("details", contents[position][1]);
        startActivity(intent);
    }
}