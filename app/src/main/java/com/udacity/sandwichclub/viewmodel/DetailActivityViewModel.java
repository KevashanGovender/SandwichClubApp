package com.udacity.sandwichclub.viewmodel;

import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.view.DetailActivityView;

import java.util.List;

public class DetailActivityViewModel {

    private DetailActivityView view;

    public DetailActivityViewModel(DetailActivityView view) {
        this.view = view;
    }

    public void isSandwichValid(Sandwich sandwich) {
        validateOtherNames(sandwich.getAlsoKnownAs());
        validatePlaceOfOrigin(sandwich.getPlaceOfOrigin());
        validateDescription(sandwich.getDescription());
        validateIngredients(sandwich.getIngredients());
    }

    private void validateOtherNames(List<String> names){
        if(names != null && !names.isEmpty()){
            view.showAlsoKnownAs(names);
        } else {
            view.noOtherNames();
        }
    }

    private void validatePlaceOfOrigin(String placeOfOrigin){
        if(placeOfOrigin != null && !placeOfOrigin.isEmpty()){
            view.showPlaceOfOrigin(placeOfOrigin);
        } else {
            view.noPlaceOfOrigin();
        }
    }

    private void validateDescription(String description){
        if(description != null && !description.isEmpty()){
            view.showDescription(description);
        } else {
            view.noDescription();
        }
    }

    private void validateIngredients(List<String> ingredients){
        if(ingredients != null && !ingredients.isEmpty()){
            view.showIngredients(ingredients);
        } else {
            view.noIngredients();
        }
    }
}
