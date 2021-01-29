package com.dreamyDestination.yash;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.RatingBar;
import android.widget.TextView;
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


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FeedbackFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FeedbackFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FeedbackFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private EditText comment;
    private RatingBar ratingBar;
    private TextView tvRateCount,tvRateMessage;
    private float ratedValue;
    private Button btn_submit_feedback;
    private ProgressBar pbLoader;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private static final String TAG = LoginActivity.class.getSimpleName();
    private static final int RC_SIGN_IN = 007;

    private OnFragmentInteractionListener mListener;

    public FeedbackFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FeedbackFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FeedbackFragment newInstance(String param1, String param2) {
        FeedbackFragment fragment = new FeedbackFragment();
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

        View rootView = inflater.inflate(R.layout.fragment_feedback, container, false);
        comment = (EditText) rootView.findViewById(R.id.comment) ;
        ratingBar = (RatingBar) rootView.findViewById(R.id.ratingBar);
        tvRateCount = (TextView) rootView.findViewById(R.id.tvRateCount);
        tvRateMessage = (TextView) rootView.findViewById(R.id.tvRateMessage);
        btn_submit_feedback = (Button) rootView.findViewById(R.id.btn_submit_feedback);
        pbLoader = (ProgressBar) rootView.findViewById(R.id.pbLoader);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {
                ratedValue = ratingBar.getRating();
                tvRateCount.setText("Rating : "
                        + ratedValue + "/5.");

                if(ratedValue<1){
                    tvRateMessage.setText("ohh ho...");
                }else if(ratedValue<2){
                    tvRateMessage.setText("Ok.");
                }else if(ratedValue<3){
                    tvRateMessage.setText("Not bad.");
                }else if(ratedValue<4){
                    tvRateMessage.setText("Nice");
                }else if(ratedValue<5){
                    tvRateMessage.setText("Very Nice");
                }else if(ratedValue==5){
                    tvRateMessage.setText("Thank you..!!!");
                }
            }
        });

        btn_submit_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (comment.getText().toString().trim().equals(""))
                {
                    Toast.makeText(getActivity().getApplicationContext(), "Feedback saved successfully", Toast.LENGTH_LONG).show();
                }
                else {
                    addFeedback();
                }
                Intent Login = new Intent(getActivity().getApplicationContext(), RegisterActivity.class);
                startActivity(Login);
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

    private void addFeedback()
    {

        if (Connectivity.isConnected(getActivity())) {

            pbLoader.setVisibility(View.VISIBLE);
            btn_submit_feedback.setVisibility(View.GONE);


            final String UserType = "VOLANTEER";

            String mURL;
            mURL = ApiFunctions.addfeedback;

            StringRequest strReq = new StringRequest(Request.Method.POST,
                    mURL, new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {

                    Log.e(TAG, response.toString());

                    pbLoader.setVisibility(View.GONE);
                    btn_submit_feedback.setVisibility(View.VISIBLE);


                    try {
                        if (response.toString().trim().contains("Success."))
                        {
                            Toast.makeText(getActivity().getApplicationContext(), "Feedback saved successfully", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(getActivity().getApplicationContext(), "Feedback not saved.", Toast.LENGTH_LONG).show();
                        }

                        //startActivity(new Intent(FeedbackFragment.this, LoginActivity.class));
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
                    btn_submit_feedback.setVisibility(View.VISIBLE);
                    pbLoader.setVisibility(View.GONE);
                    Toast.makeText(getActivity().getApplicationContext(), "Something Went Wrong.Don't Worry it's not you it's us.", Toast.LENGTH_LONG).show();
                }

            }) {

                @Override
                protected Map<String, String> getParams() {

                    Map<String, String> params = new HashMap<String, String>();
                    Date d = new Date();
                    CharSequence strDate  = DateFormat.format("MMMM d, yyyy ", d.getTime());
                    SharedPreferences preferences = getActivity().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
                    params.put("feedback_rating", tvRateCount.getText().toString().trim());
                    params.put("comment", comment.getText().toString().trim());
                    params.put("posted_by",preferences.getString("full_name",""));
                    params.put("posted_on",strDate.toString().trim());
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
