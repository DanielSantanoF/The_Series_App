package com.dsantano.theseriesapp.retrofit;

import com.dsantano.theseriesapp.models.PopularSeries;
import com.dsantano.theseriesapp.models.PopularsMovies;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TheMoviedbService {

    @GET("movie/popular")
    Call<PopularsMovies> getPopularsMovies();

    @GET("tv/popular")
    Call<PopularSeries> getPopularsSeries(@Query("page") String page);

}
