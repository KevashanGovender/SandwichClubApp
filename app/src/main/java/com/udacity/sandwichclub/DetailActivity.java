package com.udacity.sandwichclub;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;
import com.udacity.sandwichclub.view.DetailActivityView;
import com.udacity.sandwichclub.viewmodel.DetailActivityViewModel;

import org.json.JSONException;

import java.util.List;

public class DetailActivity extends AppCompatActivity implements DetailActivityView{

    private static final String TAG = DetailActivity.class.getSimpleName();

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    private TextView akaTv;
    private TextView placeOfOriginTv;
    private TextView descriptionTv;
    private TextView ingrediantsTv;
    private Sandwich sandwich;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);
        akaTv = findViewById(R.id.also_known_tv);
        placeOfOriginTv = findViewById(R.id.origin_tv);
        descriptionTv = findViewById(R.id.description_tv);
        ingrediantsTv = findViewById(R.id.ingredients_tv);

        getSandwich();

        DetailActivityViewModel viewModel = new DetailActivityViewModel(this);
        viewModel.isSandwichValid(sandwich);

        Picasso.with(this)
                .load(sandwich.getImage())
                .error(R.mipmap.ic_launcher)
                .placeholder(R.mipmap.ic_launcher)
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showAlsoKnownAs(List<String> otherNames) {
        akaTv.setText(TextUtils.join(", ", otherNames));
    }

    @Override
    public void noOtherNames() {
        showNoDataMessage(akaTv);
    }

    @Override
    public void showPlaceOfOrigin(String placeOfOrigin) {
        placeOfOriginTv.setText(placeOfOrigin);
    }

    @Override
    public void noPlaceOfOrigin() {
        showNoDataMessage(placeOfOriginTv);
    }

    @Override
    public void showDescription(String description) {
        descriptionTv.setText(description);
    }

    @Override
    public void noDescription() {
        showNoDataMessage(descriptionTv);
    }

    @Override
    public void showIngredients(List<String> ingredients) {
        ingrediantsTv.setText(TextUtils.join(", ", ingredients));
    }

    @Override
    public void noIngredients() {
        showNoDataMessage(ingrediantsTv);
    }

    private void showNoDataMessage(TextView view){
        view.setText(getString(R.string.no_data_message));
    }

    private void getSandwich(){
        if(getIntent() != null) {
            int position = getIntent().getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
            if (position == DEFAULT_POSITION) {
                // EXTRA_POSITION not found in intent
                closeOnError();
                return;
            }

            String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
            String json = sandwiches[position];
            try {
                sandwich = JsonUtils.parseSandwichJson(json);
            } catch (JSONException e) {
                Log.e(TAG, e.getMessage());
                closeOnError();
            }
        } else {
            closeOnError();
        }
    }
}
