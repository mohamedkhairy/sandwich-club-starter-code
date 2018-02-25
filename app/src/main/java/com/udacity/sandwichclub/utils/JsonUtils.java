package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonUtils {

    private static Sandwich sandwich = new Sandwich();

    public static Sandwich parseSandwichJson(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);

            JSONObject name = jsonObject.getJSONObject("name");
            String mainName = name.getString("mainName");
            JSONArray alsoKnown = name.getJSONArray("alsoKnownAs");
            ArrayList<String> alsoKnown_array = new ArrayList<>();

            for (int x=0; x<alsoKnown.length(); x++){
               alsoKnown_array.add(alsoKnown.getString(x));
            }

            String placeOfOrigin = jsonObject.getString("placeOfOrigin");
            String description = jsonObject.getString("description");
            String image = jsonObject.getString("image");
            JSONArray ingredients = jsonObject.getJSONArray("ingredients");
            ArrayList<String> ingredients_array = new ArrayList<>();

            for (int x=0; x<ingredients.length(); x++){
               ingredients_array.add(ingredients.getString(x));

            }

            sandwich.setMainName(mainName);
            sandwich.setAlsoKnownAs(alsoKnown_array);
            sandwich.setDescription(description);
            sandwich.setIngredients(ingredients_array);
            sandwich.setPlaceOfOrigin(placeOfOrigin);
            sandwich.setImage(image);

            return sandwich;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;

    }
}
