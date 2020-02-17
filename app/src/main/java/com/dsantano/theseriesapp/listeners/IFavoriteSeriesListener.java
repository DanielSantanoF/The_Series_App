package com.dsantano.theseriesapp.listeners;

import com.dsantano.theseriesapp.models.Series;

public interface IFavoriteSeriesListener {
    void onFavoriteSeriesItemClick(Series series);
}
