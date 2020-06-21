package com.yasinhajiloo.room_mvvm_rxjava_retrofit.model.repository;

import android.app.Application;
import android.util.Log;

import com.yasinhajiloo.room_mvvm_rxjava_retrofit.model.Photo;
import com.yasinhajiloo.room_mvvm_rxjava_retrofit.model.db.PhotoDao;
import com.yasinhajiloo.room_mvvm_rxjava_retrofit.model.db.RoomInstance;
import com.yasinhajiloo.room_mvvm_rxjava_retrofit.model.network.NetworkApi;
import com.yasinhajiloo.room_mvvm_rxjava_retrofit.model.network.RetrofitInstance;


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
                .flatMap(new Function<List<Photo>, Single<List<Long>>>() {
                    @Override
                    public Single<List<Long>> apply(List<Photo> photos) throws Exception {
                        return insertAll(photos);
                    }
                });
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
