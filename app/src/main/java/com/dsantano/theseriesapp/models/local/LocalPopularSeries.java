package com.dsantano.theseriesapp.models.local;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(tableName = "popular_series_table")
public class LocalPopularSeries {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo(name = "page")
    public Integer page;

    @NonNull
    @ColumnInfo(name = "totalResults")
    public Integer totalResults;

    @NonNull
    @ColumnInfo(name = "totalPages")
    public Integer totalPages;

    @NonNull
    @ColumnInfo(name = "results")
    public List<LocalSeries> results = null;
}
