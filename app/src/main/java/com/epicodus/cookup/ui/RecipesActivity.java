package com.epicodus.cookup.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.epicodus.cookup.R;
import com.epicodus.cookup.adapters.RecipeListAdapter;
import com.epicodus.cookup.models.Recipe;
import com.epicodus.cookup.services.YummlyService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class RecipesActivity extends AppCompatActivity {
    public static final String TAG = RecipesActivity.class.getSimpleName();
    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    private RecipeListAdapter mAdapter;

    public ArrayList<Recipe> mRecipes = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String ingredient = intent.getStringExtra("ingredient");
        getRecipes(ingredient);
    }

    private void getRecipes(String ingredient) {
        final YummlyService yummlyService = new YummlyService();

        yummlyService.findRecipes(ingredient, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                mRecipes = yummlyService.processResults(response);

                RecipesActivity.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        mAdapter = new RecipeListAdapter(getApplicationContext(), mRecipes);

                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager =
                                new LinearLayoutManager(RecipesActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);
                    }
                });
            }
        });
    }
}
