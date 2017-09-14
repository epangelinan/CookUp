package com.epicodus.cookup;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class YummlyService {
    public static void findRecipes(String ingredient, Callback callback) {
        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.YUMMLY_BASE_URL).newBuilder();

        urlBuilder.addQueryParameter("_app_id", Constants.YUMMLY_APP_ID);
        urlBuilder.addQueryParameter("_app_key", Constants.YUMMLY_APP_KEY);
        urlBuilder.addQueryParameter(Constants.YUMMLY_INGREDIENT_QUERY_PARAMETER, ingredient);

        String url = urlBuilder.build().toString();

        Log.v("The URL is: ", url);

        Request request= new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }


    public ArrayList<Recipe> processResults(Response response) {
        ArrayList<Recipe> recipes = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            if (response.isSuccessful()) {
                JSONObject yummlyJSON = new JSONObject(jsonData);
                JSONArray matchesJSON = yummlyJSON.getJSONArray("matches");
                for (int i = 0; i < matchesJSON.length(); i++) {
                    JSONObject recipeJSON = matchesJSON.getJSONObject(i);
                    String imageUrlsBySize = recipeJSON.getJSONObject("imageUrlsBySize")
                            .getJSONObject("90").toString();
                 //   Log.d(TAG, imageUrlsBySize);

                    String sourceDisplayName = recipeJSON.getString("sourceDisplayName");
                    String id = recipeJSON.getString("id");
                    String recipeName = recipeJSON.getString("recipeName");
                    int totalTimeInSeconds = recipeJSON.getInt("totalTimeInSeconds");
                    double rating = recipeJSON.getDouble("rating");

                    ArrayList<String> ingredients = new ArrayList<>();
                    JSONArray ingredientsJSON = recipeJSON.getJSONArray("ingredients");

                    for (int y = 0; y < ingredientsJSON.length(); y++) {
                        ingredients.add(ingredientsJSON.getJSONArray(y).get(0).toString());
                    }
                    Recipe recipe = new Recipe (imageUrlsBySize, sourceDisplayName, ingredients, id, recipeName, totalTimeInSeconds, rating);
                    recipes.add(recipe);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return recipes;
    }
}
