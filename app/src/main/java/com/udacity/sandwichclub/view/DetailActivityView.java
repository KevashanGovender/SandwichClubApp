package com.udacity.sandwichclub.view;

import java.util.List;

public interface DetailActivityView {

    void showAlsoKnownAs(List<String> otherNames);

    void noOtherNames();

    void showPlaceOfOrigin(String placeOfOrigin);

    void noPlaceOfOrigin();

    void showDescription(String description);

    void noDescription();

    void showIngredients(List<String> ingredients);

    void noIngredients();
}
