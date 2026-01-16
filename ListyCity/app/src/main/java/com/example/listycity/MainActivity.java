package com.example.listycity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    Button addButton;
    Button deleteButton;
    int deletePosition = -1;
    EditText cityInput;
    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Configure Buttons
        addButton = findViewById(R.id.add);
        // Set addButton listener to insert text from cityInput into new entry to cityList. If cityInput is blank, do nothing.
        addButton.setOnClickListener(v -> {
            // The following code retrieving text input from cityInput is taken from Google, Gemini, "xml retrieve text from edittext", 2026-1-16
            String userInput = cityInput.getText().toString().trim();
            if (!userInput.isEmpty()) {
                dataList.add(userInput);
                cityAdapter.notifyDataSetChanged();
                cityInput.setText("");
            }
        });

        deleteButton = findViewById(R.id.delete);
        // Set deleteButton listener to delete selected element in cityList. If no element is selected, do nothing.
        deleteButton.setOnClickListener(v -> {
            if (deletePosition != -1) {
                dataList.remove(deletePosition);
                cityAdapter.notifyDataSetChanged();
                deletePosition = -1;
            }
        });

        // Configure Text Input
        cityInput = findViewById(R.id.city_input);

        // Configure CityList
        cityList = findViewById(R.id.city_list);

        String []cities = {"Edmonton", "Vancouver", "Toronto"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        // Set cityList OnClick listener to highlight selected item for deleteButton
        cityList.setOnItemClickListener((parent, view, position, id) -> {
            deletePosition = position;
        });

    }
}