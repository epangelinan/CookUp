package com.epicodus.cookup.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.cookup.Constants;
import com.epicodus.cookup.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.findRecipesButton) Button mFindRecipesButton;
    @Bind(R.id.aboutButton) Button mAboutButton;
    @Bind(R.id.appNameTextView) TextView mAppNameTextView;
    @Bind(R.id.savedRecipesButton) Button mSavedRecipesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Typeface pacifico = Typeface.createFromAsset(getAssets(), "fonts/pacifico.ttf");
        mAppNameTextView.setTypeface(pacifico);

        mFindRecipesButton.setOnClickListener(this);
        mAboutButton.setOnClickListener(this);
        mSavedRecipesButton.setOnClickListener(this);
    }

    @Override
    public void onClick (View v) {
        if (v == mFindRecipesButton) {
            Intent intent = new Intent(MainActivity.this, RecipeListActivity.class);
            startActivity(intent);
        }

        if (v == mAboutButton) {
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(intent);
        }

        if (v == mSavedRecipesButton) {
            Intent intent = new Intent(MainActivity.this, SavedRecipeListActivity.class);
            startActivity(intent);
        }
    }
}
