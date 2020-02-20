package com.dsantano.theseriesapp.models.recomendations;

import com.dsantano.theseriesapp.models.recomendations.NetworkRecomended;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SerieRecomended {
    @SerializedName("backdrop_path")
    @Expose
    public String backdropPath;
    @SerializedName("first_air_date")
    @Expose
    public String firstAirDate;
    @SerializedName("genre_ids")
    @Expose
    public List<Integer> genreIds = null;
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("origin_country")
    @Expose
    public List<String> originCountry = null;
    @SerializedName("original_language")
    @Expose
    public String originalLanguage;
    @SerializedName("original_name")
    @Expose
    public String originalName;
    @SerializedName("overview")
    @Expose
    public String overview;
    @SerializedName("poster_path")
    @Expose
    public String posterPath;
    @SerializedName("vote_average")
    @Expose
    public Double voteAverage;
    @SerializedName("vote_count")
    @Expose
    public Integer voteCount;
    @SerializedName("networks")
    @Expose
    public List<NetworkRecomended> networks = null;
    @SerializedName("popularity")
    @Expose
    public Double popularity;

}
