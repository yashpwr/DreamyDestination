package com.dreamyDestination.yash;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

public class HomeMenuDetail extends AppCompatActivity {

    private static final String TAG = "BankActivity";

    Context mContext;
    Toolbar customToolBar;
    RecyclerView recyclerView;
    private HomeMenuDetailAdapter adapter;
    private ArrayList<Menu> albumList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_menu_detail);

        mContext = this;
        init();
        getIntentExter();
    }

    private void init() {

        adapter= new HomeMenuDetailAdapter(mContext, albumList);

        recyclerView = findViewById(R.id.recycler_view_bank);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        prepareAlbums();

    }

    private void prepareAlbums() {
        int[] covers = new int[]{
                R.drawable.pune,
                R.drawable.kolkata,
                R.drawable.jaipur,
                R.drawable.panaji,
                R.drawable.hyderabad,
                R.drawable.mysoore,
                R.drawable.ahmedabad,
                R.drawable.delhi,
                R.drawable.banglore,
                R.drawable.chennai};

        Menu a = new Menu("Pune", 13,covers[0]);
        albumList.add(a);

        a = new Menu("Kolkata", 8,covers[1]);
        albumList.add(a);

        a = new Menu("Jaipur", 11,covers[2]);
        albumList.add(a);

        a = new Menu("Panjim", 12,covers[3]);
        albumList.add(a);

        a = new Menu("Hyderabad", 14,covers[4]);
        albumList.add(a);

        a = new Menu("Mysoore", 1,covers[5]);
        albumList.add(a);

        a = new Menu("Ahmedabad", 11,covers[6]);
        albumList.add(a);

        a = new Menu("Delhi", 11,covers[7]);
        albumList.add(a);

        a = new Menu("Banglore", 14,covers[8]);
        albumList.add(a);

        a = new Menu("Chennai", 11,covers[9]);
        albumList.add(a);

        adapter.notifyDataSetChanged();
    }


    private void getIntentExter() {
        Intent i = getIntent();
        String name = i.getStringExtra("Name");
        customToolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(customToolBar);
        getSupportActionBar().setTitle(name);
    }
}

