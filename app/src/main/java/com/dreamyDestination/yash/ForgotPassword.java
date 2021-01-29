package com.dreamyDestination.yash;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
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

public class ForgotPassword extends AppCompatActivity {

    private EditText user_email;
    private Context mContext;
    private ProgressBar pbLoader;
    private Button btn_submit;

    private static final String TAG = LoginActivity.class.getSimpleName();
    private static final int RC_SIGN_IN = 007;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        mContext = this;
        user_email = (EditText) findViewById(R.id.user_email);
        pbLoader = (ProgressBar) findViewById(R.id.pbLoader);
        btn_submit = (Button) findViewById(R.id.btn_submit);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user_email.getText().toString().trim().equals(""))
                {

                }
                else
                {
                    forgotPassword();
                }
            }
        });
    }

    private void forgotPassword()
    {

        if (Connectivity.isConnected(mContext)) {

            pbLoader.setVisibility(View.VISIBLE);
            btn_submit.setVisibility(View.GONE);


            final String UserType = "VOLANTEER";

            String mURL;
            mURL = ApiFunctions.forgotPassword;

            StringRequest strReq = new StringRequest(Request.Method.POST,
                    mURL, new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {

                    Log.e(TAG, response.toString());

                    pbLoader.setVisibility(View.GONE);
                    btn_submit.setVisibility(View.VISIBLE);


                    try {

                        if (response.toString().contains("Records inserted successfully"))
                        {
                            Toast.makeText(getApplicationContext(), "Password has been sent to registered email address.", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(ForgotPassword.this, LoginActivity.class));
                            finish();
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();
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
                    btn_submit.setVisibility(View.VISIBLE);
                    pbLoader.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "Something Went Wrong.Don't Worry it's not you it's us.", Toast.LENGTH_LONG).show();
                }

            }) {

                @Override
                protected Map<String, String> getParams() {

                    Map<String, String> params = new HashMap<String, String>();
                    Date d = new Date();
                    CharSequence strDate  = DateFormat.format("MMMM d, yyyy ", d.getTime());

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
            Toast.makeText(getApplicationContext(), "No Internet Connection Available", Toast.LENGTH_SHORT).show();
        }
    }

}
