package com.example.sigapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SymptomInput extends AppCompatActivity {

    Button btnDone;
    EditText editText;
    String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptom_input);

        btnDone = (Button) findViewById(R.id.btn_done);
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
                // 글자수 제한 및 특정 글자수 입력해야 넘어가도록
                startActivity(new Intent(SymptomInput.this, AiResult.class));

            }
        });
    }
}