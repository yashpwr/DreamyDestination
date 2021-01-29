package com.dreamyDestination.yash;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;
import android.util.Patterns;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private EditText user_full_name,user_email,user_password,user_confirm_password;
    private Context mContext;
    private Button btn_sign_up, btn_login_page;
    private ProgressBar pbLoader;


    private static final String TAG = LoginActivity.class.getSimpleName();
    private static final int RC_SIGN_IN = 007;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mContext = this;
        user_full_name = (EditText) findViewById(R.id.name);
        user_email = (EditText) findViewById(R.id.email);
        user_password = (EditText) findViewById(R.id.password);
        user_confirm_password = (EditText) findViewById(R.id.confirm_password);
        btn_sign_up = (Button) findViewById(R.id.btnRegister);
        btn_login_page = (Button) findViewById(R.id.btnLinkToLoginScreen);
        pbLoader = (ProgressBar) findViewById(R.id.pbLoader);


        btn_login_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Login = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(Login);
            }
        });


        btn_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user_full_name.getText().toString().trim().equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Please Enter Full Name.", Toast.LENGTH_SHORT).show();
                }
                else if (user_email.getText().toString().trim().equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Please Enter Email.", Toast.LENGTH_SHORT).show();
                }
                else if (user_password.getText().toString().trim().equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Please Enter Password.", Toast.LENGTH_SHORT).show();
                }
                else if (user_confirm_password.getText().toString().trim().equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Please Enter Confirm Password.", Toast.LENGTH_SHORT).show();
                }
                else if (!(user_password.getText().toString().trim().equals(user_confirm_password.getText().toString().trim())))
                {
                    Toast.makeText(getApplicationContext(), "Password and Confirm Password does mot match.", Toast.LENGTH_SHORT).show();
                }
                if (isEmail(user_email) == false)
                {
                    Toast.makeText(getApplicationContext(), "Please Enter Valid Email.", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    SignUp();
                }
            }

        });




    }

    boolean isEmail(EditText text) {
        CharSequence email = user_email.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    private void SignUp()
    {

        if (Connectivity.isConnected(mContext)) {

            pbLoader.setVisibility(View.VISIBLE);
            btn_sign_up.setVisibility(View.GONE);


            final String UserType = "VOLANTEER";

            String mURL;
            mURL = ApiFunctions.SignUpURL;

            StringRequest strReq = new StringRequest(Request.Method.POST,
                    mURL, new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {

                    Log.e(TAG, response.toString());

                    pbLoader.setVisibility(View.GONE);
                    btn_sign_up.setVisibility(View.VISIBLE);


                    try {
                        Toast.makeText(getApplicationContext(), "Registration Successful.", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                        finish();



                    } catch (Exception ex) {
                        //btn_login.setVisibility(View.VISIBLE);
                        ex.printStackTrace();
                    }


                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d(TAG, error.toString());
                    btn_sign_up.setVisibility(View.VISIBLE);
                    pbLoader.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "Something Went Wrong.Don't Worry it's not you it's us.", Toast.LENGTH_LONG).show();
                }

            }) {

                @Override
                protected Map<String, String> getParams() {

                    Map<String, String> params = new HashMap<String, String>();
                    Date d = new Date();
                    CharSequence strDate  = DateFormat.format("MMMM d, yyyy ", d.getTime());

                    params.put("user_full_name", user_full_name.getText().toString().trim());
                    params.put("user_password", user_password.getText().toString().trim());
                    params.put("user_email",user_email.getText().toString().trim());
                    params.put("user_joined_date",strDate.toString().trim());
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
