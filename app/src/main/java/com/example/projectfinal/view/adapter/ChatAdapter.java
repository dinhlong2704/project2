package com.example.projectfinal.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectfinal.App;
import com.example.projectfinal.Message;
import com.example.projectfinal.R;
import com.example.projectfinal.Storage;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatHolder> {
    private Context context;



    private List<Message> listMsg;
    public ChatAdapter(Context context) {
        this.context = context;

    }
    public void setData(List<Message> listMsg) {
        this.listMsg = listMsg;
    }

    @NonNull
    @Override
    public ChatHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_msg_right, parent, false);
        return new ChatHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatHolder holder, int position) {
        Message msg = listMsg.get(position);
        holder.tvMsg.setText(msg.getMsg());
    }

    @Override
    public int getItemCount() {
        return listMsg.size();
    }

    public class ChatHolder extends RecyclerView.ViewHolder {
         public TextView tvMsg;
        public ChatHolder(@NonNull View itemView) {
            super(itemView);
            tvMsg = itemView.findViewById(R.id.tv_msg);
        }
    }
}
