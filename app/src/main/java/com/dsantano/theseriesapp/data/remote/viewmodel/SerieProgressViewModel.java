package com.dsantano.theseriesapp.data.remote.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.dsantano.theseriesapp.data.remote.repository.FirebaseRepository;
import com.dsantano.theseriesapp.models.remote.serieprogress.SerieProgress;

import java.util.List;

public class SerieProgressViewModel extends AndroidViewModel {

    private FirebaseRepository firebaseRepository;
    private MutableLiveData<List<SerieProgress>> serieProgressList;

    public SerieProgressViewModel(@NonNull Application application) {
        super(application);
        firebaseRepository = new FirebaseRepository();
    }

    public MutableLiveData<List<SerieProgress>> getSerieProgress(){
        serieProgressList = firebaseRepository.getSeriesProgress();
        return serieProgressList;
    }

}
