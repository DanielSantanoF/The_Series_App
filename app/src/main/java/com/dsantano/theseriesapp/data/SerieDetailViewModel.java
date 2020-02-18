package com.dsantano.theseriesapp.data;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.dsantano.theseriesapp.models.SerieDetail;

public class SerieDetailViewModel extends AndroidViewModel {

    private SerieDetailRepository serieDetailRepository;
    private LiveData<SerieDetail> serieDetail;
    private String serieId;

    public SerieDetailViewModel(@NonNull Application application) {
        super(application);
        serieDetailRepository = new SerieDetailRepository();
    }

    public LiveData<SerieDetail> getSerieDetail(){
        return serieDetailRepository.getSerieDetail(serieId);
    }

    public void setSerieId(String id){
        serieId = id;
    }
}
