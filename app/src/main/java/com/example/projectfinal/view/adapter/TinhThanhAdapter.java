package com.example.projectfinal.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectfinal.R;
import com.example.projectfinal.interfacecallback.OnItemClick;
import com.example.projectfinal.model.TinhThanh;

import java.util.List;

public class TinhThanhAdapter extends RecyclerView.Adapter<TinhThanhAdapter.TinhThanhHolder> {
    private Context context;
    private List<TinhThanh> listTinhThanh;
  //  private final View.OnClickListener event;
    private OnItemClick listener;
    public TinhThanhAdapter(Context context, List<TinhThanh> listTinhThanh) {//, View.OnClickListener event
        this.context = context;
        this.listTinhThanh = listTinhThanh;
        //this.event = event;
    }

    public void setOnItemClickListener(OnItemClick listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public TinhThanhHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_chon_tinh, parent, false);
        return new TinhThanhHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TinhThanhHolder holder, int position) {
        TinhThanh tinh = listTinhThanh.get(position);
        holder.tvTinh.setText(tinh.tinhThanh);
        holder.tvTinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    int position = holder.getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(position);
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listTinhThanh.size();
    }

    public class TinhThanhHolder extends RecyclerView.ViewHolder {
        TextView tvTinh;

        public TinhThanhHolder(@NonNull View v) {
            super(v);
            tvTinh = v.findViewById(R.id.tv_tinh);
          //  tvTinh.setOnClickListener(event);
        }
    }
}
