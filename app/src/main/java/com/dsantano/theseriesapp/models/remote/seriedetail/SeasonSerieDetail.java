package com.dsantano.theseriesapp.models.remote.seriedetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeasonSerieDetail {
    @SerializedName("air_date")
    @Expose
    public String airDate;
    @SerializedName("episode_count")
    @Expose
    public Integer episodeCount;
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("overview")
    @Expose
    public String overview;
    @SerializedName("poster_path")
    @Expose
    public String posterPath;
    @SerializedName("season_number")
    @Expose
    public Integer seasonNumber;
}
