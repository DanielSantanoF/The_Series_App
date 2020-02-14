package com.dsantano.theseriesapp.data;

import android.app.Application;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class SerieDetailViewModelFactory implements ViewModelProvider.Factory {
    private Application mApplication;
    private String mParam;


    public SerieDetailViewModelFactory(Application application, String param) {
        mApplication = application;
        mParam = param;
    }


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new SerieDetailViewModel(mApplication, mParam);
    }
}