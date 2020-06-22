package com.yasinhajiloo.room_mvvm_rxjava_retrofit.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.yasinhajiloo.room_mvvm_rxjava_retrofit.data.Photo;
import com.yasinhajiloo.room_mvvm_rxjava_retrofit.data.repository.PhotoRepository;

import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.Flowable;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class PhotoViewModel extends AndroidViewModel {

    private PhotoRepository mPhotoRepository;
    private MutableLiveData<Boolean> mNetworkResult = new MutableLiveData<>();
    private MutableLiveData<Boolean> mDeleteDataResult = new MutableLiveData<>();

    public PhotoViewModel(Application application) {
        super(application);
        mPhotoRepository = new PhotoRepository(application);
    }

    public void getNewPhotos() {
        Log.d("TRACKER", "ViewModel getNewPhotos: ");
        mPhotoRepository.getNetworkPhotos().subscribe(new SingleObserver<List<Long>>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d("TRACKER", "ViewModel getNewPhotos onSubscribe: ");
            }

            @Override
            public void onSuccess(List<Long> value) {
                mNetworkResult.postValue(true);
                Log.d("TRACKER", "ViewModel getNewPhotos onSuccess: " + value.size());
            }

            @Override
            public void onError(Throwable e) {
                Log.d("TRACKER", "ViewModel getNewPhotos onError: " + e.getMessage());
                mNetworkResult.postValue(false);
            }
        });
    }

    public LiveData<List<Photo>> getAllPhotos() {
        Flowable<List<Photo>> listFlowable = mPhotoRepository.getAllPhotos();
        return LiveDataReactiveStreams.fromPublisher(listFlowable);
    }

    public LiveData<Photo> getSpecificPhoto(String id) {
        Flowable<Photo> photoFlowable = mPhotoRepository.getSpecificPhoto(id);
        return LiveDataReactiveStreams.fromPublisher(photoFlowable);
    }

    public LiveData<Boolean> getNetworkResult() {
        return mNetworkResult;
    }

    public void deleteAll() {
        mPhotoRepository.deleteAll().subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onComplete() {
                mDeleteDataResult.postValue(true);
            }

            @Override
            public void onError(Throwable e) {
                mDeleteDataResult.postValue(false);
            }
        });
    }

    public LiveData<Boolean> getDeleteAllResult() {
        return mDeleteDataResult;
    }

    @Override

    protected void onCleared() {
        super.onCleared();
    }


    public static class Factory extends ViewModelProvider.NewInstanceFactory {
        @NonNull
        private Application mApplication;

        public Factory(@NonNull Application application) {
            mApplication = application;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new PhotoViewModel(mApplication);
        }
    }
}

