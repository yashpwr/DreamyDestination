package com.dreamyDestination.yash.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dreamyDestination.yash.AdminAddCityDetailsActivity;
import com.dreamyDestination.yash.CityDetailActivity;
import com.dreamyDestination.yash.HomeMenuAdapter;
import com.dreamyDestination.yash.Menu;
import com.dreamyDestination.yash.R;
import com.dreamyDestination.yash.entity.FeedbackModel;

import java.util.ArrayList;
import java.util.List;

public class AdminPlacesListAdapter extends RecyclerView.Adapter<AdminPlacesListAdapter.MyViewHolder> {

    private Context mContext;
    private List<Menu> albumList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title; //count;
        public ImageView thumbnail;
        CardView cardView;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            //count = (TextView) view.findViewById(R.id.count);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            cardView = view.findViewById(R.id.card_view);
        }
    }


    public AdminPlacesListAdapter(Context mContext, List<Menu> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }

    @Override
    public AdminPlacesListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_admin_places, parent, false);

        return new AdminPlacesListAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final AdminPlacesListAdapter.MyViewHolder holder, final int position) {
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

                Toast.makeText(mContext, "This is "+ position, Toast.LENGTH_SHORT).show();
                Intent i = new Intent(mContext.getApplicationContext(), AdminAddCityDetailsActivity.class);
                i.putExtra("cityName",name);
                mContext.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }
}
