package com.dsantano.theseriesapp.models.populars;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Series {
    @SerializedName("original_name")
    @Expose
    public String originalName;
    @SerializedName("genre_ids")
    @Expose
    public List<Integer> genreIds = null;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("popularity")
    @Expose
    public Double popularity;
    @SerializedName("origin_country")
    @Expose
    public List<String> originCountry = null;
    @SerializedName("vote_count")
    @Expose
    public Integer voteCount;
    @SerializedName("first_air_date")
    @Expose
    public String firstAirDate;
    @SerializedName("backdrop_path")
    @Expose
    public String backdropPath;
    @SerializedName("original_language")
    @Expose
    public String originalLanguage;
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("vote_average")
    @Expose
    public Double voteAverage;
    @SerializedName("overview")
    @Expose
    public String overview;
    @SerializedName("poster_path")
    @Expose
    public String posterPath;
}
