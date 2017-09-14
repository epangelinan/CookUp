package com.epicodus.cookup;

import android.util.Log;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

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
}
