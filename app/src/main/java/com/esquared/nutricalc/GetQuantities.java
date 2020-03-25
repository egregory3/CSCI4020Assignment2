package com.esquared.nutricalc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class GetQuantities extends AppCompatActivity {
TextView testTV;
Intent intent = getIntent();
Integer rows;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent.getIntExtra("Rows", 0);
        setContentView(R.layout.activity_get_quantities);
        testTV = findViewById(R.id.testTV);
        testTV.setText(rows.toString());

    }
}
