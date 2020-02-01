package com.example.kwadrat.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.kwadrat.HomeActivity;
import com.example.kwadrat.Models.OfferModel;
import com.example.kwadrat.OfferDetailsActivity;
import com.example.kwadrat.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class OfferAdapter extends RecyclerView.Adapter<OfferAdapter.OfferViewHolder> {

    private Context mCtx;
    private List<OfferModel> offerList;
    private OfferAdapter.OnItemClickListener mListener;

    public OfferAdapter(Context mCtx, List<OfferModel> offerList) {
        this.mCtx = mCtx;
        this.offerList = offerList;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OfferAdapter.OnItemClickListener listener) {
        mListener = listener;
    }

    @Override
    public OfferAdapter.OfferViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.offer_layout, null);
        return new OfferAdapter.OfferViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OfferAdapter.OfferViewHolder holder, int position) {
        OfferModel offerModel = offerList.get(position);

        holder.textViewStreet.setText(offerModel.getStreet());
        holder.textViewCityName.setText(offerModel.getCity_name());
        holder.textViewDistrictName.setText(offerModel.getDistrict_name());
        holder.textViewFlatArea.setText(offerModel.getFlat_area());
        holder.textViewRent.setText(offerModel.getRent());
        holder.textViewLogin.setText(offerModel.getLogin());
        holder.textViewId.setText(offerModel.getId());
        Picasso.get().load(offerModel.getThumbnail()).into(holder.imageViewThumbnail);
        holder.textViewLoggedUserEmail.setText(offerModel.getLogged_user_email());

    }

    @Override
    public int getItemCount() {
        return offerList.size();
    }

    class OfferViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewId, textViewCityName, textViewDistrictName, textViewStreet, textViewRent, textViewFlatArea, textViewLogin, textViewLoggedUserEmail;
        public ImageView imageViewThumbnail;

        public OfferViewHolder(View itemView) {
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
                    Intent intent = new Intent(mCtx, OfferDetailsActivity.class);
                    intent.putExtra("id", textViewId.getText().toString());
                    intent.putExtra("user_email", textViewLoggedUserEmail.getText().toString());
                    mCtx.startActivity(intent);
                }
            });
        }
    }
}
