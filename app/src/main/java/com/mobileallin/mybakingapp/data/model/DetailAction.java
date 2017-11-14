package com.mobileallin.mybakingapp.data.model;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Dawid on 2017-11-13.
 */

@AutoValue
public abstract class DetailAction implements Parcelable {

    @SerializedName("id")
    public abstract int id();

    @SerializedName("shortDescription")
    public abstract String shortDescription();

    @SerializedName("description")
    public abstract String description();

    @SerializedName("videoURL")
    public abstract String videoURL();

    @SerializedName("thumbnailURL")
    public abstract String thumbnailURL();

    public static TypeAdapter<DetailAction> typeAdapter(Gson gson) {
        return new AutoValue_DetailAction.GsonTypeAdapter(gson);
    }

    public static Builder builder() {
        return new AutoValue_DetailAction.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder setId(int id);
        public abstract Builder setShortDescription(String description);
        public abstract Builder setDescription(String description);
        public abstract Builder setVideoURL(String videoUrl);
        public abstract Builder setThumbnailURL(String thumbnailUrl);
        public abstract DetailAction build();
    }

}
