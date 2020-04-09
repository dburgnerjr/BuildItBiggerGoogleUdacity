package com.danielburgnerjr.builditbiggergoogleudacity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.doubleclick.PublisherInterstitialAd;
import com.danielburgnerjr.androidjokelibrary.DisplayJokesActivity;
import com.danielburgnerjr.androidjokelibrary.DisplayJokesFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    //required empty constructor
    public MainActivityFragment() {
    }

    private ProgressBar progressBar = null;
    String loadedJoke = null;

    private PublisherInterstitialAd interstitialAd = null;
    private final String LOG_TAG = MainActivityFragment.class.getSimpleName();

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        AdView mAdView = root.findViewById(R.id.adView);

        interstitialAd = new PublisherInterstitialAd(Objects.requireNonNull(getContext()));
        interstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));

        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                //process the joke Request
                progressBar.setVisibility(View.VISIBLE);
                tellJoke();

                //pre-fetch the next ad
                requestNewInterstitial();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                super.onAdFailedToLoad(errorCode);

                Log.i(LOG_TAG, "Failing");

                //prefetch the next ad
                requestNewInterstitial();

            }

            @Override
            public void onAdLoaded() {
                Log.i(LOG_TAG, "Loading");
                super.onAdLoaded();
            }
        });

        requestNewInterstitial();


        Button button = root.findViewById(R.id.joke_button);
        button.setOnClickListener(v -> {
            if (interstitialAd.isLoaded()) {
                interstitialAd.show();
            } else {
                progressBar.setVisibility(View.VISIBLE);
                tellJoke();
            }
        });

        progressBar = root.findViewById(R.id.joke_progressbar);
        progressBar.setVisibility(View.GONE);

        List<String> testDevices = new ArrayList<>();
        testDevices.add(AdRequest.DEVICE_ID_EMULATOR);

        RequestConfiguration requestConfiguration = new RequestConfiguration.Builder()
                .setTestDeviceIds(testDevices)
                .build();
        MobileAds.setRequestConfiguration(requestConfiguration);

        mAdView.loadAd(new AdRequest.Builder().build());
        return root;
    }

    protected void tellJoke(){
        new AsyncJokeTask().execute(this);
    }

    void launchDisplayJokeActivity(){
        Context context = getActivity();
        Intent intent = new Intent(context, DisplayJokesActivity.class);
        assert context != null;
        intent.putExtra(DisplayJokesFragment.EXTRA_JOKE, loadedJoke);
        context.startActivity(intent);
        progressBar.setVisibility(View.GONE);
    }

    private void requestNewInterstitial() {
        List<String> testDevices = new ArrayList<>();
        testDevices.add(AdRequest.DEVICE_ID_EMULATOR);

        RequestConfiguration requestConfiguration = new RequestConfiguration.Builder()
                .setTestDeviceIds(testDevices)
                .build();
        MobileAds.setRequestConfiguration(requestConfiguration);

        interstitialAd.loadAd(new PublisherAdRequest.Builder().build());
    }
}
