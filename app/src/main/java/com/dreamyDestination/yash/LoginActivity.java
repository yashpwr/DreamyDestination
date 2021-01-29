package com.dreamyDestination.yash;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.dreamyDestination.yash.View.CatchScrollLayout;
import com.dreamyDestination.yash.View.SignUpContainer;
import com.dreamyDestination.yash.api.ApiFunctions;
import com.dreamyDestination.yash.db.SQLiteFetcher;
import com.dreamyDestination.yash.entity.ELUserInfo;
import com.dreamyDestination.yash.util.AppController;
import com.dreamyDestination.yash.util.Connectivity;
import com.dreamyDestination.yash.util.JSONParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();
    private static final int RC_SIGN_IN = 007;
    private CatchScrollLayout mCatchScrollLayout;
    private SignUpContainer mSignUpContainer;

    private EditText user_email;
    private EditText user_password;
    private Button btn_login;
    private Button btn_signUp;
    private Context mContext;
    private String SelectedCategory;
    private ProgressBar pbLoader;
    private Button btn_forgot_password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mContext = this;
        SelectedCategory = "";

        user_email = (EditText) findViewById(R.id.user_email);
        user_password = (EditText) findViewById(R.id.user_password);
        btn_login = (Button) findViewById(R.id.btnLogin);
        btn_signUp = (Button) findViewById(R.id.btnLinkToRegisterScreen);
        btn_forgot_password = (Button) findViewById(R.id.btn_forgot_password);

        pbLoader = (ProgressBar) findViewById(R.id.pbLoader) ;

        btn_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Login = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(Login);
            }
        });

        btn_forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Login = new Intent(getApplicationContext(), ForgotPassword.class);
                startActivity(Login);
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((user_email.getText().toString().trim().equals("admin@gmail.com")) && (user_password.getText().toString().equals("123"))) {
                    Intent Login = new Intent(getApplicationContext(), AdminHomeActivity.class);
                    startActivity(Login);

                } else {

                    if (user_email.getText().toString().trim().equals(""))
                    {
                        Toast.makeText(getApplicationContext(), "Please Enter Valid Email.", Toast.LENGTH_SHORT).show();

                    }
                    else if (user_password.getText().toString().trim().equals(""))
                    {
                        Toast.makeText(getApplicationContext(), "Please Enter Valid Password.", Toast.LENGTH_SHORT).show();

                    }
                    else if (isEmail(user_email) == false)
                    {
                        Toast.makeText(getApplicationContext(), "Please Enter Valid Email.", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        LoginApi();
                    }
                }

            }
        });

    }

    boolean isEmail(EditText text) {
        CharSequence email = user_email.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    private void LoginApi()
    {

        if (Connectivity.isConnected(mContext)) {

            pbLoader.setVisibility(View.VISIBLE);
            btn_login.setVisibility(View.GONE);

            //final String UserType = "VOLANTEER";

            String mURL;
            mURL = ApiFunctions.UserLoginURL;

            StringRequest strReq = new StringRequest(Request.Method.POST,
                    mURL, new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {

                    Log.e(TAG, response.toString());

                    //pbLoader.setVisibility(View.GONE);
                    pbLoader.setVisibility(View.GONE);
                    btn_login.setVisibility(View.VISIBLE);


                    try {

                        JSONObject jsonobj = new JSONObject(response.toString());

                        if (jsonobj.has("error")) {

                            boolean error = jsonobj.getBoolean("error");

                            if (!error) {

                                ELUserInfo userInfo = JSONParser.parseStudentData(response.toString());

                                SQLiteFetcher fetcher = new SQLiteFetcher(mContext);
                                fetcher.deleteUserInfo();

                                long result = fetcher.InsertUser(userInfo);

                                if (result > 0) {
                                    Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                                    SharedPreferences preferences = mContext.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = preferences.edit();
                                    editor.putString("full_name", userInfo.getFullName());
                                    editor.putString("user_email_id", userInfo.getEmail());
                                    editor.putString("joined_date", userInfo.getJoined_date());
                                    editor.putString("user_password", userInfo.getUser_password());
                                    editor.putString("user_unique_id", userInfo.getUser_unique_id());
                                    editor.putBoolean("isLoggedIn",true);
                                    editor.commit();
                                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                    finish();

                                    }

                                    //startActivity(new Intent(LoginActivity.this, HomeTabActivity.class));
                                } else {
                                    if (jsonobj.has("message")) {
                                        btn_login.setVisibility(View.VISIBLE);
                                        pbLoader.setVisibility(View.GONE);
                                        String ErrorDesc = jsonobj.getString("message");
                                        Toast.makeText(getApplicationContext(), ErrorDesc, Toast.LENGTH_SHORT).show();

                                    }
                                }


                            } else {

                                if (jsonobj.has("message")) {
                                    pbLoader.setVisibility(View.GONE);
                                    btn_login.setVisibility(View.VISIBLE);
                                    String ErrorDesc = jsonobj.getString("message");
                                    Toast.makeText(getApplicationContext(), ErrorDesc, Toast.LENGTH_SHORT).show();

                                }

                            }

                        } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d(TAG, error.toString());
                    //pbLoader.setVisibility(View.GONE);
                    pbLoader.setVisibility(View.GONE);
                    btn_login.setVisibility(View.VISIBLE);

                    Toast.makeText(getApplicationContext(), "Something Went Wrong. Don't Worry it's not you its us.", Toast.LENGTH_SHORT).show();
                    //Utils.displayDialog(mContext, getString(R.string.common_error_title), getString(R.string.common_error_desc));
                }

            }) {

                @Override
                protected Map<String, String> getParams() {

                    Map<String, String> params = new HashMap<String, String>();
                    params.put("user_email", user_email.getText().toString().trim());
                    params.put("user_password", user_password.getText().toString().trim());
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