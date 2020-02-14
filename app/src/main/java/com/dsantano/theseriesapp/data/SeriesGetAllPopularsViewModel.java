package com.dsantano.theseriesapp.data;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.dsantano.theseriesapp.models.PopularSeries;

public class SeriesGetAllPopularsViewModel extends AndroidViewModel {

    private SeriesGetAllPopularsRepository seriesGetAllPopularsRepository;
    private LiveData<PopularSeries> allPopulars;

    public SeriesGetAllPopularsViewModel(@NonNull Application application) {
        super(application);
        seriesGetAllPopularsRepository = new SeriesGetAllPopularsRepository();
        allPopulars = seriesGetAllPopularsRepository.getAllPopulars();
    }

    public LiveData<PopularSeries> getAllPopulars(){
        return allPopulars;
    }

}
