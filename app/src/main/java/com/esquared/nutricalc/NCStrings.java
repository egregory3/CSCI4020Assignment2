package com.esquared.nutricalc;

import androidx.appcompat.app.AppCompatActivity;

public abstract class NCStrings extends AppCompatActivity {
    protected String baseURL = "https://api.nal.usda.gov/fdc/v1/";
    protected String searchPath = "search";
    protected String keyParam = "?api_key=";
    protected String generalSearchParam = "&generalSearchInput=";
}
