package com.dsantano.theseriesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.dsantano.theseriesapp.listeners.IPopularsSeriesListener;
import com.dsantano.theseriesapp.models.Series;

public class MainActivity extends AppCompatActivity implements IPopularsSeriesListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onPopularSeriesItemClick(Series series) {

    }
}
