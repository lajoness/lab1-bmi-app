package com.example.bmi;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BMIActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_info);
    }

    @Override
    protected void onStart() {
        super.onStart();

        String bmi = getIntent().getStringExtra("bmi");

        TextView bmiValueTextView = findViewById(R.id.bmiValueTextView);
        bmiValueTextView.setTextSize(45);
        bmiValueTextView.setText(bmi);
    }
}
