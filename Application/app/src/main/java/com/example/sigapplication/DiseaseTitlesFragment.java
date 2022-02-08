package com.example.sigapplication;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;

public class DiseaseTitlesFragment extends ListFragment {
    public interface OnTitleSelectedListener {
        public void onTitleSelected(int position);
    }

    OnTitleSelectedListener titleSelectedListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        showDetails(position);
    }

    public void showDetails(int position) {
        titleSelectedListener.onTitleSelected(position);
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            if (context instanceof Activity) {
                titleSelectedListener = (OnTitleSelectedListener) context;
            }
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement OnTitleSelectedListener");
        }
    }
}
