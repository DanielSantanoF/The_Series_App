package com.dsantano.theseriesapp.listeners;

import com.dsantano.theseriesapp.models.favorites.FavoriteSeries;

public interface IFavoriteSeriesListener {
    void onFavoriteSeriesItemClick(FavoriteSeries favoriteSeries);
}
