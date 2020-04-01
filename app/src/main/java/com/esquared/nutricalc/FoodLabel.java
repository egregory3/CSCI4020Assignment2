package com.esquared.nutricalc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class FoodLabel extends AppCompatActivity {
TextView tv_calories;
TextView tv_TotalFat;
TextView tv_satFat;
TextView tv_transFat;
TextView tv_sodium;
TextView tv_carbs;
TextView tv_protein;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_label);
        tv_calories = findViewById(R.id.calories_qty);
        tv_TotalFat = findViewById(R.id.totalFat_qty);
        tv_satFat = findViewById(R.id.satFat_qty);
        tv_transFat = findViewById(R.id.transFat_qty);
        tv_sodium = findViewById(R.id.sodium_qty);
        tv_carbs = findViewById(R.id.carbohydrates_qty);
        tv_protein = findViewById(R.id.protein_qty);

        Intent labelIntent = getIntent();

        Integer calories = labelIntent.getIntExtra("Calories", 0);
        Integer TotalFat = labelIntent.getIntExtra("TotalFat", 0);
        Integer TransFat = labelIntent.getIntExtra("TransFat", 0);
        Integer SaturatedFat = labelIntent.getIntExtra("SaturdatedFat", 0);
        Integer Sodium = labelIntent.getIntExtra("Sodium", 0);
        Integer Carbohydrates = labelIntent.getIntExtra("Carbohydrates", 0);
        Integer Protein = labelIntent.getIntExtra("Protein", 0);
        String mycalories = calories.toString();
        String myTotalFat = TotalFat.toString();
        String myTransFat = TransFat.toString();
        String mySaturdatedFat = SaturatedFat.toString();
        String mySodium = Sodium.toString();
        String myCarbohydrates = Carbohydrates.toString();
        String myProtein = Protein.toString();
        tv_calories.setText(mycalories);
        tv_TotalFat.setText(myTotalFat);
        tv_satFat.setText(mySaturdatedFat);
        tv_transFat.setText(myTransFat);
        tv_sodium.setText(mySodium);
        tv_carbs.setText(myCarbohydrates);
        tv_protein.setText(myProtein);
    }
}
