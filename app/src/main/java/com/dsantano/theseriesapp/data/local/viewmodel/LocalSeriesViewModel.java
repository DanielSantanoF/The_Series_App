package com.dsantano.theseriesapp.data.local.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.dsantano.theseriesapp.data.local.repository.LocalSeriesRoomRepository;
import com.dsantano.theseriesapp.models.local.LocalSeries;

import java.util.List;

public class LocalSeriesViewModel extends AndroidViewModel {

    private LocalSeriesRoomRepository localSeriesRoomRepository;
    private LiveData<List<LocalSeries>> mAllSeries;

    public LocalSeriesViewModel (Application application) {
        super(application);
        localSeriesRoomRepository = new LocalSeriesRoomRepository(application);
        mAllSeries = localSeriesRoomRepository.getAllPopularSeries();
    }

    public LiveData<List<LocalSeries>> getAllLocalPopularSeries() { return mAllSeries; }

    public void insert(LocalSeries localSeries) { localSeriesRoomRepository.insert(localSeries); }
}