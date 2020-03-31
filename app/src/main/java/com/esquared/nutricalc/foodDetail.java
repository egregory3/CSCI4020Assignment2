package com.esquared.nutricalc;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class foodDetail extends AsyncTask<String, Void, Void> {

    @Override
    protected Void doInBackground(String... params) {
        String data = "";

        try {

            Uri.Builder builder = Uri.parse("https://api.nal.usda.gov/fdc/v1/").buildUpon();
            builder.appendPath(params[0]);
            builder.appendQueryParameter("api_key", params[1]);

            URL url = new URL(builder.toString());

            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
            InputStream inputStream = httpsURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while (line != null) {
                line = bufferedReader.readLine();
                data = data + line;
            }

            String fatAmount;
            String satFatAmount;
            String transFatAmount;
            String cholesterolAmount;
            String sodiumAmount;
            String carbohydratesAmount;
            String proteinAmount;
            String calorieAmount;
            String servingSizeUnit;
            String servingSize;


            JSONObject JO = new JSONObject(data);
            JSONObject labelNutrients;
            if (JO.has("labelNutrients")) {
                labelNutrients = JO.getJSONObject("labelNutrients");
            } else {
                labelNutrients = new JSONObject();
                labelNutrients.put("Value", "No data provided");

            }

            //pull fat amount for food item
            try {
                JSONObject fat = labelNutrients.getJSONObject("fat");
                fatAmount = fat.getString("value");
            } catch (JSONException e) {
                fatAmount = "0.0";
            }
            Log.i("RESULT", "Fats are " + fatAmount);
            GetQuantities.fatCounts.add(Double.parseDouble(fatAmount));

            //pull saturated fat for food item
            try {
                JSONObject satFat = labelNutrients.getJSONObject("saturatedFat");
                satFatAmount = satFat.getString("value");
            } catch (JSONException e) {
                satFatAmount = "0.0";
            }
            Log.i("RESULT", "Saturated fats are " + satFatAmount);
            GetQuantities.satFatCounts.add(Double.parseDouble(satFatAmount));

            //pull trans fat for food item
            try {
                JSONObject transFat = labelNutrients.getJSONObject("transFat");
                transFatAmount = transFat.getString("value");
            } catch (JSONException e) {
                transFatAmount = "0.0";
            }
            Log.i("RESULT", "Trans fats are " + transFatAmount);
            GetQuantities.transFatCounts.add(Double.parseDouble(transFatAmount));

            //pull cholesterol for food item
            try {
                JSONObject cholesterol = labelNutrients.getJSONObject("cholesterol");
                cholesterolAmount = cholesterol.getString("value");
            } catch (JSONException e) {
                cholesterolAmount = "0.0";
            }
            Log.i("RESULT", "cholesterol are " + cholesterolAmount);
            GetQuantities.cholesterolCounts.add(Double.parseDouble(cholesterolAmount));

            //pull sodium for food item
            try {
                JSONObject sodium = labelNutrients.getJSONObject("sodium");
                sodiumAmount = sodium.getString("value");
            } catch (JSONException e) {
                sodiumAmount = "0.0";
            }
            Log.i("RESULT", "sodium are " + sodiumAmount);
            GetQuantities.sodiumCounts.add(Double.parseDouble(sodiumAmount));

            //pull carbohydrates for food item
            try {
                JSONObject carbohydrates = labelNutrients.getJSONObject("carbohydrates");
                carbohydratesAmount = carbohydrates.getString("value");
            } catch (JSONException e) {
                carbohydratesAmount = "0.0";
            }
            Log.i("RESULT", "carbohydrates are " + carbohydratesAmount);
            GetQuantities.carbohydratesCounts.add(Double.parseDouble(carbohydratesAmount));


            //pull protein for food item
            try {
                JSONObject protein = labelNutrients.getJSONObject("protein");
                proteinAmount = protein.getString("value");
            } catch (JSONException e) {
                proteinAmount = "0.0";
            }
            Log.i("RESULT", "protein are " + proteinAmount);
            GetQuantities.proteinCounts.add(Double.parseDouble(proteinAmount));

            //pull calories for food item
            try {
                JSONObject calories = labelNutrients.getJSONObject("calories");
                calorieAmount = calories.getString("value");
            } catch (JSONException e) {
                calorieAmount = "0.0";
            }
            Log.i("RESULT", "Calories are " + calorieAmount);
            GetQuantities.calorieCounts.add(Double.parseDouble(calorieAmount));

            //pull serving size unit for food item
            try {
                servingSizeUnit = JO.getString("servingSizeUnit");
            } catch (JSONException e) {
                servingSizeUnit = "NA";
            }
            Log.i("RESULT", "Serving size unit is " + servingSizeUnit);
            GetQuantities.servingSizeUnitCounts.add(servingSizeUnit);

            //pull serving size for food item;
            try {
                servingSize = JO.getString("servingSize");
            } catch (JSONException e) {
                servingSize = "0.0";
            }
            Log.i("RESULT", "Serving size is " + servingSize);
            GetQuantities.servingSizeCounts.add(Double.parseDouble(servingSize));


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

    }
}
