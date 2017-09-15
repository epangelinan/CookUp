package com.epicodus.cookup.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.cookup.R;
import com.epicodus.cookup.models.Recipe;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RecipeDetailFragment extends Fragment {
    @Bind(R.id.recipeImageView) ImageView mRecipeImageLabel;
    @Bind(R.id.recipeNameTextView) TextView mRecipeNameLabel;
    @Bind(R.id.sourceDisplayNameTextView) TextView mSourceDisplayNameLabel;
    @Bind(R.id.ratingTextView) TextView mRatingLabel;
    @Bind(R.id.ingredientsTextView) TextView mIngredientsLabel;
    @Bind(R.id.directionsTextView) TextView mDirectionsLabel;

    private Recipe mRecipe;

    public static RecipeDetailFragment newInstance(Recipe recipe) {
        RecipeDetailFragment recipeDetailFragment = new RecipeDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("recipe", Parcels.wrap(recipe));
        recipeDetailFragment.setArguments(args);
        return recipeDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRecipe = Parcels.unwrap(getArguments().getParcelable("recipe"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_detail, container, false);
        ButterKnife.bind(this, view);

        Picasso.with(view.getContext()).load(mRecipe.getImageUrlsBySize()).into(mRecipeImageLabel);

        mRecipeNameLabel.setText(mRecipe.getRecipeName());
        mSourceDisplayNameLabel.setText(mRecipe.getSourceDisplayName());
        mIngredientsLabel.setText(android.text.TextUtils.join(", ", mRecipe.getIngredients()));
        mRatingLabel.setText(Double.toString(mRecipe.getRating()) + "/5");
        mDirectionsLabel.setText("Read Directions");
        return view;
    }

}
