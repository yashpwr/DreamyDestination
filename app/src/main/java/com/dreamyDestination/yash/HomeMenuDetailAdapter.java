package com.dreamyDestination.yash;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class HomeMenuDetailAdapter extends RecyclerView.Adapter<HomeMenuDetailAdapter.MyBankViewholder>{
    private Context mContext;
    private ArrayList<Menu> albumList;

    public class MyBankViewholder extends RecyclerView.ViewHolder {
        public TextView title; //count;
        public ImageView thumbnail;
        CardView cardView;

        public MyBankViewholder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.person_name);
            //count = (TextView) view.findViewById(R.id.count);
            thumbnail = (ImageView) view.findViewById(R.id.person_photo);
            cardView = view.findViewById(R.id.cv);
        }
    }


    public HomeMenuDetailAdapter(Context mContext, ArrayList<Menu> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }

    @Override
    public HomeMenuDetailAdapter.MyBankViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_menu_detail_list, parent, false);

        return new HomeMenuDetailAdapter.MyBankViewholder(itemView);
    }

    @Override
    public void onBindViewHolder(final HomeMenuDetailAdapter.MyBankViewholder holder, final int position) {
        Menu album = albumList.get(position);
        holder.title.setText(album.getName());
        //holder.count.setText(album.getNumOfSongs() + " songs");

        // loading album cover using Glide library
        Glide.with(mContext).load(album.getThumbnail()).into(holder.thumbnail);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Menu album = albumList.get(position);
                holder.title.setText(album.getName());
                String name = album.getName();

                //Intent i = new Intent(mContext.getApplicationContext(), SpecificDetailActivity.class);
                //i.putExtra("Name",name);
                //mContext.startActivity(i);
           //mContext.startActivity(new Intent(mContext.getApplicationContext(), DetailActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }
}

