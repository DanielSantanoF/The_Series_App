package com.dsantano.theseriesapp.data.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.dsantano.theseriesapp.data.repository.TmdbSeriesRepository;
import com.dsantano.theseriesapp.models.populars.PopularSeries;

public class SeriePopularsViewModel extends AndroidViewModel {

    private TmdbSeriesRepository tmdbSeriesRepository;
    private MutableLiveData<PopularSeries> allPopulars;

    public SeriePopularsViewModel(@NonNull Application application) {
        super(application);
        tmdbSeriesRepository = new TmdbSeriesRepository();
    }

    public MutableLiveData<PopularSeries> getAllPopulars(){
        allPopulars = tmdbSeriesRepository.getAllPopulars();
        return allPopulars;
    }

}
