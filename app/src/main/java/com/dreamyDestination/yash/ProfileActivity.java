package com.dreamyDestination.yash;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import static android.app.Activity.RESULT_OK;
import static com.dreamyDestination.yash.TravelTipsFragment.TAG;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProfileActivity.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProfileActivity#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileActivity extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private EditText user_full_name,user_email,tell_me_about_yourself;
    private TextView user_joined_date;
    private ProgressBar pbLoader;
    private Button btn_change_password,btn_update_profile;
    private ImageButton user_profile_photo;
    private OnFragmentInteractionListener mListener;

    Bitmap bitmap;

    public ProfileActivity() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileActivity.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileActivity newInstance(String param1, String param2) {
        ProfileActivity fragment = new ProfileActivity();
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
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        user_full_name = (EditText) rootView.findViewById(R.id.user_full_name);
        user_email = (EditText) rootView.findViewById(R.id.profile_user_email);
        user_joined_date = (TextView) rootView.findViewById(R.id.joined_date);
        btn_change_password = (Button) rootView.findViewById(R.id.btn_change_password);
        btn_update_profile = (Button) rootView.findViewById(R.id.btn_update_profile);
        pbLoader = (ProgressBar) rootView.findViewById(R.id.pbLoader);
        SharedPreferences preferences = getActivity().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);

        user_full_name.setText("Name: " + preferences.getString("full_name",""));
        user_email.setText("Email: " + preferences.getString("user_email_id",""));
        user_joined_date.setText("Joined Date: " + preferences.getString("joined_date",""));
        user_profile_photo = (ImageButton) rootView.findViewById(R.id.user_profile_photo);


        String image = preferences.getString("imagePreferance","");
        if (image.equals(""))
        {

        }
        else
        {
            user_profile_photo.setImageBitmap(decodeBase64(image));
        }



        user_profile_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();

                intent.setType("image/*");

                intent.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(intent, "Select Image From Gallery"), 1);

            }
        });

        btn_change_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Login = new Intent(getActivity(), ChangePassword.class);
                startActivity(Login);
            }
        });


        btn_update_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProfile();
                //Intent Login = new Intent(getActivity(), LoginActivity.class);
                //startActivity(Login);
            }
        });




        return rootView;
    }


    @Override
    public void onActivityResult(int RC, int RQC, Intent I) {

        super.onActivityResult(RC, RQC, I);

        if (RC == 1 && RQC == RESULT_OK && I != null && I.getData() != null) {

            Uri uri = I.getData();

            try {

                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);

                user_profile_photo.setImageBitmap(bitmap);
                //encodeTobase64(bitmap);

            } catch (IOException e) {

                e.printStackTrace();
            }
        }
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

    public static String encodeTobase64(Bitmap image) {
        Bitmap immage = image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        immage.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);

        Log.d("Image Log:", imageEncoded);
        return imageEncoded;
    }


    public static Bitmap decodeBase64(String input) {
        byte[] decodedByte = Base64.decode(input, 0);
        return BitmapFactory
                .decodeByteArray(decodedByte, 0, decodedByte.length);
    }


    private void updateProfile(){

        if (Connectivity.isConnected(getActivity())) {

            pbLoader.setVisibility(View.VISIBLE);
            btn_update_profile.setVisibility(View.GONE);


            final String UserType = "VOLANTEER";

            String mURL;
            mURL = ApiFunctions.updateProfile;

            StringRequest strReq = new StringRequest(Request.Method.POST,
                    mURL, new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {

                    Log.e(TAG, response.toString());

                    pbLoader.setVisibility(View.GONE);
                    btn_update_profile.setVisibility(View.VISIBLE);


                    try {

                        if (response.toString().contains("Records inserted successfully"))
                        {
                            Toast.makeText(getActivity().getApplicationContext(), "Profile Updated Successful.", Toast.LENGTH_LONG).show();
                            SharedPreferences preferences = getActivity().getSharedPreferences("MYImagePrefrences", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("imagePreferance", encodeTobase64(bitmap));
                            editor.commit();

//                            InputStream stream;
//                            stream = getActivity().getContentResolver().openInputStream(getActivity().getData());
//                            Bitmap realImage = BitmapFactory.decodeStream(stream);
//                            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                            realImage.compress(Bitmap.CompressFormat.JPEG, 100, baos);
//                            byte[] b = baos.toByteArray();
//
//                            String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
//                            //textEncode.setText(encodedImage);
//
//                            SharedPreferences shre = PreferenceManager.getDefaultSharedPreferences(getActivity());
//                            SharedPreferences.Editor edit=shre.edit();
//                            edit.putString("image_data",encodedImage);
//                            edit.commit();

//                            Intent i = new Intent(getActivity(), LoginActivity.class);
//                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                            startActivity(i);
                        }
                        else
                        {
                            Toast.makeText(getActivity().getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();
                            //startActivity(new Intent(ChangePassword.this, LoginActivity.class));
                            //finish();
                        }




                    } catch (Exception ex) {
                        //btn_login.setVisibility(View.VISIBLE);
                        ex.printStackTrace();
                    }


                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d(TAG, error.toString());
                    btn_update_profile.setVisibility(View.VISIBLE);
                    pbLoader.setVisibility(View.GONE);
                    Toast.makeText(getActivity().getApplicationContext(), "Something Went Wrong.Don't Worry it's not you it's us.", Toast.LENGTH_LONG).show();
                }

            }) {

                @Override
                protected Map<String, String> getParams() {

                    Map<String, String> params = new HashMap<String, String>();
                    SharedPreferences preferences = getActivity().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
                    final  String user_id = preferences.getString("user_unique_id","");
                    params.put("user_unique_id", user_id);
                    params.put("user_full_name", user_full_name.getText().toString().trim());
                    params.put("user_email", user_email.getText().toString().trim());
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
            Toast.makeText(getActivity(), "No Internet Connection Available", Toast.LENGTH_SHORT).show();
        }
    }
}
