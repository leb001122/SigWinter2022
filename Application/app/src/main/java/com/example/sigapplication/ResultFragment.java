package com.example.sigapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ResultFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_result, container, false);

        Button btnNextFragment = rootView.findViewById(R.id.btn_nextFragment);
        btnNextFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AiResult activity = (AiResult) getActivity();
                activity.onFragmentChanged(1);
            }
        });
        return rootView;
    }
}