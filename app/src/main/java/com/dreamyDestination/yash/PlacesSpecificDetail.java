package com.dreamyDestination.yash;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dreamyDestination.yash.util.Utils;
import com.squareup.picasso.Picasso;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class PlacesSpecificDetail extends AppCompatActivity implements LoadImageTask.Listener {

    private TextView place_name;
    private TextView place_description;
    private Button view_on_map;

    private ImageView imageVIew;
    private Context mContext;

    private String city;
    private String isFrom;
    private String image_path;
    private String address;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places_specific_detail);
        mContext = this;
        place_name = (TextView) findViewById(R.id.disaster_name) ;
        place_description = (TextView) findViewById(R.id.select_category) ;
        imageVIew = (ImageView) findViewById(R.id.imageVIew);
        Bundle extras = getIntent().getExtras();
        city = extras.getString("CityName");
        isFrom = extras.getString("isFrom");
        address = extras.getString("Address");
        image_path = extras.getString("image");
        view_on_map = (Button) findViewById(R.id.btn_verified) ;
        place_name.setText("Address: " + address);
        place_description.setText("Place Name: " + city);

        //Picasso.with(mContext).load(image_path).fit().into(imageVIew);

        new LoadImageTask(PlacesSpecificDetail.this).execute(image_path);


        view_on_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((isFrom.equals("knowAboutPlaces")) || (isFrom.equals("funFacts")))
                {

                }
                else
                {
                    String yourAddress = address;
                    String map = "http://maps.google.co.in/maps?q=" + yourAddress;
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(map));
                    mContext.startActivity(intent);
                }

            }
        });


    }

    @Override
    public void onImageLoaded(Bitmap bitmap) {
        imageVIew.setImageBitmap(bitmap);

    }

    @Override
    public void onError() {

    }
}
