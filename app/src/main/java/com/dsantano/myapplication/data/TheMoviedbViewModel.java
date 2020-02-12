package com.dsantano.myapplication.data;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.dsantano.myapplication.models.Populars;

public class TheMoviedbViewModel extends AndroidViewModel {

    private TheMoviedbRepository theMoviedbRepository;
    private LiveData<Populars> allPopulars;

    public TheMoviedbViewModel(@NonNull Application application) {
        super(application);
        theMoviedbRepository = new TheMoviedbRepository();
        allPopulars = theMoviedbRepository.getAllPopulars();
    }

    public LiveData<Populars> getAllPopulars(){
        return allPopulars;
    }

}
