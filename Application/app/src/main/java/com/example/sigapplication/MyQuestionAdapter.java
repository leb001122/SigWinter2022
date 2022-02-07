package com.example.sigapplication;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyQuestionAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<MyQuestion> list;

    public MyQuestionAdapter(Context context, ArrayList<MyQuestion> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = View.inflate(context, R.layout.question_list, null);

        //뷰에 다음 컴포넌트들을 연결시켜줌
        TextView date = (TextView)v.findViewById(R.id.date);
        TextView content = (TextView)v.findViewById(R.id.content);

        date.setText(list.get(i).getDate());
        content.setText(list.get(i).getContent());

        //만든뷰를 반환함
        return v;
    }
}
