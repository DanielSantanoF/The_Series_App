package com.dsantano.theseriesapp.data.remote.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.dsantano.theseriesapp.common.Constants;
import com.dsantano.theseriesapp.common.MyApp;
import com.dsantano.theseriesapp.data.remote.repository.TmdbSeriesRepository;
import com.dsantano.theseriesapp.models.remote.recomendations.SerieRecomendations;

public class SerieRecomendationsViewModel extends AndroidViewModel {

    private TmdbSeriesRepository tmdbSeriesRepository;
    private MutableLiveData<SerieRecomendations> serieRecomendations;
    private String serieId;

    public SerieRecomendationsViewModel(@NonNull Application application) {
        super(application);
        tmdbSeriesRepository = new TmdbSeriesRepository();
    }

    public MutableLiveData<SerieRecomendations> getSeriesRecomended(){
        String idSerie = MyApp.getContext().getSharedPreferences(Constants.APP_SETTINGS_SHARED_PREFERENCES, Context.MODE_PRIVATE).getString(Constants.SERIE_RECOMENDATIONS_SHARED_PREFERENCES, null);
        serieRecomendations = tmdbSeriesRepository.getSerieRecomendations(idSerie);
        return serieRecomendations;
    }

    public void setSerieId(String id){
        serieId = id;
    }
}
