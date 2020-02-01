package com.example.kwadrat.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.kwadrat.Models.MyOfferModel;
import com.example.kwadrat.Models.OfferToReviewModel;
import com.example.kwadrat.MyOfferDetailsActivity;
import com.example.kwadrat.R;
import com.example.kwadrat.SendReviewActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class OfferToReviewAdapter extends RecyclerView.Adapter<OfferToReviewAdapter.OfferToReviewViewHolder> {

    private Context mCtx;
    private List<OfferToReviewModel> offerToReviewList;
    private OnItemClickListener mListener;

    public OfferToReviewAdapter(Context mCtx, List<OfferToReviewModel> offerToReviewList) {
        this.mCtx = mCtx;
        this.offerToReviewList = offerToReviewList;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    @Override
    public OfferToReviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.offer_to_review_layout, null);
        return new OfferToReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OfferToReviewViewHolder holder, int position) {
        OfferToReviewModel offerToReviewModel = offerToReviewList.get(position);

        holder.textViewId.setText(offerToReviewModel.getId());
        holder.textViewCity.setText(offerToReviewModel.getCity());
        holder.textViewDistrict.setText(offerToReviewModel.getDistrict());
        holder.textViewStreet.setText(offerToReviewModel.getStreet());
        holder.textViewUserId.setText(offerToReviewModel.getUser_id());
        holder.textViewOwnerId.setText(offerToReviewModel.getOwner_id());

    }

    @Override
    public int getItemCount() {
        return offerToReviewList.size();
    }

    class OfferToReviewViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewId, textViewCity, textViewDistrict, textViewStreet, textViewUserId, textViewOwnerId;

        public OfferToReviewViewHolder(View itemView) {
            super(itemView);

            textViewId = itemView.findViewById(R.id.offer_id);
            textViewCity = itemView.findViewById(R.id.offer_city_name);
            textViewDistrict = itemView.findViewById(R.id.offer_district_name);
            textViewStreet = itemView.findViewById(R.id.offer_street);
            textViewUserId = itemView.findViewById(R.id.offer_user_id);
            textViewOwnerId = itemView.findViewById(R.id.offer_owner_id);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mCtx, SendReviewActivity.class);
                    intent.putExtra("id", textViewId.getText().toString());
                    intent.putExtra("user_id", textViewUserId.getText().toString());
                    intent.putExtra("owner_id", textViewOwnerId.getText().toString());
                    mCtx.startActivity(intent);
                }
            });
        }
    }
}
