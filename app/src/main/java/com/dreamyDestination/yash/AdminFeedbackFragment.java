package com.dreamyDestination.yash;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.dreamyDestination.yash.Adapter.AdminFeedbackAdapter;
import com.dreamyDestination.yash.Adapter.RegisteredUserAdapter;
import com.dreamyDestination.yash.api.ApiFunctions;
import com.dreamyDestination.yash.entity.FeedbackModel;
import com.dreamyDestination.yash.entity.RegisteredUserModel;
import com.dreamyDestination.yash.util.AppController;
import com.dreamyDestination.yash.util.Connectivity;
import com.dreamyDestination.yash.util.ItemClickSupport;
import com.dreamyDestination.yash.util.JSONParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AdminFeedbackFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AdminFeedbackFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdminFeedbackFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;


    ArrayList<FeedbackModel> studentDetailInfoList = new ArrayList();


    public static final String TAG = AdminFeedbackFragment.class.getSimpleName();

    private RecyclerView recyclerView;

    public AdminFeedbackFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AdminFeedbackFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AdminFeedbackFragment newInstance(String param1, String param2) {
        AdminFeedbackFragment fragment = new AdminFeedbackFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_admin_feedback, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.fragment_square_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        getFeedBacklist();

        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {

                try {



                } catch (Exception ex) {
                    ex.printStackTrace();
                }
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

    private void getFeedBacklist()
    {

        if (Connectivity.isConnected(getActivity())) {

            //SQLiteFetcher fetcher = new SQLiteFetcher(getActivity());


            StringRequest strReq = new StringRequest(Request.Method.GET,
                    ApiFunctions.getfeedback, new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {

                    Log.e(TAG, response.toString());

                    ////pbLoader.setVisibility(View.GONE);

                    try {

                        //JSONObject jsonobj = new JSONObject(response.toString());

                        studentDetailInfoList = JSONParser.parseFeedback(response.toString());

                        AdminFeedbackAdapter adapter = new AdminFeedbackAdapter(getContext(),studentDetailInfoList);
                        recyclerView.setAdapter(adapter);



                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }


                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d(TAG, error.toString());
                    //pbLoader.setVisibility(View.GONE);

                    //Utils.displayDialog(activity, getString(R.string.common_error_title), getString(R.string.common_error_desc));
                    Toast.makeText(getContext(), "Something Went Wrong.", Toast.LENGTH_SHORT).show();

                }

            }) {

                @Override
                protected Map<String, String> getParams() {

                    Map<String, String> params = new HashMap<String, String>();
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
            //Utils.displayDialog(activity, getString(R.string.no_internet_title), getString(R.string.no_internet_desc));
            Toast.makeText(getContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
        }
    }
}
