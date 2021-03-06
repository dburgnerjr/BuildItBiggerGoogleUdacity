package com.danielburgnerjr.builditbiggergoogleudacity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.danielburgnerjr.androidjokelibrary.DisplayJokesActivity;
import com.danielburgnerjr.androidjokelibrary.DisplayJokesFragment;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    private ProgressBar progressBar = null;
    String loadedJoke = null;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        // Set onClickListener for the button
        Button button = root.findViewById(R.id.joke_button);
        button.setOnClickListener(v -> {
            progressBar.setVisibility(View.VISIBLE);
            tellJoke();
        });

        progressBar = root.findViewById(R.id.joke_progressbar);
        progressBar.setVisibility(View.GONE);

        return root;
    }
    public void tellJoke() {
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
}
