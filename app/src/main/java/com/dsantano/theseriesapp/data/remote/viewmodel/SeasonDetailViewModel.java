package com.dsantano.theseriesapp.data.remote.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.dsantano.theseriesapp.data.remote.repository.TmdbSeriesRepository;
import com.dsantano.theseriesapp.models.remote.seasondetail.SeasonDetail;

public class SeasonDetailViewModel extends AndroidViewModel {

    private TmdbSeriesRepository tmdbSeriesRepository;
    private MutableLiveData<SeasonDetail> seasonDetail;
    private String serieId;
    private String seasonId;

    public SeasonDetailViewModel(@NonNull Application application) {
        super(application);
        tmdbSeriesRepository = new TmdbSeriesRepository();
    }

    public LiveData<SeasonDetail> getSeasonDetail(){
        seasonDetail = tmdbSeriesRepository.getSeasonDetails(serieId, seasonId);
        return seasonDetail;
    }

    public void setSerieId(String id){
        serieId = id;
    }

    public void setSeasonId(String id){
        seasonId = id;
    }
}
