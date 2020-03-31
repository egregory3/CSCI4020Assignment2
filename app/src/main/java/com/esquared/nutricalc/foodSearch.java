package com.esquared.nutricalc;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;



public class foodSearch  extends AsyncTask<String, Void, Void> {
    String data = "";

    @Override
    protected Void doInBackground(String... params) {
        try {
            Uri.Builder builder = Uri.parse("https://api.nal.usda.gov/fdc/v1/search").buildUpon();
            builder.appendQueryParameter("api_key", params[0]);
            builder.appendQueryParameter("generalSearchInput",params[1]);


            URL url = new URL(builder.toString());
            Log.i("RESULT", url.toString());

            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
            InputStream inputStream = httpsURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while(line != null){
                line = bufferedReader.readLine();
                data = data+line;

            }

            JSONObject JO = new JSONObject(data);
            JSONArray food = JO.getJSONArray("foods");
            Log.i("RESULT", "Food Length " + food.length());

            for(int i=0; i < 1; i++){
                JSONObject jsonobject = food.getJSONObject(i);
                for(int j=0; j<1; j++){
                    JSONObject onefood = (JSONObject) food.get(j);

                    String description = onefood.get("description").toString();
                    Log.i("RESULT", "Description set " + description);
                    GetQuantities.myFoods.add(description);

                    Integer fdcId = (Integer)onefood.get("fdcId");
                    Log.i("RESULT", "FDCID Set " + fdcId);
                    GetQuantities.fdcIds.add(fdcId);


                }
            }

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
