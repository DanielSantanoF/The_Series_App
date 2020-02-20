package com.dsantano.theseriesapp.data.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.dsantano.theseriesapp.common.Constants;
import com.dsantano.theseriesapp.models.FavoriteSeries;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class FirebaseRepository {

    MutableLiveData<List<FavoriteSeries>> favoriteSeriesList;
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


}
