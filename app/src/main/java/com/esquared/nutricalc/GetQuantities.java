package com.esquared.nutricalc;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class GetQuantities extends AppCompatActivity {
public static ArrayList <String> myFoods = new ArrayList<String>();
public static ArrayList <Integer> fdcIds = new ArrayList<Integer>();
String api_key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        api_key = (String) getText(R.string.api_key);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_quantities);

        //Get Intent and Declare Variables to hold incoming intents
        Intent intent = getIntent();

        LinearLayout myItems = findViewById(R.id.ll_itemsDisplay);

        //get data from Intent
        ArrayList<String> items = intent.getStringArrayListExtra("Items");


        //Create a row for each item in Items and add to TextView
        for(int i=0; i < items.size(); i++){
            String foodToSearch = items.get(i);

            final String[] myParams = {api_key, foodToSearch, String.valueOf(i)};
                    foodSearch process = new foodSearch();
                    process.execute(myParams);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            TextView tv = new TextView(this);
            tv.setText(myFoods.get(i) + " " + fdcIds.get(i));
            tv.setHeight(150);
            tv.setWidth(350);
            tv.setTextSize(25);

            myItems.addView(tv);

        }


    }
}
