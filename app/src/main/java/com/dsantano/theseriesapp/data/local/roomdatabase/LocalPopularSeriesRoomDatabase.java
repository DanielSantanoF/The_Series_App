package com.dsantano.theseriesapp.data.local.roomdatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.dsantano.theseriesapp.models.local.LocalPopularSeries;
import com.dsantano.theseriesapp.models.local.dao.LocalPoularSeriesDao;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {LocalPopularSeries.class}, version = 1, exportSchema = false)
public abstract class LocalPopularSeriesRoomDatabase extends RoomDatabase {

    public abstract LocalPoularSeriesDao LocalPoularSeriesDao();

    private static volatile LocalPopularSeriesRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static LocalPopularSeriesRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (LocalPopularSeriesRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            LocalPopularSeriesRoomDatabase.class, "popular_series_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}