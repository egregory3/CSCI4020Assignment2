package com.esquared.nutricalc;

public class foodItem {
    public String name;
    public int fdcid;
    public String measurementType;
    public float servingSize;
    public int quantity;
    public int calories;
    public int fat;
    public int satFat;
    public int cholesterol;
    public int sodium;
    public int totalCarb;
    public int dietaryFiber;
    public int sugars;
    public int protein;

    public foodItem(String description, int fdcId) {

        description = name;
        fdcid = fdcid;
        measurementType = measurementType;
        servingSize = servingSize;
    }

    //update a food item with nutrient values
    public void setNutrients(int calories, int fat, int satFat, int cholesterol, int sodium, int totalCarb, int dietaryFiber, int sugars, int protein){
        calories = calories;
        fat = fat;
        satFat = satFat;
        cholesterol = cholesterol;
        sodium = sodium;
        totalCarb = totalCarb;
        dietaryFiber = dietaryFiber;
        sugars = sugars;
        protein = protein;
    }

    public String getName() {
        return name;
    }

    public int getFdcid() {
        return fdcid;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getCalories() {
        return calories;
    }

    public int getFat() {
        return fat;
    }

    public int getSatFat() {
        return satFat;
    }

    public int getCholesterol() {
        return cholesterol;
    }

    public int getSodium() {
        return sodium;
    }

    public int getTotalCarb() {
        return totalCarb;
    }

    public int getDietaryFiber() {
        return dietaryFiber;
    }

    public int getSugars() {
        return sugars;
    }

    public int getProtein() {
        return protein;
    }
}
