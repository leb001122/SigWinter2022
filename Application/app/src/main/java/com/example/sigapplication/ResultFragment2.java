package com.example.sigapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

public class ResultFragment2 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_result2, container, false);

        Button btnPrevFragment = rootView.findViewById(R.id.btn_prevFragment);
        btnPrevFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AiResult activity = (AiResult) getActivity();
                activity.onFragmentChanged(0);
            }
        });
        return rootView;
    }
}