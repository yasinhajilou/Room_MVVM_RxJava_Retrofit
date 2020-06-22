package com.yasinhajiloo.room_mvvm_rxjava_retrofit.network;

import com.yasinhajiloo.room_mvvm_rxjava_retrofit.model.Photo;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NetworkApi {
    //TODO : PLEASE CREATE UNSPALSH ACCOUNT AND FILL BELOW FIELD BY GIVEN ACCESS KEY
    String accessKey = "";

    @GET("photos")
    Single<List<Photo>> getPhotoList(@Query("client_id") String accessKey);
}
