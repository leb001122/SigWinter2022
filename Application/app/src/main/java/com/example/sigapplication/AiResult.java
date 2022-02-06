package com.example.sigapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class AiResult extends AppCompatActivity {

    private final int FRAGMENT_1 = 1;
    private final int FRAGMENT_2 = 2;

    ResultFragment fragment;
    ResultFragment2 fragment2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ai_result);

        fragment = (ResultFragment) getSupportFragmentManager().findFragmentById(R.id.result1);
        fragment2 = new ResultFragment2();

    }

    public void onFragmentChanged(int index) {
        if (index == 0) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
        }
        else if (index == 1) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment2).commit();

        }
    }
}