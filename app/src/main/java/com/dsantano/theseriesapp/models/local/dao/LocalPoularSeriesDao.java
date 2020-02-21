package com.dsantano.theseriesapp.models.local.dao;

import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.dsantano.theseriesapp.models.local.LocalPopularSeries;

import java.util.List;

@Dao
public interface LocalPoularSeriesDao {

    // allowing the insert of the same populars multiple times by passing conflict resolution strategy
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(LocalPopularSeries localPopularSeries);

    @Query("DELETE FROM popular_series_table")
    void deleteAll();

    @Query("SELECT * from popular_series_table ORDER BY id ASC")
    MutableLiveData<List<LocalPopularSeries>> getAllPopularsAsc();

}
