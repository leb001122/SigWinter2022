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

public class QuestionInputActivity extends AppCompatActivity {

    Button btnDone;
    ImageButton btnClear;
    EditText editText;
    String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_input);

        btnDone = (Button)findViewById(R.id.btn_done);
        btnClear = (ImageButton) findViewById(R.id.btn_clear);
        editText = (EditText) findViewById(R.id.edittext);

        btnDone.setOnClickListener(new View.OnClickListener() {
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
                    Intent intent = new Intent(QuestionInputActivity.this, ResultActivity.class);
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