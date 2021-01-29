package com.dreamyDestination.yash;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 */
public class HomeMenuAdapter extends RecyclerView.Adapter<HomeMenuAdapter.MyViewHolder> {

    private Context mContext;
    private List<Menu> albumList;
    private ArrayList<String> names;
    ArrayList<String> filterdNames;

    private String isFilteredCalled;

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


    public HomeMenuAdapter(Context mContext, List<Menu> albumList,ArrayList<String> filterdNames) {
        this.mContext = mContext;
        this.albumList = albumList;
        this.filterdNames = filterdNames;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_home_cardview, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

//        names = new ArrayList<>();
//
//        names.add("Pune");
//        names.add("Kolkata");
//        names.add("Jaipur");
//        names.add("Panjim");
//        names.add("Hyderabad");
//        names.add("Mysoore");
//        names.add("Ahmedabad");
//        names.add("Delhi");
//        names.add("Banglore");
//        names.add("Chennai");

        if (isFilteredCalled == "YES")
        {
            Menu album = albumList.get(position);
            if (names == null)
            {
                holder.title.setText(album.getName());
            }
            else
            {
                holder.title.setText(names.get(position));
            }

            //holder.count.setText(album.getNumOfSongs() + " songs");

            // loading album cover using Glide library
            Glide.with(mContext).load(album.getThumbnail()).into(holder.thumbnail);

            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Menu album = albumList.get(position);
                    String name;
                    if (names == null)
                    {
                        holder.title.setText(album.getName());
                        name = album.getName();
                    }
                    else
                    {
                        holder.title.setText(names.get(position));
                        name = names.get(position);
                    }

                    //String name = album.getName();

                    Toast.makeText(mContext, "This is "+ position, Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(mContext.getApplicationContext(), CityDetailActivity.class);
                    i.putExtra("cityName",name);
                    mContext.startActivity(i);
                }
            });
        }
        else
        {
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
                    Intent i = new Intent(mContext.getApplicationContext(), CityDetailActivity.class);
                    i.putExtra("cityName",name);
                    mContext.startActivity(i);
                }
            });
        }

    }


    @Override
    public int getItemCount() {
        if (isFilteredCalled == "YES")
        {
            return  names.size();

        }
        else
        {
            return albumList.size();
        }
    }


    public void filterList(ArrayList<String> filterdNames) {
        isFilteredCalled = "YES";
        this.names = filterdNames;
        notifyDataSetChanged();
    }
}
