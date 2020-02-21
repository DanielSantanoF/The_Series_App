package com.dsantano.theseriesapp.data.local.roomdatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.dsantano.theseriesapp.models.local.LocalSeries;
import com.dsantano.theseriesapp.models.local.dao.LocalSeriesDao;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {LocalSeries.class}, version = 1, exportSchema = false)
public abstract  class LocalSeriesRoomDatabase extends RoomDatabase {

    public abstract LocalSeriesDao LocalSeriesDao();

    private static volatile LocalSeriesRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static LocalSeriesRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (LocalSeriesRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            LocalSeriesRoomDatabase.class, "popular_result_list_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
