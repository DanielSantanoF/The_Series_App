package com.dsantano.theseriesapp.data.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.dsantano.theseriesapp.data.repository.TmdbSeriesRepository;
import com.dsantano.theseriesapp.models.recomendations.SerieRecomendations;

public class SerieRecomendationsViewModel extends AndroidViewModel {

    private TmdbSeriesRepository tmdbSeriesRepository;
    private MutableLiveData<SerieRecomendations> serieRecomendations;
    private String serieId;

    public SerieRecomendationsViewModel(@NonNull Application application) {
        super(application);
        tmdbSeriesRepository = new TmdbSeriesRepository();
    }

    public MutableLiveData<SerieRecomendations> getSerieDetail(){
        serieRecomendations = tmdbSeriesRepository.getSerieRecomendations(serieId);
        return serieRecomendations;
    }

    public void setSerieId(String id){
        serieId = id;
    }
}
