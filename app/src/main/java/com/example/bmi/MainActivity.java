package com.example.bmi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int currentMode = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
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

            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    protected void onStart() {

        super.onStart();

        Button infoButton = findViewById(R.id.countButton);

        infoButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                EditText massEditText = findViewById(R.id.massEditText);
                EditText heightEditText = findViewById(R.id.heightEditText);
                TextView BMITextView = findViewById(R.id.BMITextView);

                try {

                    BMICalculator bmiCalculator = new BMICalculator(Double.valueOf(massEditText.getText().toString()), Double.valueOf(heightEditText.getText().toString()));
                    double bmi;

                    if (currentMode == 1) {
                        bmi = bmiCalculator.calculateBMI("imperial");
                    }
                    else {
                        bmi = bmiCalculator.calculateBMI("metric");
                    }

                    BMITextView.setTextSize(45);
                    BMITextView.setTextColor(findColorForBMI(bmi));
                    BMITextView.setText(String.format("%.2f", bmi));
                }
                catch (NumberFormatException | BMICalculator.ZeroNumberException ex) {

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
