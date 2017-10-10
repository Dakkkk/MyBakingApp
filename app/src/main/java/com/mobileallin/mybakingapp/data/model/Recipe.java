package com.mobileallin.mybakingapp.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Dawid on 2017-10-10.
 */

public class Recipe {

    public long id;

    @SerializedName("name")
    public String name;

    @SerializedName("servings")
    public int servings;

    @SerializedName("image")
    public String imageUrl;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
