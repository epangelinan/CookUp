package com.epicodus.cookup;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private Button mFindRecipesButton;
    private EditText mIngredientEditText;
    private TextView mAppNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mIngredientEditText = (EditText) findViewById(R.id.ingredientEditText);
        mFindRecipesButton = (Button) findViewById(R.id.findRecipesButton);
        mAppNameTextView = (TextView) findViewById(R.id.appNameTextView);
        Typeface pacifico = Typeface.createFromAsset(getAssets(), "fonts/pacifico.ttf");
        mAppNameTextView.setTypeface(pacifico);

        mFindRecipesButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String ingredient = mIngredientEditText.getText().toString();
                Intent intent = new Intent(MainActivity.this, RecipesActivity.class);
                intent.putExtra("ingredient", ingredient);
                startActivity(intent);
            }
        });
    }
}
