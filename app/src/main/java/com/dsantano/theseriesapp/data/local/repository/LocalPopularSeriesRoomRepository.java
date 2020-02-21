package com.dsantano.theseriesapp.data.local.repository;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.dsantano.theseriesapp.data.local.roomdatabase.LocalPopularSeriesRoomDatabase;
import com.dsantano.theseriesapp.models.local.LocalPopularSeries;
import com.dsantano.theseriesapp.models.local.dao.LocalPoularSeriesDao;

import java.util.List;

public class LocalPopularSeriesRoomRepository {

    private LocalPoularSeriesDao localPoularSeriesDao;
    private MutableLiveData<List<LocalPopularSeries>> mAllPopulars;

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    LocalPopularSeriesRoomRepository(Application application) {
        LocalPopularSeriesRoomDatabase db = LocalPopularSeriesRoomDatabase.getDatabase(application);
        localPoularSeriesDao = db.LocalPoularSeriesDao();
        mAllPopulars = localPoularSeriesDao.getAllPopularsAsc();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    MutableLiveData<List<LocalPopularSeries>> getAllPopulars() {
        return mAllPopulars;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    void insert(final LocalPopularSeries localPopularSeries) {
        LocalPopularSeriesRoomDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                //https://codelabs.developers.google.com/codelabs/android-room-with-a-view/#8
                localPoularSeriesDao.insert(localPopularSeries);
            }
        });
    }
}
