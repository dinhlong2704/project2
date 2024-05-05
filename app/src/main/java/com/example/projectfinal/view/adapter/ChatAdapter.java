package com.example.projectfinal.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectfinal.Message;
import com.example.projectfinal.R;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatHolder> {
    private final Context context;
    private final List<Message> listMsg;

    public ChatAdapter(Context context, List<Message> listMsg) {
        this.context = context;
        this.listMsg = listMsg;
    }

    @NonNull
    @Override
    public ChatHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == Message.TYPE_LEFT) {
            return new ChatHolder(LayoutInflater.from(context).inflate(R.layout.item_msg_left, parent, false));
        }
        return new ChatHolder(LayoutInflater.from(context).inflate(R.layout.item_msg_right, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ChatHolder holder, int position) {
        Message msg = listMsg.get(position);
        holder.tvMsg.setText(msg.msg);
    }

    @Override
    public int getItemViewType(int position) {
        Message msg = listMsg.get(position);
        return msg.msgType;
    }

    @Override
    public int getItemCount() {
        return listMsg.size();
    }

    public static class ChatHolder extends RecyclerView.ViewHolder {
        public TextView tvMsg;

        public ChatHolder(@NonNull View itemView) {
            super(itemView);
            tvMsg = itemView.findViewById(R.id.tv_msg);
        }
    }
}
