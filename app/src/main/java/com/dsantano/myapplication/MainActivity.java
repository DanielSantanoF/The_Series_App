package com.dsantano.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.dsantano.myapplication.listeners.IPopularsMoviesListener;
import com.dsantano.myapplication.models.Movie;

public class MainActivity extends AppCompatActivity implements IPopularsMoviesListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onPopularMoviesItemClick(Movie movie) {

    }
}
