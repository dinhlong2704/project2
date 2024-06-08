package com.example.projectfinal.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectfinal.R;
import com.example.projectfinal.model.Song;

import java.util.List;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongHolder> {
    private final Context context;
    private final List<Song> listSong;
    private final View.OnClickListener event;
    private int currentSong;

    public SongAdapter(Context context, List<Song> listSong, View.OnClickListener event) {
        this.context = context;
        this.listSong = listSong;
        this.event = event;
    }

    @NonNull
    @Override
    public SongHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_song, parent, false);
        return new SongHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SongHolder holder, int position) {
        Song song = listSong.get(position);
        if (position == currentSong){
            holder.view.setBackgroundResource(R.color.purple200);
                    }
        else {
            holder.view.setBackgroundResource(R.color.white);
        }
        if (song.cover!=null){
            holder.ivCover.setImageURI(song.cover);
        }else {
            holder.ivCover.setImageResource(R.mipmap.ic_song);

        }
        holder.tvSong.setText(song.title);
        holder.tvSong.setTag(song);
    }

    @Override
    public int getItemCount() {
        return listSong.size();
    }

    public void updateUI(int currentSong){
        this.currentSong= currentSong;
        notifyItemRangeChanged(0,listSong.size());
    }

    public class SongHolder extends RecyclerView.ViewHolder {
        TextView tvSong;
        View view;
        ImageView ivCover;
        public SongHolder(@NonNull View v) {
            super(v);
            view = v.findViewById(R.id.view);
            ivCover = v.findViewById(R.id.iv_song);
            tvSong = v.findViewById(R.id.tv_song);
            tvSong.setOnClickListener(event);
        }
    }
}
