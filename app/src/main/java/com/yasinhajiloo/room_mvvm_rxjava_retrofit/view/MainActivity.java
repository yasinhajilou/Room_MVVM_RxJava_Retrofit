package com.yasinhajiloo.room_mvvm_rxjava_retrofit.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.yasinhajiloo.room_mvvm_rxjava_retrofit.R;
import com.yasinhajiloo.room_mvvm_rxjava_retrofit.databinding.ActivityMainBinding;
import com.yasinhajiloo.room_mvvm_rxjava_retrofit.viewmodel.PhotoViewModel;


public class MainActivity extends AppCompatActivity {

    private PhotoViewModel mPhotoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        com.yasinhajiloo.room_mvvm_rxjava_retrofit.databinding.ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        PhotoViewModel.Factory factory = new PhotoViewModel.Factory(getApplication());
        mPhotoViewModel = new ViewModelProvider(this, factory).get(PhotoViewModel.class);

        final AdapterPhotos adapterPhotos = new AdapterPhotos();

        binding.rvMain.setLayoutManager(new LinearLayoutManager(this));
        binding.rvMain.setAdapter(adapterPhotos);

        //req for loading new photo
        Toast.makeText(this, "Getting new photos...", Toast.LENGTH_SHORT).show();
        mPhotoViewModel.getNewPhotos();


        //observing for new photos
        mPhotoViewModel.getAllPhotos().observe(this, adapterPhotos::setData);

        //observing for network connection results
        mPhotoViewModel.getNetworkResult().observe(this, aBoolean -> {
            if (aBoolean)
                Toast.makeText(this, "Photos Updated", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "Please check network connection", Toast.LENGTH_SHORT).show();
            binding.progressBar.setVisibility(View.GONE);
        });

        //observing for deleting items result
        mPhotoViewModel.getDeleteAllResult().observe(this, aBoolean -> {
            if (aBoolean)
                Toast.makeText(MainActivity.this, "Deleted Successfully", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(MainActivity.this, "An error occurred", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_delete) {
            mPhotoViewModel.deleteAll();
        }
        return super.onOptionsItemSelected(item);
    }
}