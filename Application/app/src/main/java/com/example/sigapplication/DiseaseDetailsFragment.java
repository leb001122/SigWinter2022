package com.example.sigapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DiseaseDetailsFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.result_details, container, false);
        super.onCreateView(inflater, container, savedInstanceState);
        Bundle args= getArguments();

        TextView titleTextView = (TextView) view.findViewById(R.id.selectedTitle);
        titleTextView.setText(args.getString("title"));
        TextView detailsTextView = (TextView) view.findViewById(R.id.detailsText);
        detailsTextView.setText(args.getString("details"));

        return view;
    }
}
