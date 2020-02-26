package com.dsantano.theseriesapp.listeners;

import com.dsantano.theseriesapp.models.remote.serieprogress.SerieProgress;

public interface ISerieProgressListener {
    void onSerieProgressItemClick(SerieProgress serieProgress);
}
