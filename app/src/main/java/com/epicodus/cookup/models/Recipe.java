package com.epicodus.cookup.models;


import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Recipe {
    private String imageUrlsBySize;
    private String sourceDisplayName;
    private List<String> ingredients = new ArrayList<>();
    private String id;
    private String recipeName;
    private int totalTimeInSeconds;
    private double rating;

    public Recipe() {}

    public Recipe (String imageUrlsBySize, String sourceDisplayName, ArrayList<String> ingredients, String id, String recipeName, int totalTimeInSeconds, double rating) {
        this.imageUrlsBySize = imageUrlsBySize;
        this.sourceDisplayName = sourceDisplayName;
        this.ingredients = ingredients;
        this.id = id;
        this.recipeName = recipeName;
        this.totalTimeInSeconds = totalTimeInSeconds;
        this.rating = rating;
    }

    public String getImageUrlsBySize() {
        return imageUrlsBySize;
    }

    public String getSourceDisplayName() {
        return sourceDisplayName;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public String getId() {
        return id;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public int getTotalTimeInSeconds() {

        return totalTimeInSeconds;
    }

    public double getRating() {
        return rating;
    }

}
