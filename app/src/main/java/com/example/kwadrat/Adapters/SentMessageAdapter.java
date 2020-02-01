package com.example.kwadrat.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.kwadrat.Models.MessageModel;
import com.example.kwadrat.Models.SentMessageModel;
import com.example.kwadrat.R;
import com.example.kwadrat.ReplyMessageActivity;

import java.util.List;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

public class SentMessageAdapter extends RecyclerView.Adapter<SentMessageAdapter.SentMessageViewHolder> {

    private Context mCtx;
    private List<SentMessageModel> sentMessageList;
    private SentMessageAdapter.OnItemClickListener mListener;

    public SentMessageAdapter(Context mCtx, List<SentMessageModel> sentMessageList) {
        this.mCtx = mCtx;
        this.sentMessageList = sentMessageList;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(SentMessageAdapter.OnItemClickListener listener) {
        mListener = listener;
    }

    @Override
    public SentMessageAdapter.SentMessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.one_message, null);
        return new SentMessageAdapter.SentMessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SentMessageAdapter.SentMessageViewHolder holder, int position) {
        SentMessageModel sentMessageModel = sentMessageList.get(position);

        holder.textViewMessageFrom.setText(sentMessageModel.getMessage_from());
        holder.textViewAboutFlat.setText(sentMessageModel.getAbout_flat());
        holder.textViewMessage.setText(sentMessageModel.getMessage());
        holder.textViewLoggedUserEmail.setText(sentMessageModel.getLogged_user_email());
        holder.textViewMessageFromLabel.setText("Do: ");
        holder.textViewTapToReply.setVisibility(View.GONE);

    }

    @Override
    public int getItemCount() {
        return sentMessageList.size();
    }

    class SentMessageViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewMessageFromLabel, textViewMessageFrom, textViewAboutFlat, textViewMessage, textViewLoggedUserEmail, textViewTapToReply;

        public SentMessageViewHolder(View itemView) {
            super(itemView);

            textViewMessageFromLabel = itemView.findViewById(R.id.message_from_label);
            textViewMessageFrom = itemView.findViewById(R.id.message_from);
            textViewAboutFlat = itemView.findViewById(R.id.about_flat);
            textViewMessage = itemView.findViewById(R.id.message);
            textViewLoggedUserEmail = itemView.findViewById(R.id.logged_user_email);
            textViewTapToReply = itemView.findViewById(R.id.tap_to_reply);
        }
    }
}
