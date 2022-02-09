package com.example.sigapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class DiseaseAdapter extends BaseAdapter {
    LayoutInflater inflater;
    ArrayList<Disease> diseases;
    int layout;

    public DiseaseAdapter(Context context, int layout, ArrayList<Disease> diseases) {
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.diseases = diseases;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return diseases.size();
    }

    @Override
    public Disease getItem(int position) {
        return diseases.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            view = inflater.inflate(layout, parent, false);
        }
        Disease disease = diseases.get(position);

        TextView diseaseName = (TextView) view.findViewById(R.id.textview);
        diseaseName.setText(disease.getDiseaseName());

        return view;
    }
}
