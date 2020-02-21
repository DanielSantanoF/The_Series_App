package com.dsantano.theseriesapp.data.remote.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.dsantano.theseriesapp.data.remote.repository.TmdbSeriesRepository;
import com.dsantano.theseriesapp.models.remote.detail.SerieDetail;

public class SerieDetailViewModel extends AndroidViewModel {

    private TmdbSeriesRepository tmdbSeriesRepository;
    private MutableLiveData<SerieDetail> serieDetail;
    private String serieId;

    public SerieDetailViewModel(@NonNull Application application) {
        super(application);
        tmdbSeriesRepository = new TmdbSeriesRepository();
    }

    public LiveData<SerieDetail> getSerieDetail(){
        serieDetail = tmdbSeriesRepository.getSerieDetail(serieId);
        return serieDetail;
    }

    public void setSerieId(String id){
        serieId = id;
    }
}
