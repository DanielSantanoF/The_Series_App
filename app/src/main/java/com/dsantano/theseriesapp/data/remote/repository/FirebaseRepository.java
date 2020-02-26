package com.dsantano.theseriesapp.data.remote.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.dsantano.theseriesapp.common.Constants;
import com.dsantano.theseriesapp.models.remote.favorites.FavoriteSeries;
import com.dsantano.theseriesapp.models.remote.serieprogress.SerieProgress;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class FirebaseRepository {

    MutableLiveData<List<FavoriteSeries>> favoriteSeriesList;
    MutableLiveData<List<SerieProgress>> serieProgressList;
    FirebaseFirestore db;
    String uid;

    public FirebaseRepository() {
        db = FirebaseFirestore.getInstance();
        uid = FirebaseAuth.getInstance().getUid();
    }

    public MutableLiveData<List<FavoriteSeries>> getAllFavorites(){
        final MutableLiveData<List<FavoriteSeries>> data = new MutableLiveData<>();
        db.collection(Constants.FIREBASE_COLLECTION_USERS).document(uid).collection(Constants.FIREBASE_COLLECTION_FAVORITES)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            data.setValue(task.getResult().toObjects(FavoriteSeries.class));
                            favoriteSeriesList = data;
                        } else {
                            Log.w("FB", "Error getting documents.", task.getException());
                        }
                    }
                });
        return data;
    }

    public MutableLiveData<List<SerieProgress>> getSeriesProgress(){
        final MutableLiveData<List<SerieProgress>> data = new MutableLiveData<>();
        db.collection(Constants.FIREBASE_COLLECTION_USERS).document(uid).collection(Constants.FIREBASE_COLLECTION_SERIES_PROGRESS)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            data.setValue(task.getResult().toObjects(SerieProgress.class));
                            serieProgressList = data;
                        } else {
                            Log.w("FB", "Error getting documents.", task.getException());
                        }
                    }
                });
        return data;
    }

}
