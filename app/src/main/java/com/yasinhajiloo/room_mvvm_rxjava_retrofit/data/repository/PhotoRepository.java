package com.yasinhajiloo.room_mvvm_rxjava_retrofit.data.repository;

import android.app.Application;
import android.util.Log;

import com.yasinhajiloo.room_mvvm_rxjava_retrofit.data.Photo;
import com.yasinhajiloo.room_mvvm_rxjava_retrofit.data.db.PhotoDao;
import com.yasinhajiloo.room_mvvm_rxjava_retrofit.data.db.RoomInstance;
import com.yasinhajiloo.room_mvvm_rxjava_retrofit.data.network.NetworkApi;
import com.yasinhajiloo.room_mvvm_rxjava_retrofit.data.network.RetrofitInstance;


import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class PhotoRepository {
    private PhotoDao mPhotoDao;
    private NetworkApi mNetworkApi;

    public PhotoRepository(Application application) {
        RoomInstance database = RoomInstance.getInstance(application);
        mPhotoDao = database.mPhotoDao();
        mNetworkApi = RetrofitInstance.getInstance().create(NetworkApi.class);
    }

    public Single<List<Long>> getNetworkPhotos() {
        Log.d("TRACKER", "Repository getNetworkPhotos: ");
        return mNetworkApi.getPhotoList(NetworkApi.accessKey)
                .subscribeOn(Schedulers.io())
                .flatMap((Function<List<Photo>, Single<List<Long>>>) this::insertAll);
    }

    public Single<List<Long>> insertAll(List<Photo> photos) {
        return mPhotoDao.insertAll(photos)
                .subscribeOn(Schedulers.io());
    }

    public Flowable<List<Photo>> getAllPhotos() {
        return mPhotoDao.getAllImage()
                .subscribeOn(Schedulers.io());
    }

    public Flowable<Photo> getSpecificPhoto(String id) {
        return mPhotoDao.getSpecificPhoto(id)
                .subscribeOn(Schedulers.io());
    }


    public Completable deleteAll() {
        return mPhotoDao.deleteAll()
                .subscribeOn(Schedulers.io());
    }
}
