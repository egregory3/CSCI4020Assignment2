package com.esquared.nutricalc;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class GetQuantities extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_quantities);

        //Get Intent and Declare Variables to hold incoming intents
        Intent intent = getIntent();

        LinearLayout myItems = findViewById(R.id.ll_itemsDisplay);

        //get data from Intent
        ArrayList<String> items = intent.getStringArrayListExtra("Items");

        //Create a row for each item in Items and add to TextView
        for(int i=0; i < items.size(); i++){

            TextView tv = new TextView(this);
            tv.setText(items.get(i));
            tv.setHeight(150);
            tv.setWidth(350);
            tv.setTextSize(25);

            myItems.addView(tv);

        }


    }
}
