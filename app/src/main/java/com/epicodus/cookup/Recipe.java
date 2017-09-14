package com.epicodus.cookup;


import java.util.ArrayList;

public class Recipe {
    private String mImageUrl;
    private String mSourceDisplayName;
    private ArrayList<String> mIngredients = new ArrayList<>();
    private String mId;
    private String mRecipeName;
    private int mTotalTimeInSeconds;
    private double mRating;

    public Recipe (String imageUrl, String sourceDisplayName, ArrayList<String> ingredients, String id, String recipeName, int totalTimeInSeconds, double rating) {
        this.mImageUrl = imageUrl;
        this.mSourceDisplayName = sourceDisplayName;
        this.mIngredients = ingredients;
        this.mId = id;
        this.mRecipeName = recipeName;
        this.mTotalTimeInSeconds = totalTimeInSeconds;
        this.mRating = rating;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public String getSourceDisplayName() {
        return mSourceDisplayName;
    }

    public ArrayList<String> getIngredients() {
        return mIngredients;
    }

    public String getId() {
        return mId;
    }

    public String getRecipeName() {
        return mRecipeName;
    }

    public int getTotalTimeInSeconds() {
        return mTotalTimeInSeconds;
    }

    public double getRating() {
        return mRating;
    }
}
