package com.example.sigapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class QuestionInputActivity extends AppCompatActivity {

    private Button btnSend;
    private ImageButton btnClear;
    private EditText editText;
    private String text;
    private RequestQueue queue;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_input);

        btnSend = (Button)findViewById(R.id.btn_send);
        btnClear = (ImageButton) findViewById(R.id.btn_clear);
        editText = (EditText) findViewById(R.id.edittext);
        queue = Volley.newRequestQueue(getApplicationContext());
        url = "https://5c87-210-218-158-162.ngrok.io/appServer";

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text = editText.getText().toString();

                if (text.length() == 0) {
                    Toast toast = Toast.makeText(getApplicationContext(), "증상을 입력하세요.", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 70);
                    toast.show();
                }
                else {
                    // 글자수 제한 및 특정 글자수 입력해야 넘어가도록
                    request(url);
                    Intent intent = new Intent(QuestionInputActivity.this, DiseaseListActivity.class);
                    startActivity(intent);

                }
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
                params.put("input", editText.getText().toString());
                return params;
            }
        };
        request.setShouldCache(false);
        queue.add(request);
        Log.d("로그 : ", "요청 보냄");
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