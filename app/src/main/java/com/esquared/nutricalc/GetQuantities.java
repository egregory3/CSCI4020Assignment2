package com.esquared.nutricalc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class GetQuantities extends AppCompatActivity {
    public static ArrayList<String> myFoods = new ArrayList<String>();
    public static ArrayList<Integer> fdcIds = new ArrayList<Integer>();
    public static ArrayList<Double> fatCounts = new ArrayList<Double>();
    public static ArrayList<Double> satFatCounts = new ArrayList<Double>();
    public static ArrayList<Double> transFatCounts = new ArrayList<Double>();
    public static ArrayList<Double> cholesterolCounts = new ArrayList<Double>();
    public static ArrayList<Double> sodiumCounts = new ArrayList<Double>();
    public static ArrayList<Double> carbohydratesCounts = new ArrayList<Double>();
    public static ArrayList<Double> proteinCounts = new ArrayList<Double>();
    public static ArrayList<Double> calorieCounts = new ArrayList<Double>();
    public static ArrayList<String> servingSizeUnitCounts = new ArrayList<String>();
    public static ArrayList<Double> servingSizeCounts = new ArrayList<Double>();

    String api_key;
    @Override
    protected void onResume() {
        myFoods.clear();
        //fdcIds.clear();
        //fatCounts.clear();
        //satFatCounts.clear();
        //transFatCounts.clear();
        //cholesterolCounts.clear();
        //sodiumCounts.clear();
        //carbohydratesCounts.clear();
        //proteinCounts.clear();
        //calorieCounts.clear();
        //servingSizeUnitCounts.clear();
        //servingSizeCounts.clear();

        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        api_key = (String) getText(R.string.api_key);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_quantities);

        //Get Intent and Declare Variables to hold incoming intents
        Intent intent = getIntent();

        //get data from Intent
        final ArrayList<String> items = intent.getStringArrayListExtra("Items");

        LinearLayout myItems = findViewById(R.id.ll_itemsDisplay);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        final TextView[] tv = new TextView[items.size()];
        final EditText[] et = new EditText[items.size()];


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



        }
        Button btnAddItems = findViewById(R.id.btnAddRow);
        btnAddItems.setText("Add Ingredients");

        btnAddItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View myItems) {
                double temp = 0;
                double itemCalories = 0;
                double itemTotalFat = 0;
                double itemTransFat = 0;
                double itemSatFat = 0;
                double itemSodium = 0;
                double itemCarbs = 0;
                double itemProtein = 0;
                double recipeCalorieTotal = 0;
                double RecipeFatTotal =0;
                double RecipeSatFat = 0;
                double RecipeTrFat = 0;
                double RecipeSodium = 0;
                double RecipeCarbs = 0;
                double RecipeProtein = 0;
                Integer labelCalories = 0;
                Integer labelTFat = 0;
                Integer labelSatFat = 0;
                Integer labelTrFat = 0;
                Integer labelSodium = 0;
                Integer labelCarbs = 0;
                Integer labelProtein = 0;

                for (int i = 0; i < fdcIds.size(); i++) {
                    temp = Double.parseDouble(et[i].getText().toString());
                    itemCalories = (temp / servingSizeCounts.get(i)) * calorieCounts.get(i);
                    itemTotalFat = (temp/ servingSizeCounts.get(i)) + fatCounts.get(i);
                    itemTransFat = (temp/servingSizeCounts.get(i) + transFatCounts.get(i));
                    itemSatFat = (temp/servingSizeCounts.get(i)) + satFatCounts.get(i);
                    itemSodium = (temp/servingSizeCounts.get(i)) + sodiumCounts.get(i);
                    itemCarbs = (temp/servingSizeCounts.get(i)) + carbohydratesCounts.get(i);
                    itemProtein = (temp/servingSizeCounts.get(i)) + proteinCounts.get(i);
                    recipeCalorieTotal = recipeCalorieTotal + itemCalories;
                    RecipeFatTotal = RecipeFatTotal + itemTransFat;
                    RecipeSatFat = RecipeSatFat + itemSatFat;
                    RecipeTrFat = RecipeTrFat + itemTransFat;
                    RecipeSodium = RecipeSodium + itemSodium;
                    RecipeCarbs = RecipeCarbs + itemCarbs;
                    RecipeProtein = RecipeProtein + itemProtein;
                    tv[i].setText("There are " + String.valueOf(itemCalories) + " calories in the " + items.get(i));
                }

                //tv[1].setText("The total amount of calories is " + recipeCalorieTotal);
                labelCalories = (int) recipeCalorieTotal;
                labelTrFat = (int) RecipeTrFat;
                labelCarbs = (int) RecipeCarbs;
                labelSatFat = (int) RecipeSatFat;
                labelTFat = (int) RecipeFatTotal;
                labelSodium = (int) RecipeSodium;
                labelProtein = (int) RecipeProtein;

                Intent labelIntent = new Intent(getApplicationContext(), FoodLabel.class);
                labelIntent.putExtra("Calories", labelCalories);
                labelIntent.putExtra("TotalFat", labelTFat);
                labelIntent.putExtra("TransFat", labelTrFat);
                labelIntent.putExtra("Carbohydrates", labelCarbs);
                labelIntent.putExtra("SaturdatedFat", labelSatFat);
                labelIntent.putExtra("Sodium", labelSodium);
                labelIntent.putExtra("Protein", labelProtein);
                startActivity(labelIntent);


            }
        });

    }
}
