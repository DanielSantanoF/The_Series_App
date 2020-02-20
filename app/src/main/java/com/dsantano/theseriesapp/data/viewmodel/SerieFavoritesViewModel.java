package com.dsantano.theseriesapp.data.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.dsantano.theseriesapp.data.repository.FirebaseRepository;
import com.dsantano.theseriesapp.models.favorites.FavoriteSeries;

import java.util.List;

public class SerieFavoritesViewModel extends AndroidViewModel {

    private FirebaseRepository firebaseRepository;
    private MutableLiveData<List<FavoriteSeries>> favoriteSeriesList;

    public SerieFavoritesViewModel(@NonNull Application application) {
        super(application);
        firebaseRepository = new FirebaseRepository();
    }

    public MutableLiveData<List<FavoriteSeries>> getAllFavorites(){
        favoriteSeriesList = firebaseRepository.getAllFavorites();
        return favoriteSeriesList;
    }

}
