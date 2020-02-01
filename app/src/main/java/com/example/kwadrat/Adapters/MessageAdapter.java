package com.example.kwadrat.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.kwadrat.Models.MessageModel;
import com.example.kwadrat.R;
import com.example.kwadrat.ReplyMessageActivity;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {

    private Context mCtx;
    private List<MessageModel> messageList;
    private MessageAdapter.OnItemClickListener mListener;

    public MessageAdapter(Context mCtx, List<MessageModel> messageList) {
        this.mCtx = mCtx;
        this.messageList = messageList;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(MessageAdapter.OnItemClickListener listener) {
        mListener = listener;
    }

    @Override
    public MessageAdapter.MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.one_message, null);
        return new MessageAdapter.MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MessageAdapter.MessageViewHolder holder, int position) {
        MessageModel messageModel = messageList.get(position);

        holder.textViewMessageFrom.setText(messageModel.getMessage_from());
        holder.textViewAboutFlat.setText(messageModel.getAbout_flat());
        holder.textViewMessage.setText(messageModel.getMessage());
        holder.textViewLoggedUserEmail.setText(messageModel.getLogged_user_email());

    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    class MessageViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewMessageFrom, textViewAboutFlat, textViewMessage, textViewLoggedUserEmail;

        public MessageViewHolder(View itemView) {
            super(itemView);

            textViewMessageFrom = itemView.findViewById(R.id.message_from);
            textViewAboutFlat = itemView.findViewById(R.id.about_flat);
            textViewMessage = itemView.findViewById(R.id.message);
            textViewLoggedUserEmail = itemView.findViewById(R.id.logged_user_email);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mCtx, ReplyMessageActivity.class);
                    intent.putExtra("user_to_login", textViewMessageFrom.getText().toString());
                    intent.putExtra("flat_id", textViewAboutFlat.getText().toString());
                    intent.putExtra("user_from_email", textViewLoggedUserEmail.getText().toString());
                    mCtx.startActivity(intent);
                }
            });
        }
    }
}
