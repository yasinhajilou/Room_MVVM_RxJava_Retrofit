package com.yasinhajiloo.room_mvvm_rxjava_retrofit.data;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "image_info")
public class Photo {

    @PrimaryKey
    @NonNull
    @SerializedName("id")
    private String id;

    @SerializedName("description")
    private String description;

    @SerializedName("alt_description")
    private String alt_description;

    @SerializedName("urls")
    @Embedded
    private Urls urls;

    public Photo(@NonNull String id, String description, String alt_description, Urls urls) {
        this.id = id;
        this.description = description;
        this.alt_description = alt_description;
        this.urls = urls;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getAlt_description() {
        return alt_description;
    }

    public Urls getUrls() {
        return urls;
    }
}
