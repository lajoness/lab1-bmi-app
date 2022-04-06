package com.example.bmi;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class HistoryActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        SharedPreferences sharedPreferences = getSharedPreferences("SharedPref", MODE_PRIVATE);

        int id = sharedPreferences.getAll().size();
        int numberToShow = 10;

        if (id < 10) {

            numberToShow = id;
        }

        String[] bmiHistoryArray = new String[numberToShow];

        for (int i = 0; i < numberToShow; i++) {

            bmiHistoryArray[i] = sharedPreferences.getString("bmi" + id, "");
            id--;
        }

        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.history_list_view, bmiHistoryArray);

        ListView listView = (ListView) findViewById(R.id.historyListView);
        listView.setAdapter(adapter);
    }
}
