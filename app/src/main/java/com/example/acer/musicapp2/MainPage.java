package com.example.acer.musicapp2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainPage extends AppCompatActivity {

    Button bt1;

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                    Home home=new Home();
                    fragmentTransaction.replace(R.id.container, home);
                    fragmentTransaction.commit();


                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_share:
                    FragmentManager fragmentManager2 = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction2 = fragmentManager2.beginTransaction();

                    Share share = new Share();
                    fragmentTransaction2.replace(R.id.container, share);
                    fragmentTransaction2.commit();

                    mTextMessage.setText(R.string.title_share);
                    return true;
                case R.id.navigation_profile:
                    FragmentManager fragmentManager3 = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction3 = fragmentManager3.beginTransaction();

                    Profile profile = new Profile();
                    fragmentTransaction3.replace(R.id.container, profile);
                    fragmentTransaction3.commit();

                    mTextMessage.setText(R.string.title_profile);
                    return true;
            }
            return false;

        }

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MainPage.super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Home home=new Home();
        fragmentTransaction.replace(R.id.container, home);
        fragmentTransaction.commit();

    }


    @Override
    public void onBackPressed() {
        MainPage.super.onBackPressed();

        finishAffinity();
    }

    }

