package com.epicodus.cookup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {
    private Button mFindRecipesButton;
    private EditText mIngredientEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mIngredientEditText = (EditText) findViewById(R.id.ingredientEditText);
        mFindRecipesButton = (Button) findViewById(R.id.findRecipesButton);

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
