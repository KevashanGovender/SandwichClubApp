package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonUtils {

    private static final String NAME_JSON_KEY = "name";
    private static final String MAIN_NAME_JSON_KEY = "mainName";
    private static final String ALSO_KNOWN_AS_JSON_KEY = "alsoKnownAs";
    private static final String PLACE_OF_ORIGIN_JSON_KEY = "placeOfOrigin";
    private static final String DESCRIPTION_JSON_KEY = "description";
    private static final String IMAGE_JSON_KEY = "image";
    private static final String INGREDIENTS_JSON_KEY = "ingredients";
    private static final String NO_INFORMATION_JSON_FALLBACK = "No information";

    public static Sandwich parseSandwichJson(String json) throws JSONException {

        JSONObject sandwich = new JSONObject(json);

        JSONObject name = sandwich.optJSONObject(NAME_JSON_KEY);
        String sandwichName = name.optString(MAIN_NAME_JSON_KEY, NO_INFORMATION_JSON_FALLBACK);

        ArrayList<String> alsoKnownAs = new ArrayList<>();
        JSONArray aka = name.optJSONArray(ALSO_KNOWN_AS_JSON_KEY);
        for(int i = 0; i < aka.length(); i++ ){
            alsoKnownAs.add(aka.optString(i, NO_INFORMATION_JSON_FALLBACK));
        }

        String placeOfOrigin = sandwich.optString(PLACE_OF_ORIGIN_JSON_KEY, NO_INFORMATION_JSON_FALLBACK);
        String description = sandwich.optString(DESCRIPTION_JSON_KEY, NO_INFORMATION_JSON_FALLBACK);
        String image = sandwich.optString(IMAGE_JSON_KEY, NO_INFORMATION_JSON_FALLBACK);

        ArrayList<String> ingredientsArray = new ArrayList<>();
        JSONArray ingretiants = sandwich.optJSONArray(INGREDIENTS_JSON_KEY);
        for(int i = 0; i < ingretiants.length(); i++ ){
            ingredientsArray.add(ingretiants.optString(i, NO_INFORMATION_JSON_FALLBACK));
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
