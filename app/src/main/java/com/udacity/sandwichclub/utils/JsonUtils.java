package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class JsonUtils {


    public static Sandwich parseSandwichJson(String json) {
        Sandwich sandwich = new Sandwich();
        ArrayList<String> ingredients = new ArrayList<>();
        ArrayList<String> alsoKnownAsArrayList = new ArrayList<>();


        try {
            JSONObject root = new JSONObject(json);
            JSONObject nameObj = root.getJSONObject("name");
            JSONArray alsoKnownAsJsonArray = nameObj.getJSONArray("alsoKnownAs");
            String main_name = nameObj.getString("mainName");

            if(alsoKnownAsJsonArray.length()>0){

                for(int i=0; i<alsoKnownAsJsonArray.length(); i++){
                    String knownAsString = alsoKnownAsJsonArray.getString(i);
                    alsoKnownAsArrayList.add(knownAsString);

                }

            }
            else{
                alsoKnownAsArrayList.add("Not Applicable");
            }

            String placeOfOrigin = root.getString("placeOfOrigin");
            String desc = root.getString("description");
            String image = root.getString("image");
            Log.i("parseSandwichJson()",image);
            JSONArray ingredientsJsonArray = root.getJSONArray("ingredients");

            if(ingredientsJsonArray.length()>0){

                for(int i=0; i<ingredientsJsonArray.length(); i++){
                    String ingred = ingredientsJsonArray.optString(i);

                    ingredients.add(ingred);

                }

            }else{
                ingredients.add("Not Applicable");
            }



            sandwich.setAlsoKnownAs(alsoKnownAsArrayList);
            sandwich.setMainName(main_name);
            sandwich.setDescription(desc);
            sandwich.setImage(image);
            sandwich.setIngredients(ingredients);
            sandwich.setPlaceOfOrigin(placeOfOrigin);

        } catch (JSONException e) {
            Log.e("JSONUtils", "Problem parsing the earthquake JSON results", e);        }

        return sandwich;
    }
}
