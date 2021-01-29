package com.dreamyDestination.yash;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class CityDetailActivity extends AppCompatActivity {

    TextView cityTemprature ;
    //ProgressBar loader;
    Typeface weatherFont;
    String city = "Dhaka, BD";
    /* Please Put your API KEY here */
    String OPEN_WEATHER_MAP_API = "36b2270cced9804cd80555e7beaefb76";
    private Context mContext;
    private double temprature;
    private ImageView city_image;
    /* Please Put your API KEY here */

    private Button know_more_about,fun_facts,hangouts,city_monumnets,city_hotels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_detail);

        mContext = this;
        //loader = (ProgressBar) findViewById(R.id.pbLoader);
        cityTemprature = (TextView) findViewById(R.id.city_temprature);
        weatherFont = Typeface.createFromAsset(getAssets(), "fonts/weathericons-regular-webfont.ttf");
        cityTemprature.setTypeface(weatherFont);
        city = getIntent().getStringExtra("cityName");
        know_more_about = (Button) findViewById(R.id.city_know_about_more) ;
        fun_facts = (Button) findViewById(R.id.City_fun_facts);
        hangouts = (Button) findViewById(R.id.city_hangouts) ;
        city_monumnets = (Button) findViewById(R.id.city_monumnets) ;
        city_hotels = (Button) findViewById(R.id.city_hotels) ;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        taskLoadUp(city);


        know_more_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Login = new Intent(getApplicationContext(), PlacesDetailActivity.class);
                Login.putExtra("isFrom","knowAboutPlaces");
                Login.putExtra("City",city);
                startActivity(Login);
            }
        });

        city_hotels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Login = new Intent(getApplicationContext(), HotelBooking.class);
                //Login.putExtra("isFrom","restuarants");
                //Login.putExtra("City",city);
                startActivity(Login);
            }
        });


        fun_facts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Login = new Intent(getApplicationContext(), PlacesDetailActivity.class);
                Login.putExtra("isFrom","funFacts");
                Login.putExtra("City",city);
                startActivity(Login);
            }
        });

        city_monumnets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Login = new Intent(getApplicationContext(), PlacesDetailActivity.class);
                Login.putExtra("isFrom","monuments");
                Login.putExtra("City",city);
                startActivity(Login);
            }
        });

        hangouts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Login = new Intent(getApplicationContext(), PlacesDetailActivity.class);
                Login.putExtra("isFrom","hangout");
                Login.putExtra("City",city);
                startActivity(Login);
            }
        });


//        selectCity.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                final String climate = detailsField.getText().toString().trim();
//                final String tempratureDegree = currentTemperatureField.getText().toString().trim();
//                final String pressureStr = pressure_field.getText().toString().trim();
//                final String cityStr = cityField.getText().toString().trim();
//                int tempratureInt = (int) temprature;
//
//                SharedPreferences preferences = mContext.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
//                SharedPreferences.Editor editor = preferences.edit();
//                editor.remove("temprature");
//                editor.putInt("temprature", tempratureInt);
//                editor.commit();
//            }
//        });
    }

    public void taskLoadUp(String query) {
        if (Function.isNetworkAvailable(getApplicationContext())) {
            DownloadWeather task = new DownloadWeather();
            task.execute(query);
        } else {
            Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
        }
    }


    class DownloadWeather extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //loader.setVisibility(View.VISIBLE);

        }

        protected String doInBackground(String... args) {
            String url = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&units=metric&appid=" + OPEN_WEATHER_MAP_API;
            String xml = Function.excuteGet("http://api.openweathermap.org/data/2.5/weather?q=" + args[0] +
                    "&units=metric&appid=" + OPEN_WEATHER_MAP_API);

            return xml;
        }

        @Override
        protected void onPostExecute(String xml) {

            try {
                JSONObject json = new JSONObject(xml);
                if (json != null) {
                    JSONObject details = json.getJSONArray("weather").getJSONObject(0);
                    JSONObject main = json.getJSONObject("main");
                    DateFormat df = DateFormat.getDateTimeInstance();
                    temprature = main.getDouble("temp");
                    cityTemprature.setText("City Name: " + city + "\nHumidity: " + main.getString("humidity") + "%" + "\nPressure: " + main.getString("pressure") + " hPa" + "\nTemprature: " + temprature);
                    //loader.setVisibility(View.GONE);

                }
            } catch (JSONException e) {
                Toast.makeText(mContext.getApplicationContext(), "Error, Check City", Toast.LENGTH_SHORT).show();
                //loader.setVisibility(View.GONE);
            }


        }
    }
}
