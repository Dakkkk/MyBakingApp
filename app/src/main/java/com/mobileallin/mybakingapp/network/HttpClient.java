package com.mobileallin.mybakingapp.network;

import com.mobileallin.mybakingapp.data.model.Recipe;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by Dawid on 2017-10-10.
 */

public interface HttpClient {

    String ENDPOINT = "http://go.udacity.com/";

    @GET("android-baking-app-json")
    Observable<List<Recipe>> getRecipes();
}
