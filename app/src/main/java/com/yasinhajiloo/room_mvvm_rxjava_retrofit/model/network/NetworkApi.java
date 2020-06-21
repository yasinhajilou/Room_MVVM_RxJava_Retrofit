package com.yasinhajiloo.room_mvvm_rxjava_retrofit.model.network;

import com.yasinhajiloo.room_mvvm_rxjava_retrofit.model.Photo;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NetworkApi {
    String accessKey = "8fI6zwZjbaxcPULpJcKV5ZxAawpaX96B2ysTFhWoyjE";

    @GET("photos")
    Single<List<Photo>> getPhotoList(@Query("client_id") String accessKey);
}
