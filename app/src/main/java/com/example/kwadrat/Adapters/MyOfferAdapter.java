package com.example.kwadrat.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.kwadrat.Models.MyOfferModel;
import com.example.kwadrat.MyOfferDetailsActivity;
import com.example.kwadrat.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyOfferAdapter extends RecyclerView.Adapter<MyOfferAdapter.MyOfferViewHolder> {

    private Context mCtx;
    private List<MyOfferModel> myOfferList;
    private OnItemClickListener mListener;

    public MyOfferAdapter(Context mCtx, List<MyOfferModel> myOfferList) {
        this.mCtx = mCtx;
        this.myOfferList = myOfferList;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    @Override
    public MyOfferViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.my_offer_layout, null);
        return new MyOfferViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyOfferViewHolder holder, int position) {
        MyOfferModel myOfferModel = myOfferList.get(position);

        holder.textViewCityName.setText(myOfferModel.getCity_name());
        holder.textViewStreet.setText(myOfferModel.getStreet());
        holder.textViewFlatArea.setText(myOfferModel.getFlat_area());
        holder.textViewRent.setText(myOfferModel.getRent());
        holder.textViewVisibility.setText(myOfferModel.getVisibility());
        if((holder.textViewVisibility.getText()).equals("aktywne")) {
            holder.textViewVisibility.setTextColor(Color.GREEN);
        } else holder.textViewVisibility.setTextColor(Color.RED);
        holder.textViewId.setText(myOfferModel.getId());
        holder.textViewDistrictName.setText(myOfferModel.getDistrict_name());
        Picasso.get().load(myOfferModel.getThumbnail()).into(holder.imageViewThumbnail);

    }

    @Override
    public int getItemCount() {
        return myOfferList.size();
    }

    class MyOfferViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewId, textViewCityName, textViewDistrictName, textViewStreet, textViewRent, textViewFlatArea, textViewVisibility;
        public ImageView imageViewThumbnail;

        public MyOfferViewHolder(View itemView) {
            super(itemView);

            textViewId = itemView.findViewById(R.id.offer_id);
            textViewCityName = itemView.findViewById(R.id.offer_city_name);
            textViewDistrictName = itemView.findViewById(R.id.offer_district_name);
            textViewStreet = itemView.findViewById(R.id.offer_street);
            textViewFlatArea = itemView.findViewById(R.id.offer_flat_area);
            textViewRent = itemView.findViewById(R.id.offer_rent);
            textViewVisibility = itemView.findViewById(R.id.activity);
            imageViewThumbnail = itemView.findViewById(R.id.thumbnail);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mCtx, MyOfferDetailsActivity.class);
                    intent.putExtra("id", textViewId.getText().toString());
                    mCtx.startActivity(intent);
                }
            });
        }
    }
}
