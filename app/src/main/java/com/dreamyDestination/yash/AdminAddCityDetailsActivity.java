package com.dreamyDestination.yash;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminAddCityDetailsActivity extends AppCompatActivity {

    private Button know_more_about_places,btn_restauarant,btn_hangout,btn_monumnets,btn_fun_facts;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_city_details);
        mContext = this;

        Bundle extras = getIntent().getExtras();
        final String city = extras.getString("cityName");

        know_more_about_places = (Button) findViewById(R.id.city_know_about_more);
        btn_restauarant = (Button) findViewById(R.id.city_restuarants);
        btn_fun_facts = (Button) findViewById(R.id.City_fun_facts);
        btn_monumnets = (Button) findViewById(R.id.city_monumnets);
        btn_hangout = (Button) findViewById(R.id.city_hangouts);

        know_more_about_places.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Login = new Intent(getApplicationContext(), AdminAddDetailPage.class);
                Login.putExtra("isFrom","knowAboutPlaces");
                Login.putExtra("City",city);
                startActivity(Login);
            }
        });

        btn_restauarant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Login = new Intent(getApplicationContext(), AdminAddDetailPage.class);
                Login.putExtra("isFrom","restuarants");
                Login.putExtra("City",city);
                startActivity(Login);
            }
        });


        btn_fun_facts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Login = new Intent(getApplicationContext(), AdminAddDetailPage.class);
                Login.putExtra("isFrom","funFacts");
                Login.putExtra("City",city);
                startActivity(Login);
            }
        });

        btn_monumnets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Login = new Intent(getApplicationContext(), AdminAddDetailPage.class);
                Login.putExtra("isFrom","monuments");
                Login.putExtra("City",city);
                startActivity(Login);
            }
        });

        btn_hangout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Login = new Intent(getApplicationContext(), AdminAddDetailPage.class);
                Login.putExtra("isFrom","hangout");
                Login.putExtra("City",city);
                startActivity(Login);
            }
        });

    }
}
