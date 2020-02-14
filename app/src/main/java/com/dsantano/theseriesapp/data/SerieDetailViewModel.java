package com.dsantano.theseriesapp.data;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.dsantano.theseriesapp.models.SerieDetail;

public class SerieDetailViewModel extends AndroidViewModel {

    private SerieDetailRepository serieDetailRepository;
    private LiveData<SerieDetail> serieDetail;

    public SerieDetailViewModel(@NonNull Application application, String idSerie) {
        super(application);
        serieDetailRepository = new SerieDetailRepository(idSerie);
        serieDetail = serieDetailRepository.getSerieDetail();
    }

    public LiveData<SerieDetail> getSerieDetail(){
        return serieDetail;
    }
}
