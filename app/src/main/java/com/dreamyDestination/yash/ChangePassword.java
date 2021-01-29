package com.dreamyDestination.yash;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class ChangePassword extends AppCompatActivity {

    private EditText old_password,new_password,confirm_password;
    private Context mContext;
    private Button btn_confirm_password_submit;

    private ProgressBar pbLoader;

    private static final String TAG = LoginActivity.class.getSimpleName();
    private static final int RC_SIGN_IN = 007;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        mContext = this;
        old_password = (EditText) findViewById(R.id.user_old_password);
        new_password = (EditText) findViewById(R.id.user_new_password);
        confirm_password = (EditText) findViewById(R.id.user_confirm_password);
        btn_confirm_password_submit = (Button) findViewById(R.id.btn_confirm_password_submit);
        pbLoader = (ProgressBar) findViewById(R.id.pbLoader);

        SharedPreferences preferences = mContext.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        final String user_password = preferences.getString("user_password","");
        btn_confirm_password_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!user_password.equals(old_password.getText().toString().trim()))
                {
                    Toast.makeText(getApplicationContext(), "Please Enter Valid Old Password.", Toast.LENGTH_SHORT).show();
                }
                else if (old_password.getText().toString().trim().equals(""))
                {

                }else if (new_password.getText().toString().trim().equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Please Enter New Password.", Toast.LENGTH_SHORT).show();
                }
                else if (confirm_password.getText().toString().trim().equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Please Confirm Password again.", Toast.LENGTH_SHORT).show();
                }
                else if (!new_password.getText().toString().trim().equals(confirm_password.getText().toString().trim()))
                {
                    final String newPass = new_password.getText().toString().trim();
                    final String confirmpass = confirm_password.getText().toString().trim();
                    Toast.makeText(getApplicationContext(), "Old Password and New Password Does not match.", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    updatePassword();
                    //Toast.makeText(getApplicationContext(), "Please Enter Valid Email.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void updatePassword()
    {

        if (Connectivity.isConnected(mContext)) {

            pbLoader.setVisibility(View.VISIBLE);
            btn_confirm_password_submit.setVisibility(View.GONE);


            final String UserType = "VOLANTEER";

            String mURL;
            mURL = ApiFunctions.updatePassword;

            StringRequest strReq = new StringRequest(Request.Method.POST,
                    mURL, new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {

                    Log.e(TAG, response.toString());

                    pbLoader.setVisibility(View.GONE);
                    btn_confirm_password_submit.setVisibility(View.VISIBLE);


                    try {

                        if (response.toString().contains("Records inserted successfully"))
                        {
                            Toast.makeText(getApplicationContext(), "Password Updated Successful.", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(ChangePassword.this, LoginActivity.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(i);
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();
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
                    btn_confirm_password_submit.setVisibility(View.VISIBLE);
                    pbLoader.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "Something Went Wrong.Don't Worry it's not you it's us.", Toast.LENGTH_LONG).show();
                }

            }) {

                @Override
                protected Map<String, String> getParams() {

                    Map<String, String> params = new HashMap<String, String>();
                    SharedPreferences preferences = mContext.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
                    final  String user_id = preferences.getString("user_unique_id","");
                    params.put("user_unique_id", user_id);
                    params.put("user_password", new_password.getText().toString().trim());
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
