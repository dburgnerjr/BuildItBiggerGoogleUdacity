package com.danielburgnerjr.androidjokelibrary;

import androidx.fragment.app.FragmentActivity;
import android.os.Bundle;

public class DisplayJokesActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_jokes);
    }
}