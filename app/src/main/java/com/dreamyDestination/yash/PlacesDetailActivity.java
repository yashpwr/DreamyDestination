package com.dreamyDestination.yash;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.dreamyDestination.yash.Adapter.AdminFeedbackAdapter;
import com.dreamyDestination.yash.Adapter.UserPlacesListAdapter;
import com.dreamyDestination.yash.api.ApiFunctions;
import com.dreamyDestination.yash.entity.FeedbackModel;
import com.dreamyDestination.yash.entity.PlacesDetailListModel;
import com.dreamyDestination.yash.util.AppController;
import com.dreamyDestination.yash.util.Connectivity;
import com.dreamyDestination.yash.util.ItemClickSupport;
import com.dreamyDestination.yash.util.JSONParser;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static com.dreamyDestination.yash.TravelTipsFragment.TAG;

public class PlacesDetailActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    EditText editTextSearch;
    ArrayList<String> names;
    private Context mContext;
    private String city;
    private String isFrom;

    UserPlacesListAdapter adapter;

    ArrayList<PlacesDetailListModel> studentDetailInfoList = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places_detail);
        mContext = this;

        //names = new ArrayList<>();

//        names.add("Ramiz");
//        names.add("Belal");
//        names.add("Azad");
//        names.add("Manish");
//        names.add("Sunny");
//        names.add("Shahid");
//        names.add("Deepak");
//        names.add("Deepika");
//        names.add("Sumit");
//        names.add("Mehtab");
//        names.add("Vivek");


        Bundle extras = getIntent().getExtras();
        city = extras.getString("City");
        isFrom = extras.getString("isFrom");


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        editTextSearch = (EditText) findViewById(R.id.editTextSearch);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getPlacesList();


        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                //after the change calling the method and passing the search input
                filter(editable.toString());
            }
        });


        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {

                try
                {
                    PlacesDetailListModel appointmentInfo = studentDetailInfoList.get(position);

                    if(appointmentInfo != null)
                    {
                        Intent Login = new Intent(getApplicationContext(), PlacesSpecificDetail.class);
                        Login.putExtra("CityName",appointmentInfo.getPlace_name());
                        Login.putExtra("Address",appointmentInfo.getPlace_address());
                        Login.putExtra("image",appointmentInfo.getImage_path());
                        Login.putExtra("isFrom",isFrom);
                        mContext.startActivity(Login);
                    }


                }catch (Exception ex)
                {
                    ex.printStackTrace();
                }

            }
        });


    }

    private void filter(String text) {
        //new array list that will hold the filtered data
        ArrayList<String> filterdNames = new ArrayList<>();

        //looping through existing elements
        for (String s : names) {
            //if the existing elements contains the search input
            if (s.toLowerCase().contains(text.toLowerCase())) {
                //adding the element to filtered list
                filterdNames.add(s);
            }
        }

        //calling a method of the adapter class and passing the filtered list
        adapter.filterList(filterdNames);
    }


    private void getPlacesList()
    {

        if (Connectivity.isConnected(mContext)) {

            //pbLoader.setVisibility(View.VISIBLE);
            //btn_add_travel_tip.setVisibility(View.GONE);


            final String UserType = "VOLANTEER";

            String mURL;
            mURL = ApiFunctions.getPlacesDetailList;

            StringRequest strReq = new StringRequest(Request.Method.POST,
                    mURL, new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {

                    Log.e(TAG, response.toString());


                    try {

                        studentDetailInfoList = JSONParser.parsePlacesDetailList(response.toString());

//                        for (int i = 0; i < studentDetailInfoList.size(); i++)
//                        {
//                            PlacesDetailListModel object = studentDetailInfoList.get(i);
//                            names.add(object.getPlace_name());
//                            //do something with i
//                        }
                        UserPlacesListAdapter adapter = new UserPlacesListAdapter(mContext,studentDetailInfoList);
                        recyclerView.setAdapter(adapter);



                    } catch (Exception ex) {
                        //btn_login.setVisibility(View.VISIBLE);
                        ex.printStackTrace();
                    }


                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d(TAG, error.toString());

                    Toast.makeText(mContext.getApplicationContext(), "Something Went Wrong.Don't Worry it's not you it's us.", Toast.LENGTH_LONG).show();
                }

            }) {

                @Override
                protected Map<String, String> getParams() {

                    Map<String, String> params = new HashMap<String, String>();
                    Date d = new Date();
                    CharSequence strDate  = DateFormat.format("MMMM d, yyyy ", d.getTime());
                    params.put("place_city", city);
                    params.put("place_type",isFrom);
                    return params;
                }

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> map = new HashMap<String, String>();
                    return map;
                }


            };

            AppController.getInstance().addToRequestQueue(strReq, TAG);

        } else {
            //Utils.displayDialog(mContext, getString(R.string.no_internet_title), getString(R.string.no_internet_desc));
            Toast.makeText(mContext.getApplicationContext(), "No Internet Connection Available", Toast.LENGTH_SHORT).show();
        }
    }


}
