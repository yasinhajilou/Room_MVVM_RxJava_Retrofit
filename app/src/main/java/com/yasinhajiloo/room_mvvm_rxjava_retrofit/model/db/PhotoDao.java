package com.yasinhajiloo.room_mvvm_rxjava_retrofit.model.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.yasinhajiloo.room_mvvm_rxjava_retrofit.model.Photo;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface PhotoDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Single<List<Long>> insertAll(List<Photo> photos);

    @Delete
    Single<Integer> delete(Photo photo);

    @Query("SELECT * FROM image_info")
    Flowable<List<Photo>> getAllImage();

    @Query("SELECT * FROM image_info WHERE id LIKE :id LIMIT 40")
    Flowable<Photo> getSpecificPhoto(String id);

    @Query("DELETE FROM image_info")
    Completable deleteAll();
}
