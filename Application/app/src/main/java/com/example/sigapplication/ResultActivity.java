package com.example.sigapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {

    private static final int NUM_PAGES = 3;
    ViewPager viewPager;
    ImageButton btnFirstPage, btnLastPage;
    PagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        btnFirstPage = findViewById(R.id.btn_firstpage);
        btnLastPage = findViewById(R.id.btn_lastpage);
        viewPager = findViewById(R.id.viewpager);
        viewPager.setOffscreenPageLimit(NUM_PAGES);
        adapter = new PagerAdapter(getSupportFragmentManager());

        Fragment1 fragment1 = new Fragment1();
        adapter.addItem(fragment1);
        Fragment2 fragment2 = new Fragment2();
        adapter.addItem(fragment2);
        Fragment3 fragment3 = new Fragment3();
        adapter.addItem(fragment3);

        viewPager.setAdapter(adapter);

        btnFirstPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(0);
            }
        });

        btnLastPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(NUM_PAGES-1);
            }
        });
    }

    class PagerAdapter extends FragmentStatePagerAdapter {
        ArrayList<Fragment> items = new ArrayList<Fragment>();

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addItem(Fragment item) {
            items.add(item);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return items.get(position);
        }

        @Override
        public int getCount() {
            return items.size();
        }
    }
}