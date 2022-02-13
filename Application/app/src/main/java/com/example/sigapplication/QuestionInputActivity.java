package com.example.sigapplication;

import androidx.annotation.LongDef;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
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
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QuestionInputActivity extends AppCompatActivity {

    private Button btnSend;
    private ImageButton btnClear;
    private EditText editText;
    private TextView inputCounter;
    private String inputText;
    private String url;
    private ArrayList<Disease> diseases;
    private MyAPI myAPI;
    final String TAG = getClass().getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_input);

        btnSend = (Button)findViewById(R.id.btn_send);
        btnClear = (ImageButton) findViewById(R.id.btn_clear);
        editText = (EditText) findViewById(R.id.edittext);
        inputCounter = (TextView) findViewById(R.id.textinput_counter);
        url = "https://e031-210-218-158-162.ngrok.io/";

        initMyAPI(url);

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

               if (inputText.length() == 0) {
                   Toast toast = Toast.makeText(getApplicationContext(), "증상을 입력하세요.", Toast.LENGTH_SHORT);
                   toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 70);
                   toast.show();
                   return;
               }

               Call<List<Disease>> call = myAPI.postDisease(inputText);
               call.enqueue(new Callback<List<Disease>>() {

                   @Override
                   public void onResponse(Call<List<Disease>> call, Response<List<Disease>> response) {
                       List<Disease> diseases = response.body();
                       System.out.println("개수 : " + diseases.size());
                       for (Disease disease : diseases) {
                           System.out.print(disease.toString());
                       }

                       Intent intent = new Intent(QuestionInputActivity.this, DiseaseListActivity.class);
                       intent.putExtra("diseaseList", (ArrayList) diseases);
                       startActivity(intent);

/*                       if (response.isSuccessful()) {

                       }
                       else {
                           Log.d(TAG, "Status Code : " + response.code());
                       }*/
                   }

                   @Override
                   public void onFailure(Call<List<Disease>> call, Throwable t) {
                       Log.e(TAG, "Fail msg : " + t.getMessage());
                   }
               });
           }
       });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.setText(null);
            }
        });
    }

    private void initMyAPI(String url) {

        Log.d(TAG, "initMyAPI : " + url);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        myAPI = retrofit.create(MyAPI.class);
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