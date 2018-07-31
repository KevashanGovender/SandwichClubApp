package com.udacity.sandwichclub.viewmodel;

import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.view.DetailActivityView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DetailActivityViewModelTest {

    @Mock
    private DetailActivityView view;
    @Mock
    private Sandwich sandwich;

    private List<String> otherNames;
    private DetailActivityViewModel viewModel;
    private List<String> ingrediants;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);

        otherNames = new ArrayList<>();
        viewModel = new DetailActivityViewModel(view);
        ingrediants = new ArrayList<>();
    }

    @Test
    public void shouldShowAlsoKnownAsDataIfNotNull (){
        otherNames.add("Name 2");
        otherNames.add("Name 3");
        when(sandwich.getAlsoKnownAs()).thenReturn(otherNames);

        viewModel.isSandwichValid(sandwich);

        verify(view).showAlsoKnownAs(otherNames);
    }

    @Test
    public void shouldShowNoDataMessageIf_AlsoKnownAsIsEmpty(){
        when(sandwich.getAlsoKnownAs()).thenReturn(otherNames);

        viewModel.isSandwichValid(sandwich);

        verify(view).noOtherNames();
    }

    @Test
    public void showShowNoDataMessageIf_AlsoKnownIsNull() {
        when(sandwich.getAlsoKnownAs()).thenReturn(null);

        viewModel.isSandwichValid(sandwich);

        verify(view).noOtherNames();
    }

    @Test
    public void showPlaceOfOriginIf_NotNull() {
        when(sandwich.getPlaceOfOrigin()).thenReturn("South Africa");

        viewModel.isSandwichValid(sandwich);

        verify(view).showPlaceOfOrigin(sandwich.getPlaceOfOrigin());
    }

    @Test
    public void showNoDataMessageIf_PlaceOfOriginIsNull(){
        when(sandwich.getPlaceOfOrigin()).thenReturn(null);

        viewModel.isSandwichValid(sandwich);

        verify(view).noPlaceOfOrigin();
    }

    @Test
    public void showNoDataMessageIf_PlaceOfOriginIsEmpty() {
        when(sandwich.getPlaceOfOrigin()).thenReturn("");

        viewModel.isSandwichValid(sandwich);

        verify(view).noPlaceOfOrigin();
    }

    @Test
    public void showDescriptionIf_NotNull() {
        when(sandwich.getDescription()).thenReturn("Description");

        viewModel.isSandwichValid(sandwich);

        verify(view).showDescription(sandwich.getDescription());
    }

    @Test
    public void showNoDataMessageIf_DescriptionIsNull() {
        when(sandwich.getDescription()).thenReturn(null);

        viewModel.isSandwichValid(sandwich);

        verify(view).noDescription();
    }

    @Test
    public void showNoDataMessageIf_DescriptionIsEmpty(){
        when(sandwich.getDescription()).thenReturn("");

        viewModel.isSandwichValid(sandwich);

        verify(view).noDescription();
    }

    @Test
    public void showIngrediantsIf_NotNull(){
        ingrediants.add("Bread");
        when(sandwich.getIngredients()).thenReturn(ingrediants);

        viewModel.isSandwichValid(sandwich);

        verify(view).showIngredients(ingrediants);
    }

    @Test
    public void showNoDataMessageIf_IngredientsIsNull() {
        when(sandwich.getIngredients()).thenReturn(null);

        viewModel.isSandwichValid(sandwich);

        verify(view).noIngredients();
    }

    @Test
    public void showNoDataMessageIf_IngredientsIsEmpty() {
        when(sandwich.getIngredients()).thenReturn(ingrediants);

        viewModel.isSandwichValid(sandwich);

        verify(view).noIngredients();
    }
}