package com.dreamyDestination.yash;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CabBooking.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CabBooking#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CabBooking extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private WebView webView = null;
    private OnFragmentInteractionListener mListener;

    private Button btn_cab,btn_hotel,btn_bus,btn_food;

    public CabBooking() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CabBooking.
     */
    // TODO: Rename and change types and number of parameters
    public static CabBooking newInstance(String param1, String param2) {
        CabBooking fragment = new CabBooking();
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

        View rootView = inflater.inflate(R.layout.fragment_cab_booking, container, false);

        btn_cab = (Button) rootView.findViewById(R.id.btn_cab);
        btn_bus = (Button) rootView.findViewById(R.id.btn_bus);
        btn_hotel = (Button) rootView.findViewById(R.id.btn_hotels);
        btn_food = (Button) rootView.findViewById(R.id.btn_food);

        btn_bus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Login = new Intent(getActivity().getApplicationContext(), BusBookingActivity.class);
                startActivity(Login);
            }
        });

        btn_hotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Login = new Intent(getActivity().getApplicationContext(), HotelBooking.class);
                startActivity(Login);
            }
        });

        btn_cab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Login = new Intent(getActivity().getApplicationContext(), cabBookingActivity.class);
                startActivity(Login);
            }
        });

        btn_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Login = new Intent(getActivity().getApplicationContext(), FoodActivity.class);
                startActivity(Login);
            }
        });



        return  rootView;
        //return inflater.inflate(R.layout.fragment_cab_booking, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if ((keyCode == KeyEvent.KEYCODE_BACK) && this.webView.canGoBack()) {
//            this.webView.goBack();
//            return true;
//        }
//
//        return super.onKeyDown(keyCode, event);
//    }

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
}
