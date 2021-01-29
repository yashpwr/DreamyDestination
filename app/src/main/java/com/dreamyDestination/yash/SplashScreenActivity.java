package com.dreamyDestination.yash;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SplashScreenActivity extends AppCompatActivity {

    public static final int POST_DELYED_TIME = 2000;

    private Context context;

    ImageView imageViewSplash;
    TextView txtAppName;
    RelativeLayout relativeLayout;
    Thread SplashThread;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        imageViewSplash = (ImageView) findViewById(R.id.imageViewSplash);
        txtAppName = (TextView) findViewById(R.id.txtAppName);
        relativeLayout = (RelativeLayout) findViewById(R.id.relative);

        startAnimations();

        context = this;
        Handler mHandler = new Handler();

//        mHandler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//
//                SharedPreferences shared = getSharedPreferences("MyPreferences", MODE_PRIVATE);
//                String loginCategory = shared.getString("loginCategory", "");
//                Boolean isLoggedIn = shared.getBoolean("isloggedIn", false);
//                Boolean isWalkThroughShown = shared.getBoolean("isWalkthroughShown", false);
//                if (isWalkThroughShown.equals(false)) {
//                    Intent i = new Intent(context.getApplicationContext(), IntroScreen.class);
//                    context.startActivity(i);
//                } else {
//                    if (isLoggedIn.equals(true)) {
//                        finish();
//                        Intent i = new Intent(context.getApplicationContext(), MainActivity.class);
//                        context.startActivity(i);
//                    } else {
//                        finish();
//                        Intent i = new Intent(context.getApplicationContext(), LoginActivity.class);
//                        context.startActivity(i);
//                    }
//
//
//                }
//
//            }
//        }, 1400);
    }


    private void startAnimations() {

        Animation rotate = AnimationUtils.loadAnimation(this, R.anim.rotate);
        Animation translate = AnimationUtils.loadAnimation(this, R.anim.translate);

        rotate.reset();
        translate.reset();
        relativeLayout.clearAnimation();

        imageViewSplash.startAnimation(rotate);
        txtAppName.startAnimation(translate);
        SplashThread = new Thread(){
            @Override
            public void run() {
                super.run();
                int waited = 0;
                while (waited < 3500) {
                    try {
                        sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    waited += 100;
                }
                //SplashScreenActivity.this.finish();
                SharedPreferences shared = getSharedPreferences("MyPreferences", MODE_PRIVATE);
                String loginCategory = shared.getString("loginCategory", "");
                Boolean isLoggedIn = shared.getBoolean("isLoggedIn", false);
                Boolean isWalkThroughShown = shared.getBoolean("isWalkthroughShown", false);
                if (isWalkThroughShown.equals(false)) {
                    Intent i = new Intent(context.getApplicationContext(), IntroScreen.class);
                    context.startActivity(i);
                } else {
                    if (isLoggedIn.equals(true)) {
                        finish();
                        Intent i = new Intent(context.getApplicationContext(), MainActivity.class);
                        context.startActivity(i);
                    } else {
                        finish();
                        Intent i = new Intent(context.getApplicationContext(), LoginActivity.class);
                        context.startActivity(i);
                    }


                }
            }
        };
        SplashThread.start();
    }


}
