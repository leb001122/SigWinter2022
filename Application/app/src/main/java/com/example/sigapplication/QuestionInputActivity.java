package com.example.sigapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class QuestionInputActivity extends AppCompatActivity {

    private Button btnSend;
    private ImageButton btnClear;
    private EditText editText;
    private TextView inputCounter;
    private String inputText;
    private String url;
    private ArrayList<Disease> diseases;
    static RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_input);

        btnSend = (Button)findViewById(R.id.btn_send);
        btnClear = (ImageButton) findViewById(R.id.btn_clear);
        editText = (EditText) findViewById(R.id.edittext);
        inputCounter = (TextView) findViewById(R.id.textinput_counter);
        queue = Volley.newRequestQueue(getApplicationContext());
        url = "https://ca59-210-218-158-162.ngrok.io/appServer/post";

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                String tmpStr = editText.getText().toString();
                inputCounter.setText(tmpStr.length() + "/128");
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputText = editText.getText().toString();
                System.out.println(inputText);

                if (inputText.length() == 0) {
                    Toast toast = Toast.makeText(getApplicationContext(), "증상을 입력하세요.", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 70);
                    toast.show();
                    return;
                }
                request(url);

                System.out.println(diseases.get(0).getDiseaseName());

                Intent intent = new Intent(QuestionInputActivity.this, DiseaseListActivity.class);
                intent.putExtra("diseaseList", diseases);
                startActivity(intent);
            }
        });


        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.setText(null);
            }
        });
    }

    public void request(String url) {
        StringRequest request = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("응답 -> ", response);
                        response(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("에러", error.toString());
                    }
                }

        ) {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("symptom", editText.getText().toString());
                return params;
            }
        };
        request.setShouldCache(false); //이전 응답 결과를 사용하지 않음
        queue.add(request);
        Log.d("로그 : ", "요청 보냄");
    }

    public void response(String jsonData) {
        Gson gson = new Gson();

        diseases = gson.fromJson(jsonData, new TypeToken<ArrayList<Disease>>(){}.getType());

        try {
            int cnt = diseases.size();
            System.out.println("질병개수 : " + cnt);

        } catch (NullPointerException e) {
           Log.d("로그", "nullpointerexception");
        }


        for (int i=0; i<5; i++) {
            System.out.println(diseases.get(i).getDiseaseName());
            System.out.println(diseases.get(i).getDescription());
            System.out.println();
        }
    }




    @Override
    protected void onPause() {
        super.onPause();
        saveState();
        Log.d("InputActivity", "onPause 호출됨");
    }
    @Override
    protected void onResume() {
        super.onResume();
        loadState();
        Log.d("InputActivity", "onResume 호출됨");
    }
    public void saveState() {
        SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("text", editText.getText().toString());
        editor.commit();
    }
    public void loadState() {
        SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        if (pref != null) {
            String text = pref.getString("text", "");
            editText.setText(text);
        }
    }
}