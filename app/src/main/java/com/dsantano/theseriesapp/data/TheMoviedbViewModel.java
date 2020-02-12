package com.dsantano.theseriesapp.data;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.dsantano.theseriesapp.models.PopularSeries;
import com.dsantano.theseriesapp.models.Populars;

public class TheMoviedbViewModel extends AndroidViewModel {

    private TheMoviedbRepository theMoviedbRepository;
    private LiveData<PopularSeries> allPopulars;

    public TheMoviedbViewModel(@NonNull Application application) {
        super(application);
        theMoviedbRepository = new TheMoviedbRepository();
        allPopulars = theMoviedbRepository.getAllPopulars();
    }

    public LiveData<PopularSeries> getAllPopulars(){
        return allPopulars;
    }

}
