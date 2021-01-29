package com.dreamyDestination.yash;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.dreamyDestination.yash.api.ApiFunctions;
import com.dreamyDestination.yash.util.AppController;
import com.dreamyDestination.yash.util.Connectivity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.dreamyDestination.yash.TravelTipsFragment.TAG;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AdminAddTravelTipsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AdminAddTravelTipsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdminAddTravelTipsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private EditText travel_tip_text,travel_tip_url;
    private Button btn_add_travel_tip;
    private ProgressBar pbLoader;
    private String typeOfInput;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public AdminAddTravelTipsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AdminAddTravelTipsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AdminAddTravelTipsFragment newInstance(String param1, String param2) {
        AdminAddTravelTipsFragment fragment = new AdminAddTravelTipsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_admin_add_travel_tips, container, false);
        typeOfInput = "";
        travel_tip_text = (EditText) rootView.findViewById(R.id.enter_text);
        travel_tip_url = (EditText) rootView.findViewById(R.id.enter_url);
        btn_add_travel_tip = (Button) rootView.findViewById(R.id.btn_add_travel_tips);
        pbLoader = (ProgressBar) rootView.findViewById(R.id.pbLoader);

        btn_add_travel_tip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ((travel_tip_text.getText().toString().trim().equals("")) && (travel_tip_url.getText().toString().trim().equals("")))
                {
                    Toast.makeText(getActivity(), "Please Enter Either URL or Text as a travel tip for User.", Toast.LENGTH_SHORT).show();
                }
                else if (!travel_tip_url.getText().toString().trim().equals(""))
                {
                    typeOfInput = "TEXT";
                    addTravelTip();
                }
                else if (!travel_tip_text.getText().toString().trim().equals(""))
                {
                    typeOfInput = "URL";
                    addTravelTip();
                }

                //Intent Login = new Intent(getActivity(), RegisterActivity.class);
                //startActivity(Login);
            }
        });
        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void addTravelTip()
    {

        if (Connectivity.isConnected(getActivity())) {

            pbLoader.setVisibility(View.VISIBLE);
            btn_add_travel_tip.setVisibility(View.GONE);


            final String UserType = "VOLANTEER";

            String mURL;
            mURL = ApiFunctions.addTravelTip;

            StringRequest strReq = new StringRequest(Request.Method.POST,
                    mURL, new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {

                    Log.e(TAG, response.toString());

                    pbLoader.setVisibility(View.GONE);
                    btn_add_travel_tip.setVisibility(View.VISIBLE);


                    try {
                        if (response.toString().trim().contains("Success"))
                        {
                            Toast.makeText(getActivity().getApplicationContext(), "Travel Tips Addded Successfully", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(getActivity().getApplicationContext(), response.toString().trim(), Toast.LENGTH_LONG).show();
                        }
                        //Toast.makeText(getApplicationContext(), "Registration Successful.", Toast.LENGTH_LONG).show();
                        //startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                        //finish();



                    } catch (Exception ex) {
                        //btn_login.setVisibility(View.VISIBLE);
                        ex.printStackTrace();
                    }


                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d(TAG, error.toString());
                    btn_add_travel_tip.setVisibility(View.VISIBLE);
                    pbLoader.setVisibility(View.GONE);
                    Toast.makeText(getActivity().getApplicationContext(), "Something Went Wrong.Don't Worry it's not you it's us.", Toast.LENGTH_LONG).show();
                }

            }) {

                @Override
                protected Map<String, String> getParams() {

                    Map<String, String> params = new HashMap<String, String>();
                    Date d = new Date();
                    CharSequence strDate  = DateFormat.format("MMMM d, yyyy ", d.getTime());
                    if (typeOfInput.equals("TEXT"))
                    {
                        params.put("travel_description", travel_tip_text.getText().toString().trim());
                    }
                    else
                    {
                        params.put("travel_description", travel_tip_url.getText().toString().trim());
                    }
                    Date tipDate = new Date();
                    CharSequence tipDateChar  = DateFormat.format("MMMM d, yyyy ", tipDate.getTime());

                    params.put("travel_description_type", typeOfInput);
                    params.put("date_added",tipDateChar.toString());
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
            Toast.makeText(getActivity().getApplicationContext(), "No Internet Connection Available", Toast.LENGTH_SHORT).show();
        }
    }
}
