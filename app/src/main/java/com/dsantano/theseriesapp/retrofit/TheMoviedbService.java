package com.dsantano.theseriesapp.retrofit;

import com.dsantano.theseriesapp.models.PopularSeries;
import com.dsantano.theseriesapp.models.Populars;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TheMoviedbService {

    @GET("movie/popular")
    Call<Populars> getPopularsMovies();

    @GET("tv/popular")
    Call<PopularSeries> getPopularsSeries(@Query("page") String page);

}
