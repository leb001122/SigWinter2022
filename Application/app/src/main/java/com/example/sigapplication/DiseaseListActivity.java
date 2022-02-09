package com.example.sigapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DiseaseListActivity extends Activity implements View.OnClickListener {
    private ArrayList<Disease> diseases;
    private ListView listView;
    private RequestQueue queue;
    private DiseaseAdapter adapter;
    private String url;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        listView = (ListView) findViewById(R.id.disease_listview);
        diseases = new ArrayList<>();
        queue = Volley.newRequestQueue(getApplicationContext());
        url = "https://5c87-210-218-158-162.ngrok.io/appServer";

        // 서버에서 데이터 받아옴
        request(url);
/*
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("diseaseName", "감기");
        jsonObject.addProperty("description", "감기란 말이야");
        JsonObject jsonObject2 = new JsonObject();
        jsonObject2.addProperty("diseaseName", "독감");
        jsonObject2.addProperty("description", "독감이란 말이야");
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(jsonObject);
        jsonArray.add(jsonObject2);
        processResponse(jsonArray.toString());
        System.out.println(jsonArray.toString());*/

        adapter = new DiseaseAdapter(this, R.layout.result_item, diseases);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), DiseaseDetailsActivity.class);

                intent.putExtra("diseaseName", diseases.get(position).getDiseaseName());
                intent.putExtra("description", diseases.get(position).getDescription());
                startActivity(intent);
            }
        });
    }

    public void request(String url) {
        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("응답 -> ", response);
                        processResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("에러", error.toString());
                    }
                }

        );
        request.setShouldCache(false);
        queue.add(request);
        Log.d("로그 : ", "요청 보냄");
    }

    public void processResponse(String response) {
        Gson gson = new Gson();

        diseases = gson.fromJson(response, new TypeToken<ArrayList<Disease>>(){}.getType());
        int cnt = diseases.size();

        System.out.println("질병개수 : " + cnt);

        for (int i=0; i<cnt; i++) {
            System.out.println(diseases.get(i).getDiseaseName());
            System.out.println(diseases.get(i).getDescription());
            System.out.println();
        }

    }

    public void onClick(View v) {
    }

}
