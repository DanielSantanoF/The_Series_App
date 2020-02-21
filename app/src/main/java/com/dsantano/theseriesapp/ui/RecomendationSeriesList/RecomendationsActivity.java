package com.dsantano.theseriesapp.ui.RecomendationSeriesList;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.dsantano.theseriesapp.R;
import com.dsantano.theseriesapp.common.Constants;
import com.dsantano.theseriesapp.listeners.IRecomendationsSeriesListener;
import com.dsantano.theseriesapp.models.remote.recomendations.SerieRecomended;
import com.dsantano.theseriesapp.ui.DetailSerie.DetailSerieScrollingActivity;

public class RecomendationsActivity extends AppCompatActivity implements IRecomendationsSeriesListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recomendations);
        setTitle(getResources().getString(R.string.btn_recomendations));
    }

    @Override
    public void onRecomendedSerieClick(SerieRecomended serieRecomended) {
        Intent i = new Intent(RecomendationsActivity.this, DetailSerieScrollingActivity.class);
        i.putExtra(Constants.EXTRA_SERIE_ID, String.valueOf(serieRecomended.getId()));
        startActivity(i);
    }
}
