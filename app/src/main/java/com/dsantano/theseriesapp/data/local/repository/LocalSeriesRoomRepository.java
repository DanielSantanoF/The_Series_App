package com.dsantano.theseriesapp.data.local.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.dsantano.theseriesapp.data.local.roomdatabase.LocalSeriesRoomDatabase;
import com.dsantano.theseriesapp.models.local.LocalSeries;
import com.dsantano.theseriesapp.models.local.dao.LocalSeriesDao;

import java.util.List;

public class LocalSeriesRoomRepository {

    private LocalSeriesDao localSeriesDao;
    private LiveData<List<LocalSeries>> mAllPopulars;

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    public LocalSeriesRoomRepository(Application application) {
        LocalSeriesRoomDatabase db = LocalSeriesRoomDatabase.getDatabase(application);
        localSeriesDao = db.LocalSeriesDao();
        mAllPopulars = localSeriesDao.getAllPopularsSeriesAsc();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<LocalSeries>> getAllPopularSeries() {
        return mAllPopulars;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    public void insert(final LocalSeries localSeries) {
        LocalSeriesRoomDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                //https://codelabs.developers.google.com/codelabs/android-room-with-a-view/#8
                localSeriesDao.insert(localSeries);
            }
        });
    }
}
