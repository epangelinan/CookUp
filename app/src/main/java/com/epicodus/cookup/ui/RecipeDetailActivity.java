package com.epicodus.cookup.ui;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.epicodus.cookup.Constants;
import com.epicodus.cookup.R;
import com.epicodus.cookup.adapters.RecipePagerAdapter;
import com.epicodus.cookup.models.Recipe;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RecipeDetailActivity extends AppCompatActivity {
    private String mSource;
    @Bind(R.id.viewPager) ViewPager mViewPager;
    private RecipePagerAdapter adapterViewPager;
    ArrayList<Recipe> mRecipes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);
        ButterKnife.bind(this);

        mRecipes = Parcels.unwrap(getIntent().getParcelableExtra(Constants.EXTRA_KEY_RECIPES));
        int startingPosition = getIntent().getIntExtra(Constants.EXTRA_KEY_POSITION, 0);
        mSource = getIntent().getStringExtra(Constants.KEY_SOURCE);

        adapterViewPager = new RecipePagerAdapter(getSupportFragmentManager(), mRecipes, mSource);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);

    }
}
