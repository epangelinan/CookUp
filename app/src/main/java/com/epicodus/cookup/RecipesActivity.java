package com.epicodus.cookup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class RecipesActivity extends AppCompatActivity {
    private TextView mLIngredientTextView;
    private ListView mListView;
    private String[] recipes = new String[] {"Chicken Parmesan", "Buffalo Chicken Wings",
            "Chicken Masala", "Curry Chicken Salad", "Chicken Pot Pie", "Spicy Chicken Soup",
            "Honey-Garlic Chicken", "Rosemary Chicken Kabobs", "Chicken Tortilla Soup", "Chicken Cordon Bleu",
            "Indian Butter Chicken", "Holiday Chicken Salad", "Sweet Hot Mustard Chicken",
            "General Tsao's Chicken", "Chicken Asparagus Roll-ups"};

    private TextView mIngredientTextView;
    private TextView mAppNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

        mListView = (ListView) findViewById(R.id.listView);
        mIngredientTextView = (TextView) findViewById(R.id.ingredientTextView);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, recipes);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String recipe = ((TextView)view).getText().toString();
                Toast.makeText(RecipesActivity.this, recipe, Toast.LENGTH_LONG).show();
            }
        });

        Intent intent = getIntent();
        String ingredient = intent.getStringExtra("ingredient");
        mIngredientTextView.setText("Here are all the recipes containing " + ingredient);

    }
}
