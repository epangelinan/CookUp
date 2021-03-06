package com.epicodus.cookup.adapters;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.epicodus.cookup.Constants;
import com.epicodus.cookup.R;
import com.epicodus.cookup.models.Recipe;
import com.epicodus.cookup.ui.RecipeDetailActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import org.parceler.Parcels;
import java.util.ArrayList;

public class FirebaseRecipeViewHolder extends RecyclerView.ViewHolder {
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;

    View mView;
    Context mContext;
    public ImageView mRecipeImageView;

    public FirebaseRecipeViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
    }

    public void bindRecipe(Recipe recipe) {
        mRecipeImageView = (ImageView) mView.findViewById(R.id.recipeImageView);
        TextView recipeNameTextView = (TextView) mView.findViewById(R.id.recipeNameTextView);
        TextView ratingTextView = (TextView) mView.findViewById(R.id.ratingTextView);
        TextView sourceDisplayNameTextView = (TextView) mView.findViewById(R.id.sourceDisplayNameTextView);

        Picasso.with(mContext)
                .load(recipe.getImageUrlsBySize())
                .resize(MAX_WIDTH, MAX_HEIGHT)
                .centerCrop()
                .into(mRecipeImageView);

        recipeNameTextView.setText(recipe.getRecipeName());
        ratingTextView.setText("Rating: " + recipe.getRating() + "/5");
        sourceDisplayNameTextView.setText(recipe.getSourceDisplayName());
    }
}
