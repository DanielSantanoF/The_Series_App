package com.dsantano.theseriesapp.models.remote.seasondetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeasonDetail {
    @SerializedName("_id")
    @Expose
    public String id;
    @SerializedName("air_date")
    @Expose
    public String airDate;
    @SerializedName("episodes")
    @Expose
    public List<EpisodeSeasonDetail> episodes = null;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("overview")
    @Expose
    public String overview;
    @SerializedName("id")
    @Expose
    public Integer idSeason;
    @SerializedName("poster_path")
    @Expose
    public String posterPath;
    @SerializedName("season_number")
    @Expose
    public Integer seasonNumber;
}
