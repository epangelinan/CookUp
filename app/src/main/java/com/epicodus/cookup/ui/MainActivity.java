package com.epicodus.cookup.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.cookup.Constants;
import com.epicodus.cookup.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

//    private SharedPreferences mSharedPreferences;
//    private SharedPreferences.Editor mEditor;

    private DatabaseReference mSearchedIngredientReference;

    @Bind(R.id.findRecipesButton) Button mFindRecipesButton;
    @Bind(R.id.aboutButton) Button mAboutButton;
    @Bind(R.id.ingredientEditText) EditText mIngredientEditText;
    @Bind(R.id.appNameTextView) TextView mAppNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mSearchedIngredientReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_CHILD_SEARCHED_INGREDIENT);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

//        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        mEditor = mSharedPreferences.edit();

        Typeface pacifico = Typeface.createFromAsset(getAssets(), "fonts/pacifico.ttf");
        mAppNameTextView.setTypeface(pacifico);

        mFindRecipesButton.setOnClickListener(this);
        mAboutButton.setOnClickListener(this);
    }

    @Override
    public void onClick (View v) {
        if (v == mFindRecipesButton) {
            String ingredient = mIngredientEditText.getText().toString();
            String regexStr = "^[0-9]*$";

            saveLocationToFirebase(ingredient);

            if ((mIngredientEditText.getText().toString().trim().length() == 0) || (mIngredientEditText.getText().toString().trim().matches(regexStr))) {
                Toast.makeText(MainActivity.this, "Please enter your main ingredient", Toast.LENGTH_LONG).show();
            } else {
 //               addToSharedPreferences(ingredient);
                Intent intent = new Intent(MainActivity.this, RecipeListActivity.class);
                intent.putExtra("ingredient", ingredient);
                startActivity(intent);
            }

        } else if (v == mAboutButton) {
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(intent);

        }
    }

    public void saveLocationToFirebase(String ingredient) {
        mSearchedIngredientReference.push().setValue(ingredient);
    }

 //       private void addToSharedPreferences(String ingredient) {
 //           mEditor.putString(Constants.PREFERENCES_INGREDIENT_KEY, ingredient).apply();
 //       }

}
