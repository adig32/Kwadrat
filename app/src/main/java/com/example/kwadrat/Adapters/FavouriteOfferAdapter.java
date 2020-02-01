package com.example.kwadrat.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.kwadrat.FavouriteOfferDetailsActivity;
import com.example.kwadrat.HomeActivity;
import com.example.kwadrat.Models.FavouriteOfferModel;
import com.example.kwadrat.Models.OfferModel;
import com.example.kwadrat.OfferDetailsActivity;
import com.example.kwadrat.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FavouriteOfferAdapter extends RecyclerView.Adapter<FavouriteOfferAdapter.FavouriteOfferViewHolder> {

    private Context mCtx;
    private List<FavouriteOfferModel> favouriteOfferList;
    private FavouriteOfferAdapter.OnItemClickListener mListener;

    public FavouriteOfferAdapter(Context mCtx, List<FavouriteOfferModel> favouriteOfferList) {
        this.mCtx = mCtx;
        this.favouriteOfferList = favouriteOfferList;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(FavouriteOfferAdapter.OnItemClickListener listener) {
        mListener = listener;
    }

    @Override
    public FavouriteOfferAdapter.FavouriteOfferViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.offer_layout, null);
        return new FavouriteOfferAdapter.FavouriteOfferViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FavouriteOfferAdapter.FavouriteOfferViewHolder holder, int position) {
        FavouriteOfferModel favouriteOfferModel = favouriteOfferList.get(position);

        holder.textViewStreet.setText(favouriteOfferModel.getStreet());
        holder.textViewCityName.setText(favouriteOfferModel.getCity_name());
        holder.textViewDistrictName.setText(favouriteOfferModel.getDistrict_name());
        holder.textViewFlatArea.setText(favouriteOfferModel.getFlat_area());
        holder.textViewRent.setText(favouriteOfferModel.getRent());
        holder.textViewLogin.setText(favouriteOfferModel.getLogin());
        holder.textViewId.setText(favouriteOfferModel.getId());
        Picasso.get().load(favouriteOfferModel.getThumbnail()).into(holder.imageViewThumbnail);
        holder.textViewLoggedUserEmail.setText(favouriteOfferModel.getLogged_user_email());

    }

    @Override
    public int getItemCount() {
        return favouriteOfferList.size();
    }

    class FavouriteOfferViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewId, textViewCityName, textViewDistrictName, textViewStreet, textViewRent, textViewFlatArea, textViewLogin, textViewLoggedUserEmail;
        public ImageView imageViewThumbnail;

        public FavouriteOfferViewHolder(View itemView) {
            super(itemView);

            textViewId = itemView.findViewById(R.id.offer_id);
            textViewCityName = itemView.findViewById(R.id.offer_city_name);
            textViewDistrictName = itemView.findViewById(R.id.offer_district_name);
            textViewStreet = itemView.findViewById(R.id.offer_street);
            textViewFlatArea = itemView.findViewById(R.id.offer_flat_area);
            textViewRent = itemView.findViewById(R.id.offer_rent);
            textViewLogin = itemView.findViewById(R.id.offer_login);
            imageViewThumbnail = itemView.findViewById(R.id.thumbnail);
            textViewLoggedUserEmail = itemView.findViewById(R.id.logged_user_id);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mCtx, FavouriteOfferDetailsActivity.class);
                    intent.putExtra("id", textViewId.getText().toString());
                    intent.putExtra("user_email", textViewLoggedUserEmail.getText().toString());
                    mCtx.startActivity(intent);
                }
            });
        }
    }
}
