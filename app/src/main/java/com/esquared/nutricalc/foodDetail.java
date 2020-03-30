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


            JSONObject JO = new JSONObject(data);
            JSONObject labelNutrients = JO.getJSONObject("labelNutrients");

            //pull fat amount for food item
            String fatAmount;
            try {
                JSONObject fat = labelNutrients.getJSONObject("fat");
                fatAmount = fat.getString("value");
            } catch (JSONException e) {
                fatAmount = "0.0";
            }
            Log.i("RESULT", "Fats are " + fatAmount);
            GetQuantities.fatCounts.add(Double.parseDouble(fatAmount));

            //pull saturated fat for food item
            String satFatAmount;
            try {
                JSONObject satFat = labelNutrients.getJSONObject("saturatedFat");
                satFatAmount = satFat.getString("value");
            } catch (JSONException e) {
                satFatAmount = "0.0";
            }
            Log.i("RESULT", "Saturated fats are " + satFatAmount);
            GetQuantities.satFatCounts.add(Double.parseDouble(satFatAmount));

            //pull trans fat for food item
            String transFatAmount;
            try {
                JSONObject transFat = labelNutrients.getJSONObject("transFat");
                transFatAmount = transFat.getString("value");
            } catch (JSONException e) {
                transFatAmount = "0.0";
            }
            Log.i("RESULT", "Trans fats are " + transFatAmount);
            GetQuantities.transFatCounts.add(Double.parseDouble(transFatAmount));

            //pull cholesterol for food item
            String cholesterolAmount;
            try {
                JSONObject cholesterol = labelNutrients.getJSONObject("cholesterol");
                cholesterolAmount = cholesterol.getString("value");
            } catch (JSONException e) {
                cholesterolAmount = "0.0";
            }
            Log.i("RESULT", "cholesterol are " + cholesterolAmount);
            GetQuantities.cholesterolCounts.add(Double.parseDouble(cholesterolAmount));

            //pull sodium for food item
            String sodiumAmount;
            try {
                JSONObject sodium = labelNutrients.getJSONObject("sodium");
                sodiumAmount = sodium.getString("value");
            } catch (JSONException e) {
                sodiumAmount = "0.0";
            }
            Log.i("RESULT", "sodium are " + sodiumAmount);
            GetQuantities.sodiumCounts.add(Double.parseDouble(sodiumAmount));

            //pull carbohydrates for food item
            String carbohydratesAmount;
            try {
                JSONObject carbohydrates = labelNutrients.getJSONObject("carbohydrates");
                carbohydratesAmount = carbohydrates.getString("value");
            } catch (JSONException e) {
                carbohydratesAmount = "0.0";
            }
            Log.i("RESULT", "carbohydrates are " + carbohydratesAmount);
            GetQuantities.carbohydratesCounts.add(Double.parseDouble(carbohydratesAmount));

            /*
            //pull fiber for food item
            JSONObject fiber = labelNutrients.getJSONObject("fiber");
            String fiberAmount = fiber.getString("value");
            Log.i("RESULT", "fiber are " + fiberAmount);
            GetQuantities.fiberCounts.add(Double.parseDouble(fiberAmount));
            */

            /*
            //pull sugar for food item
            JSONObject sugar = labelNutrients.getJSONObject("sugars");
            String sugarAmount = sugar.getString("value");
            Log.i("RESULT", "sugar are " + sugarAmount);
            GetQuantities.sugarCounts.add(Double.parseDouble(sugarAmount));
            */


            //pull protein for food item
            String proteinAmount;
            try {
                JSONObject protein = labelNutrients.getJSONObject("protein");
                proteinAmount = protein.getString("value");
            } catch (JSONException e) {
                proteinAmount = "0.0";
            }
            Log.i("RESULT", "protein are " + proteinAmount);
            GetQuantities.proteinCounts.add(Double.parseDouble(proteinAmount));

            /*
            //pull calcium for food item
            JSONObject calcium= labelNutrients.getJSONObject("calcium");
            String calciumAmount = calcium.getString("value");
            Log.i("RESULT", "calcium are " + calciumAmount);
            GetQuantities.calciumCounts.add(Double.parseDouble(calciumAmount));
            */

           /*
            //pull iron for food item
            JSONObject iron =labelNutrients.getJSONObject("iron");
            String ironAmount = iron.getString("value");
            Log.i("RESULT", "iron are " + ironAmount);
            GetQuantities.ironCounts.add(Double.parseDouble(ironAmount));
            */

            //pull calorie amount for food item
            String calorieAmount;
            try {
                JSONObject calories = labelNutrients.getJSONObject("calories");
                calorieAmount = calories.getString("value");
            } catch (JSONException e) {
                calorieAmount = "0.0";
            }
            Log.i("RESULT", "Calories are " + calorieAmount);
            GetQuantities.calorieCounts.add(Double.parseDouble(calorieAmount));

            //pull serving size for food item
            String servingSizeUnit = JO.getString("servingSizeUnit");
            Log.i("RESULT", "Serving size unit is " + servingSizeUnit);
            GetQuantities.servingSizeUnitCounts.add(servingSizeUnit);

            //pull serving size for food item;
            String servingSize = JO.getString("servingSize");
            Log.i("RESULT","Serving size is " + servingSize);
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
