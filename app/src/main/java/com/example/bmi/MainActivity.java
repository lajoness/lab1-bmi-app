package com.example.bmi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private int currentMode = 0;
    ArrayList bmiHistoryList = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        TextView massTextView = findViewById(R.id.massTextView);
        TextView heightTextView = findViewById(R.id.heightTextView);

        switch (item.getItemId()) {

            case R.id.metric:
                massTextView.setText(R.string.massM);
                heightTextView.setText(R.string.heightM);
                currentMode = 0;
                return true;

            case R.id.imperial:
                massTextView.setText(R.string.massI);
                heightTextView.setText(R.string.heightI);
                currentMode = 1;
                return true;

            case R.id.author:
                Intent authorIntent = new Intent(getApplicationContext(), AuthorActivity.class);
                startActivity(authorIntent);
                return true;

            case R.id.history:
                Intent historyIntent = new Intent(getApplicationContext(), HistoryActivity.class);
                startActivity(historyIntent);
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    protected void onStart() {

        super.onStart();
        final BMIViewModel viewModel = new ViewModelProvider(this).get(BMIViewModel.class);

        Button infoButton = findViewById(R.id.countButton);

        infoButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                EditText massEditText = findViewById(R.id.massEditText);
                EditText heightEditText = findViewById(R.id.heightEditText);
                TextView BMITextView = findViewById(R.id.BMITextView);

                try {

                    viewModel.height = Double.valueOf(heightEditText.getText().toString());
                    viewModel.mass = Double.valueOf(massEditText.getText().toString());

                    double bmi;

                    if (currentMode == 1) {
                        bmi = viewModel.calculateBMI("imperial");
                    }
                    else {
                        bmi = viewModel.calculateBMI("metric");
                    }

                    BMITextView.setTextSize(45);
                    BMITextView.setTextColor(findColorForBMI(bmi));
                    String bmiS = String.format("%.2f", bmi);
                    BMITextView.setText(bmiS);

                    SharedPreferences sharedPreferences = getSharedPreferences("SharedPref", MODE_PRIVATE);
                    SharedPreferences.Editor prefEditor = sharedPreferences.edit();

                    prefEditor.putString("bmi" + (sharedPreferences.getAll().size() + 1), bmiS).apply();
                }
                catch (NumberFormatException | BMIViewModel.ZeroNumberException ex) {

                    BMITextView.setTextSize(20);
                    BMITextView.setTextColor(Color.BLACK);
                    BMITextView.setText(R.string.error_msg);
                }
            }
        });

        final TextView BMITextView = findViewById(R.id.BMITextView);
        BMITextView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent bmiIntent = new Intent(getApplicationContext(), BMIActivity.class);
                String currBMI = BMITextView.getText().toString();
                bmiIntent.putExtra("bmi", currBMI);
                startActivity(bmiIntent);
            }
        });
    }

    public int findColorForBMI(double bmi){

        int color;

        if (bmi < 18.5){
            color = Color.BLUE;
        }
        else if (bmi < 25){
            color = Color.GREEN;
        }
        else if (bmi < 30){
            color = 0xffa500;
        }
        else {
            color = Color.RED;
        }

        return color;
    }
}
