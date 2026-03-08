package com.example.androiduitesting;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ShowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitvity_show);

        TextView cityNameText = findViewById(R.id.cityNameText);
        Button backButton = findViewById(R.id.backButton);

        // Get the city name from the Intent
        String cityName = getIntent().getStringExtra("city_name");
        cityNameText.setText(cityName);

        // Back button returns to previous activity
        backButton.setOnClickListener(v -> finish());
    }
}