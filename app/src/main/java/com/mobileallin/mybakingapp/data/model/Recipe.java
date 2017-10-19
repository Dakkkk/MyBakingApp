package com.mobileallin.mybakingapp.data.model;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Dawid on 2017-10-10.
 */
@AutoValue
public abstract class Recipe implements Parcelable {

    public abstract long id();

    @SerializedName("name")
    public abstract String name();

    @SerializedName("servings")
    public abstract int servings();

    @SerializedName("image")
    public abstract String imageUrl();

    public static TypeAdapter<Recipe> typeAdapter(Gson gson){
        return new AutoValue_Recipe.GsonTypeAdapter(gson);
    }

    public static Builder builder() {
        return new AutoValue_Recipe.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder setId(long id);
        public abstract Builder setName(String value);
        public abstract Builder setServings(int servings);
        public abstract Builder setImageUrl(String imageUrl);
        public abstract Recipe build();
    }

}
