package com.epicodus.cookup;


import android.content.Context;
import android.widget.ArrayAdapter;

public class CookUpArrayAdapter extends ArrayAdapter {
    private Context mContext;
    private String[] mRecipes;
    private String[] mChefs;

    public CookUpArrayAdapter(Context mContext, int resource, String[] mRecipes, String[] mChefs) {
        super(mContext, resource);
        this.mContext = mContext;
        this.mRecipes = mRecipes;
        this.mChefs = mChefs;
    }

    @Override
    public Object getItem(int position) {
        String recipe = mRecipes[position];
        String chef = mChefs[position];
        return String.format("%s \nBy: %s", recipe, chef);
    }

    @Override
    public int getCount() {
        return mRecipes.length;
    }
}
