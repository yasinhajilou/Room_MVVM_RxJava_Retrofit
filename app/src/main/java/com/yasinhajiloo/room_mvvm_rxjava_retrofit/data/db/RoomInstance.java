package com.yasinhajiloo.room_mvvm_rxjava_retrofit.data.db;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.yasinhajiloo.room_mvvm_rxjava_retrofit.data.Photo;

@Database(entities = {Photo.class}, version = 1, exportSchema = true)
public abstract class RoomInstance extends RoomDatabase {
    private static RoomInstance sInstance;

    public abstract PhotoDao mPhotoDao();

    public static RoomInstance getInstance(Application application) {
        if (sInstance == null) {
            synchronized (RoomInstance.class) {
                if (sInstance == null) {
                    sInstance = Room.databaseBuilder(application.getApplicationContext(), RoomInstance.class, "MY_DB")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }

        return sInstance;
    }
}
