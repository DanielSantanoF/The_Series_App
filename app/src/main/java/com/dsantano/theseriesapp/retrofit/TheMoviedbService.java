package com.dsantano.theseriesapp.retrofit;

import com.dsantano.theseriesapp.models.PopularSeries;
import com.dsantano.theseriesapp.models.Populars;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TheMoviedbService {

    @GET("movie/popular")
    Call<Populars> getPopularsMovies();

    @GET("tv/popular")
    Call<PopularSeries> getPopularsSeries();

}
