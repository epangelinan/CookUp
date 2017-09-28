package com.epicodus.cookup.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.preference.PreferenceManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.epicodus.cookup.Constants;
import com.epicodus.cookup.R;
import com.epicodus.cookup.adapters.RecipeListAdapter;
import com.epicodus.cookup.models.Recipe;
import com.epicodus.cookup.services.YummlyService;
import com.epicodus.cookup.util.OnRecipeSelectedListener;

import org.parceler.Parcels;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class RecipeListActivity extends AppCompatActivity implements OnRecipeSelectedListener {
    private Integer mPosition;
    ArrayList<Recipe> mRecipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

        if (savedInstanceState != null) {

            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                mPosition = savedInstanceState.getInt(Constants.EXTRA_KEY_POSITION);
                mRecipes = Parcels.unwrap(savedInstanceState.getParcelable(Constants.EXTRA_KEY_RECIPES));

                if (mPosition != null && mRecipes != null) {
                    Intent intent = new Intent(this, RecipeDetailActivity.class);
                    intent.putExtra(Constants.EXTRA_KEY_POSITION, mPosition);
                    intent.putExtra(Constants.EXTRA_KEY_RECIPES, Parcels.wrap(mRecipes));
                    startActivity(intent);
                }

            }

        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if (mPosition != null && mRecipes != null) {
            outState.putInt(Constants.EXTRA_KEY_POSITION, mPosition);
            outState.putParcelable(Constants.EXTRA_KEY_RECIPES, Parcels.wrap(mRecipes));
        }

    }

    @Override
    public void onRecipeSelected(Integer position, ArrayList<Recipe> recipes) {
        mPosition = position;
        mRecipes = recipes;
    }
}
