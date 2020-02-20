package com.dsantano.theseriesapp.listeners;

import com.dsantano.theseriesapp.models.populars.Series;

public interface IPopularsSeriesListener {
    void onPopularSeriesItemClick(Series series);
}
