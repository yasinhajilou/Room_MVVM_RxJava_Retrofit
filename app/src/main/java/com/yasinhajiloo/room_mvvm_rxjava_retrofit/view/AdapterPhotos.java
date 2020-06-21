package com.yasinhajiloo.room_mvvm_rxjava_retrofit.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.yasinhajiloo.room_mvvm_rxjava_retrofit.R;
import com.yasinhajiloo.room_mvvm_rxjava_retrofit.model.Photo;

import java.util.List;

public class AdapterPhotos extends RecyclerView.Adapter<AdapterPhotos.ViewHolder> {

    private List<Photo> mPhotos;

    public void setData(List<Photo> photoList) {
        mPhotos = photoList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_photo, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindData(mPhotos.get(position));
    }

    @Override
    public int getItemCount() {
        return mPhotos == null ? 0 : mPhotos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;
        TextView mTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.iv_item);
            mTextView = itemView.findViewById(R.id.tv_item);
        }

        void bindData(Photo photo) {
            mTextView.setText(photo.getAlt_description());
            Glide.with(mImageView.getContext()).load(photo.getUrls().getSmall()).into(mImageView);
        }
    }
}
