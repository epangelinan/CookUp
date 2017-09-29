package com.epicodus.cookup.adapters;


import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.cookup.Constants;
import com.epicodus.cookup.R;
import com.epicodus.cookup.models.Recipe;
import com.epicodus.cookup.ui.RecipeDetailActivity;
import com.epicodus.cookup.ui.RecipeDetailFragment;
import com.epicodus.cookup.util.OnRecipeSelectedListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.RecipeViewHolder> {
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;

    private ArrayList<Recipe> mRecipes = new ArrayList<>();
    private Context mContext;
    private OnRecipeSelectedListener mOnRecipeSelectedListener;


    public RecipeListAdapter(Context context, ArrayList<Recipe> recipes, OnRecipeSelectedListener recipeSelectedListener) {
        mContext = context;
        mRecipes = recipes;
        mOnRecipeSelectedListener = recipeSelectedListener;
    }

    @Override
    public RecipeListAdapter.RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_list_item, parent, false);
        RecipeViewHolder viewHolder = new RecipeViewHolder(view, mRecipes, mOnRecipeSelectedListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecipeListAdapter.RecipeViewHolder holder, int position) {
        holder.bindRecipe(mRecipes.get(position));
    }

    @Override
    public int getItemCount() {
        return mRecipes.size();
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.recipeImageView) ImageView mRecipeImageView;
        @Bind(R.id.recipeNameTextView) TextView mRecipeNameTextView;
        @Bind(R.id.sourceDisplayNameTextView) TextView mSourceDisplayNameTextView;
        @Bind(R.id.ratingTextView) TextView mRatingTextView;

        private Context mContext;
        private int mOrientation;
        private ArrayList<Recipe> mRecipes = new ArrayList<>();
        private OnRecipeSelectedListener mRecipeSelectedListener;

        public RecipeViewHolder(View itemView, ArrayList<Recipe> recipes, OnRecipeSelectedListener recipeSelectedListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            mContext = itemView.getContext();

            // Determines the current orientation of the device:
            mOrientation = itemView.getResources().getConfiguration().orientation;
            mRecipes = recipes;
            mRecipeSelectedListener = recipeSelectedListener;

            // Checks if the recorded orientation matches Android's landscape configuration.
            // if so, we create a new DetailFragment to display in our special landscape layout:
            if (mOrientation == Configuration.ORIENTATION_LANDSCAPE) {
                createDetailFragment(0);
            }

            itemView.setOnClickListener(this);
        }

        public void bindRecipe(Recipe recipe) {

            Picasso.with(mContext)
                    .load(recipe.getImageUrlsBySize())
                    .resize(MAX_WIDTH, MAX_HEIGHT)
                    .centerCrop()
                    .into(mRecipeImageView);

            mRecipeNameTextView.setText(recipe.getRecipeName());
            mSourceDisplayNameTextView.setText(recipe.getSourceDisplayName());
            mRatingTextView.setText("Rating: " + recipe.getRating() + "/5");
        }

            // Takes position of restaurant in list as parameter:
        private void createDetailFragment(int position) {
            // Creates new RecipeDetailFragment with the given position:
            RecipeDetailFragment detailFragment = RecipeDetailFragment.newInstance(mRecipes, position, Constants.SOURCE_FIND);
            // Gathers necessary components to replace the FrameLayout in the layout with the RecipeDetailFragment:
            FragmentTransaction ft = ((FragmentActivity) mContext).getSupportFragmentManager().beginTransaction();
            //  Replaces the FrameLayout with the RecipeDetailFragment:
            ft.replace(R.id.recipeDetailContainer, detailFragment);
            // Commits these changes:
            ft.commit();
        }

        @Override
        public void onClick(View v) {
            // Determines the position of the recipe clicked:
            int itemPosition = getLayoutPosition();
            mRecipeSelectedListener.onRecipeSelected(itemPosition, mRecipes, Constants.SOURCE_FIND);
            if (mOrientation == Configuration.ORIENTATION_LANDSCAPE) {
                createDetailFragment(itemPosition);
            } else {
                Intent intent = new Intent(mContext, RecipeDetailActivity.class);
                intent.putExtra(Constants.EXTRA_KEY_POSITION, itemPosition);
                intent.putExtra(Constants.EXTRA_KEY_RECIPES, Parcels.wrap(mRecipes));
                intent.putExtra(Constants.KEY_SOURCE, Constants.SOURCE_FIND);
                mContext.startActivity(intent);
            }
        }
    }
}