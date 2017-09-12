package com.epicodus.cookup;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.findRecipesButton) Button mFindRecipesButton;
    @Bind(R.id.aboutButton) Button mAboutButton;
    @Bind(R.id.ingredientEditText) EditText mIngredientEditText;
    @Bind(R.id.appNameTextView) TextView mAppNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Typeface pacifico = Typeface.createFromAsset(getAssets(), "fonts/pacifico.ttf");
        mAppNameTextView.setTypeface(pacifico);

        mFindRecipesButton.setOnClickListener(this);
        mAboutButton.setOnClickListener(this);
    }

    @Override
    public void onClick (View v){
        if (v == mFindRecipesButton) {
            String ingredient = mIngredientEditText.getText().toString();
            String regexStr = "^[0-9]*$";
            if((mIngredientEditText.getText().toString().trim().length() == 0) || (mIngredientEditText.getText().toString().trim().matches(regexStr))) {
                Toast.makeText(MainActivity.this, "Please enter your main ingredient", Toast.LENGTH_LONG).show();
            } else {
                Intent intent = new Intent(MainActivity.this, RecipesActivity.class);
                intent.putExtra("ingredient", ingredient);
                startActivity(intent);
            }

        } else if (v == mAboutButton) {
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(intent);
        }
    }

}
