package com.dsantano.theseriesapp.listeners;

import com.dsantano.theseriesapp.models.remote.recomendations.SerieRecomended;

public interface IRecomendationsSeriesListener {
    void onRecomendedSerieClick(SerieRecomended serieRecomended);
}
