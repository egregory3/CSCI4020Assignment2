package com.esquared.nutricalc;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class GetQuantities extends AppCompatActivity {
public static ArrayList <String> myFoods = new ArrayList<String>();
public static ArrayList <Integer> fdcIds = new ArrayList<Integer>();
    public static ArrayList<Double> fatCounts = new ArrayList<Double>();
    public static ArrayList<Double> satFatCounts = new ArrayList<Double>();
    public static ArrayList<Double> transFatCounts = new ArrayList<Double>();
    public static ArrayList<Double> cholesterolCounts = new ArrayList<Double>();
    public static ArrayList<Double> sodiumCounts = new ArrayList<Double>();
    public static ArrayList<Double> carbohydratesCounts = new ArrayList<Double>();
    //public static ArrayList <Double> fiberCounts = new ArrayList<Double>();
//public static ArrayList <Double> sugarCounts = new ArrayList<Double>();
    public static ArrayList<Double> proteinCounts = new ArrayList<Double>();
    //public static ArrayList <Double> calciumCounts = new ArrayList<Double>();
//public static ArrayList <Double> ironCounts = new ArrayList<Double>();
    public static ArrayList<Double> calorieCounts = new ArrayList<Double>();
    public static ArrayList<String> servingSizeUnitCounts = new ArrayList<String>();
    public static ArrayList<Double> servingSizeCounts = new ArrayList<Double>();

String api_key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        api_key = (String) getText(R.string.api_key);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_quantities);

        //Get Intent and Declare Variables to hold incoming intents
        Intent intent = getIntent();

        //get data from Intent
        ArrayList<String> items = intent.getStringArrayListExtra("Items");

        LinearLayout myItems = findViewById(R.id.ll_itemsDisplay);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        TextView[] tv = new TextView[items.size()];
        EditText[] et = new EditText[items.size()];


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

            final String[] myParams2 = {fdcIds.get(i).toString(), api_key};
            foodDetail process2 = new foodDetail();
            process2.execute(myParams2);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            tv[i] = new TextView(this);
            tv[i].setTextSize(25);
            tv[i].setLayoutParams(lp);
            tv[i].setId(i);
            tv[i].setText("How many " + servingSizeUnitCounts.get(i) + " of " + items.get(i) + " do you have?");
            myItems.addView(tv[i]);

            et[i] = new EditText(this);
            et[i].setTextSize(25);
            et[i].setLayoutParams(lp);
            et[i].setId(i);
            myItems.addView(et[i]);


            /*
            TextView tv = new TextView(this);
            tv.setText("How many " + servingSizeUnitCounts.get(i)+ " do you have? ");
            EditText et = new EditText(this);
            et.setHeight(150);
            et.setWidth(350);
            et.setTextSize(25);

            tv.setHeight(150);
            tv.setWidth(350);
            tv.setTextSize(25);

            myItems.addView(tv);
            */
        }
        Button btn = new Button(this);
        btn.setText("Add Up Ingredients");
        myItems.addView(btn);

    }
}
