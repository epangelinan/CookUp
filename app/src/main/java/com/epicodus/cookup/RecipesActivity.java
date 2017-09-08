package com.epicodus.cookup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class RecipesActivity extends AppCompatActivity {
    private TextView mIngredientTextView;
    private TextView mAppNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);
        mIngredientTextView = (TextView) findViewById(R.id.ingredientTextView);
        Intent intent = getIntent();
        String ingredient = intent.getStringExtra("ingredient");
        mIngredientTextView.setText("Here are all the recipes containing " + ingredient);

    }
}
