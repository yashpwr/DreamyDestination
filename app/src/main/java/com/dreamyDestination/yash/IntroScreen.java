package com.dreamyDestination.yash;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.codemybrainsout.onboarder.AhoyOnboarderActivity;
import com.codemybrainsout.onboarder.AhoyOnboarderCard;

import java.util.ArrayList;
import java.util.List;

public class IntroScreen extends AhoyOnboarderActivity {

    private Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_intro_screen);

        mContext = this;

        AhoyOnboarderCard ahoyOnboarderCard1 = new AhoyOnboarderCard("Register", "Register Yourself get travel advice at your finger tips", R.drawable.travel);
        AhoyOnboarderCard ahoyOnboarderCard2 = new AhoyOnboarderCard("Ride Bookings", "Book cab and bus rides at sigle place", R.drawable.travel);

        ahoyOnboarderCard1.setBackgroundColor(R.color.white);
        ahoyOnboarderCard2.setBackgroundColor(R.color.white);


        List<AhoyOnboarderCard> pages = new ArrayList<>();

        pages.add(ahoyOnboarderCard1);
        pages.add(ahoyOnboarderCard2);

        for (AhoyOnboarderCard page : pages) {
            page.setTitleColor(R.color.black);
            page.setDescriptionColor(R.color.black);
            //setColorBackground(colorList);
        }

        setFinishButtonTitle("Finish");
        showNavigationControls(true);
        setGradientBackground();

        //set the button style you created
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            setFinishButtonDrawableStyle(ContextCompat.getDrawable(this, R.drawable.rounded_button));
        }

        //Typeface face = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");
        //setFont(face);

        setOnboardPages(pages);

    }

    @Override
    public void onFinishButtonPressed() {

        SharedPreferences preferences = mContext.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("isWalkthroughShown",true);
        editor.commit();
        finish();
        Intent Login = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(Login);
    }

}

