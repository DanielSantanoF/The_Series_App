package com.dsantano.theseriesapp.models.local.dao;

import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.dsantano.theseriesapp.models.local.LocalSeries;

import java.util.List;

@Dao
public interface LocalSeriesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(LocalSeries localSeries);

    @Query("DELETE FROM popular_result_list_table")
    void deleteAll();

    @Query("SELECT * from popular_result_list_table ORDER BY id ASC")
    MutableLiveData<List<LocalSeries>> getAllPopularsSeriesAsc();
}
