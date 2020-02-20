package com.dsantano.theseriesapp.models.detail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LastEpisodeToAirSerieDetail {
    @SerializedName("air_date")
    @Expose
    public String airDate;
    @SerializedName("episode_number")
    @Expose
    public Integer episodeNumber;
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("overview")
    @Expose
    public String overview;
    @SerializedName("production_code")
    @Expose
    public String productionCode;
    @SerializedName("season_number")
    @Expose
    public Integer seasonNumber;
    @SerializedName("show_id")
    @Expose
    public Integer showId;
    @SerializedName("still_path")
    @Expose
    public String stillPath;
    @SerializedName("vote_average")
    @Expose
    public Integer voteAverage;
    @SerializedName("vote_count")
    @Expose
    public Integer voteCount;
}
