package com.dsantano.theseriesapp.listeners;

import com.dsantano.theseriesapp.models.remote.favorites.FavoriteSeries;

public interface IFavoriteSeriesListener {
    void onFavoriteSeriesItemClick(FavoriteSeries favoriteSeries);
}
