package com.dsantano.theseriesapp.models.local;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity(tableName = "popular_result_list_table")
public class LocalSeries {

    @NonNull
    @ColumnInfo(name = "originalName")
    public String originalName;

    @NonNull
    @ColumnInfo(name = "name")
    public String name;

    @NonNull
    @ColumnInfo(name = "popularity")
    public Double popularity;

    @NonNull
    @ColumnInfo(name = "voteCount")
    public Integer voteCount;

    @NonNull
    @ColumnInfo(name = "firstAirDate")
    public String firstAirDate;

    @NonNull
    @ColumnInfo(name = "backdropPath")
    public String backdropPath;

    @NonNull
    @ColumnInfo(name = "originalLanguage")
    public String originalLanguage;

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    public Integer id;

    @NonNull
    @ColumnInfo(name = "voteAverage")
    public Double voteAverage;

    @NonNull
    @ColumnInfo(name = "overview")
    public String overview;

    @NonNull
    @ColumnInfo(name = "posterPath")
    public String posterPath;

}
