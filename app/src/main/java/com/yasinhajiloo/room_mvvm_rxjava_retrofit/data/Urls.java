package com.yasinhajiloo.room_mvvm_rxjava_retrofit.data;

import com.google.gson.annotations.SerializedName;

public class Urls {
    @SerializedName("raw")
    private String raw;

    @SerializedName("full")
    private String full;

    @SerializedName("small")
    private String small;

    @SerializedName("thumb")
    private String thumb;

    public Urls(String raw, String full, String small, String thumb) {
        this.raw = raw;
        this.full = full;
        this.small = small;
        this.thumb = thumb;
    }

    public String getRaw() {
        return raw;
    }

    public String getFull() {
        return full;
    }

    public String getSmall() {
        return small;
    }

    public String getThumb() {
        return thumb;
    }
}
