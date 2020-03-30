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
import java.net.HttpURLConnection;
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
            JSONObject fat = labelNutrients.getJSONObject("fat");
            String fatAmount=fat.getString("value");
            Log.i("RESULT", "Fats are " + fatAmount);
            GetQuantities.fatCounts.add(Double.parseDouble(fatAmount));

            //pull saturated fat for food item
            JSONObject satFat = labelNutrients.getJSONObject("saturatedFat");
            String satFatAmount = satFat.getString("value");
            Log.i("RESULT", "Saturate dfats are " + satFatAmount);
            GetQuantities.satFatCounts.add(Double.parseDouble(satFatAmount));

            //pull trans fat for food item
            JSONObject transFat = labelNutrients.getJSONObject("transFat");
            String transFatAmount = transFat.getString("value");
            Log.i("RESULT", "Trans fats are " + transFatAmount);
            GetQuantities.transFatCounts.add(Double.parseDouble(transFatAmount));

            //pull cholesterol for food item
            JSONObject cholesterol = labelNutrients.getJSONObject("cholesterol");
            String cholesterolAmount = cholesterol .getString("value");
            Log.i("RESULT", "cholesterol are " + cholesterolAmount);
            GetQuantities.cholesterolCounts.add(Double.parseDouble(cholesterolAmount));

            //pull sodium for food item
            JSONObject sodium = labelNutrients.getJSONObject("sodium");
            String sodiumAmount = sodium.getString("value");
            Log.i("RESULT", "sodium are " + sodiumAmount);
            GetQuantities.sodiumCounts.add(Double.parseDouble(sodiumAmount));


            //pull carbohydrates for food item
            JSONObject carbohydrates = labelNutrients.getJSONObject("carbohydrates");
            String carbohydratesAmount = carbohydrates.getString("value");
            Log.i("RESULT", "carbohydrates are " + carbohydratesAmount);
            GetQuantities.carbohydratesCounts.add(Double.parseDouble(carbohydratesAmount));

            //pull fiber for food item
            JSONObject fiber = labelNutrients.getJSONObject("fiber");
            String fiberAmount = fiber.getString("value");
            Log.i("RESULT", "fiber are " + fiberAmount);
            GetQuantities.fiberCounts.add(Double.parseDouble(fiberAmount));

            //pull sugar for food item
            JSONObject sugar = labelNutrients.getJSONObject("sugars");
            String sugarAmount = sugar.getString("value");
            Log.i("RESULT", "sugar are " + sugarAmount);
            GetQuantities.sugarCounts.add(Double.parseDouble(sugarAmount));

            //pull protein for food item
            JSONObject protein = labelNutrients.getJSONObject("protein");
            String proteinAmount = protein.getString("value");
            Log.i("RESULT", "protein are " + proteinAmount);
            GetQuantities.proteinCounts.add(Double.parseDouble(proteinAmount));

            //pull calcium for food item
            JSONObject calcium= labelNutrients.getJSONObject("calcium");
            String calciumAmount = calcium.getString("value");
            Log.i("RESULT", "calcium are " + calciumAmount);
            GetQuantities.calciumCounts.add(Double.parseDouble(calciumAmount));

            //pull iron for food item
            JSONObject iron =labelNutrients.getJSONObject("iron");
            String ironAmount = iron.getString("value");
            Log.i("RESULT", "iron are " + ironAmount);
            GetQuantities.ironCounts.add(Double.parseDouble(ironAmount));

            //pull calorie amount for food item
            JSONObject calories = labelNutrients.getJSONObject("calories");
            String calorieAmount = calories.getString("value");
            Log.i("RESULT", "Calories are " + calorieAmount);
            GetQuantities.calorieCounts.add(Double.parseDouble(calorieAmount));


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
