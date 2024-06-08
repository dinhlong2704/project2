package com.example.projectfinal.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.projectfinal.R;
import com.example.projectfinal.model.Hospital;

import java.util.ArrayList;

public class HostpitalAdapter extends PagerAdapter {
    private ArrayList<Hospital> listPlace;
    private Context context;
    private final View.OnClickListener event;

    public HostpitalAdapter(ArrayList<Hospital> listPlace, Context context,View.OnClickListener event) {
        this.listPlace = listPlace;
        this.context = context;
        this.event= event;
    }

    @Override
    public int getCount() {
        return listPlace.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_place,container,false);
        ImageView ivPlace = v.findViewById(R.id.iv_place_item);
        TextView tvName = v.findViewById(R.id.tv_name);
        TextView tvDesc = v.findViewById(R.id.tv_desc);

        Hospital item  = listPlace.get(position);
        Glide.with(context).load(item.linkPhoto).into(ivPlace);
        tvName.setText(item.name);
        tvDesc.setText(item.desc);
        ivPlace.setTag(item);
        ivPlace.setOnClickListener(event);
        container.addView(v);
        return v;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }
}
