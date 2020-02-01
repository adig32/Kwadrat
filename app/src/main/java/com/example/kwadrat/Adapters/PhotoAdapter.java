package com.example.kwadrat.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.kwadrat.Models.PhotoModel;
import com.example.kwadrat.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder> {

    private Context mCtx;
    private List<PhotoModel> photoList;

    public PhotoAdapter(Context mCtx, List<PhotoModel> photoList) {
        this.mCtx = mCtx;
        this.photoList = photoList;
    }

    @Override
    public PhotoAdapter.PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.one_photo, parent, false);
        return new PhotoAdapter.PhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PhotoAdapter.PhotoViewHolder holder, int position) {
        PhotoModel photoModel = photoList.get(position);

        Picasso.get().load(photoModel.getPath()).into(holder.imageViewPhoto);

    }

    @Override
    public int getItemCount() {
        return photoList.size();
    }

    class PhotoViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageViewPhoto;

        public PhotoViewHolder(View itemView) {
            super(itemView);

            imageViewPhoto = itemView.findViewById(R.id.photo);
        }
    }
}

