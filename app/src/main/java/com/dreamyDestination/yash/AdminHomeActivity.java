package com.dreamyDestination.yash;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.dreamyDestination.yash.util.BottomNavigationBehavior;

public class AdminHomeActivity extends AppCompatActivity implements AdminManagePlaces.OnFragmentInteractionListener, AdminRegisteredUserFragment.OnFragmentInteractionListener,AdminAddTravelTipsFragment.OnFragmentInteractionListener,AdminFeedbackFragment.OnFragmentInteractionListener,AdminProfileFragment.OnFragmentInteractionListener {

    private Button btn_calculateBMI;
    private EditText user_age;
    private EditText user_weight;
    private EditText user_height;

    int age, feet, height;
    double weight, new_weight, bmi;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        // attaching bottom sheet behaviour - hide / show on scroll
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) navigation.getLayoutParams();
        layoutParams.setBehavior(new BottomNavigationBehavior());

        // load the store fragment by default
        loadFragment(new AdminManagePlaces());
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_studnet_list:
                    fragment = new AdminManagePlaces();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_teacher_faqs:
                    fragment = new AdminRegisteredUserFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_add_travel_tips:
                    fragment = new AdminAddTravelTipsFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_feedback:
                    fragment = new AdminFeedbackFragment();
                    loadFragment(fragment);
                    return true;

                case R.id.navigation_profile:
                    fragment = new AdminProfileFragment();
                    loadFragment(fragment);
                    return true;
            }

            return false;
        }
    };

    /**
     * loading fragment into FrameLayout
     *
     * @param fragment
     */
    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

}


