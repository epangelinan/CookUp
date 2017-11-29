package com.epicodus.cookup.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.cookup.Constants;
import com.epicodus.cookup.R;
import com.epicodus.cookup.models.Recipe;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.epicodus.cookup.BuildConfig.YUMMLY_APP_ID;
import static com.epicodus.cookup.Constants.YUMMLY_APP_KEY;

public class RecipeDetailFragment extends Fragment implements View.OnClickListener{

    private static final int MAX_WIDTH = 400;
    private static final int MAX_HEIGHT = 300;

    @Bind(R.id.recipeImageView) ImageView mRecipeImageLabel;
    @Bind(R.id.recipeNameTextView) TextView mRecipeNameLabel;
    @Bind(R.id.sourceDisplayNameTextView) TextView mSourceDisplayNameLabel;
    @Bind(R.id.ratingTextView) TextView mRatingLabel;
    @Bind(R.id.ingredientsTextView) TextView mIngredientsLabel;
    @Bind(R.id.directionsTextView) TextView mDirectionsLabel;
    @Bind(R.id.saveRecipeButton) TextView mSaveRecipeButton;


    private Recipe mRecipe;
    private ArrayList<Recipe> mRecipes;
    private int mPosition;
    private String mSource;

    public static RecipeDetailFragment newInstance(ArrayList<Recipe> recipes, Integer position, String source) {
        RecipeDetailFragment recipeDetailFragment = new RecipeDetailFragment();
        Bundle args = new Bundle();

        args.putParcelable(Constants.EXTRA_KEY_RECIPES, Parcels.wrap(recipes));
        args.putInt(Constants.EXTRA_KEY_POSITION, position);
        args.putString(Constants.KEY_SOURCE, source);

        recipeDetailFragment.setArguments(args);
        return recipeDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRecipes = Parcels.unwrap(getArguments().getParcelable(Constants.EXTRA_KEY_RECIPES));
        mPosition = getArguments().getInt(Constants.EXTRA_KEY_POSITION);
        mRecipe = mRecipes.get(mPosition);
        mSource = getArguments().getString(Constants.KEY_SOURCE);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_detail, container, false);
        ButterKnife.bind(this, view);

        if (mSource.equals(Constants.SOURCE_SAVED)) {
            mSaveRecipeButton.setVisibility(View.GONE);
        } else {
            mSaveRecipeButton.setOnClickListener(this);
        }

        Picasso.with(view.getContext())
                .load(mRecipe.getImageUrlsBySize())
                .resize(MAX_WIDTH, MAX_HEIGHT)
                .centerCrop()
                .into(mRecipeImageLabel);

        mRecipeNameLabel.setText(mRecipe.getRecipeName());
        mSourceDisplayNameLabel.setText(mRecipe.getSourceDisplayName());
        mIngredientsLabel.setText(android.text.TextUtils.join(", ", mRecipe.getIngredients()));
        mRatingLabel.setText(Double.toString(mRecipe.getRating()) + "/5");
        mDirectionsLabel.setText("Get the Recipe");

        mDirectionsLabel.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == mDirectionsLabel) {

            String recipeId = mRecipe.getId();

            Intent webIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.yummly.com/recipe/" + recipeId ));
            startActivity(webIntent);
        }
        if (v == mSaveRecipeButton) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String uid = user.getUid();
            DatabaseReference recipeRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_RECIPES)
                    .child(uid);

            DatabaseReference pushRef = recipeRef.push();
            String pushId = pushRef.getKey();
            mRecipe.setPushId(pushId);
            pushRef.setValue(mRecipe);

            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
        }
    }
}
