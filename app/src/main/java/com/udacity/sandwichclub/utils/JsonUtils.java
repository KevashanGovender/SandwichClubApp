package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) throws JSONException {

        JSONObject sandwich = new JSONObject(json);

        JSONObject name = sandwich.getJSONObject("name");
        String sandwichName = name.getString("mainName");

        ArrayList<String> alsoKnownAs = new ArrayList<>();
        JSONArray aka = name.getJSONArray("alsoKnownAs");
        for(int i = 0; i < aka.length(); i++ ){
            alsoKnownAs.add(aka.getString(i));
        }

        String placeOfOrigin = sandwich.getString("placeOfOrigin");
        String description = sandwich.getString("description");
        String image = sandwich.getString("image");

        ArrayList<String> ingredientsArray = new ArrayList<>();
        JSONArray ingretiants = sandwich.getJSONArray("ingredients");
        for(int i = 0; i < ingretiants.length(); i++ ){
            ingredientsArray.add(ingretiants.getString(i));
        }

        Sandwich sandwichToBeReturned = new Sandwich();
        sandwichToBeReturned.setMainName(sandwichName);
        sandwichToBeReturned.setAlsoKnownAs(alsoKnownAs);
        sandwichToBeReturned.setDescription(description);
        sandwichToBeReturned.setPlaceOfOrigin(placeOfOrigin);
        sandwichToBeReturned.setImage(image);
        sandwichToBeReturned.setIngredients(ingredientsArray);

        return sandwichToBeReturned;
    }
}
