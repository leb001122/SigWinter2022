package com.example.sigapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;

public class AiResult extends AppCompatActivity {

    private ViewPager viewPager ;
    private TextViewPagerAdapter pagerAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ai_result);

        viewPager = (ViewPager) findViewById(R.id.viewPager) ;
        pagerAdapter = new TextViewPagerAdapter(this) ;
        viewPager.setAdapter(pagerAdapter) ;

    }
}