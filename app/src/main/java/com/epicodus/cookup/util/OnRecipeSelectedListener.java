package com.epicodus.cookup.util;


import com.epicodus.cookup.models.Recipe;

import java.util.ArrayList;


public interface OnRecipeSelectedListener {
    public void onRecipeSelected(Integer position, ArrayList<Recipe> recipes, String source);
}
