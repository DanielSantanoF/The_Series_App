package com.dsantano.theseriesapp.retrofit;

import com.dsantano.theseriesapp.models.PopularSeries;
import com.dsantano.theseriesapp.models.PopularsMovies;
import com.dsantano.theseriesapp.models.SerieDetail;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TheMoviedbService {

    @GET("movie/popular")
    Call<PopularsMovies> getPopularsMovies();

    @GET("tv/popular")
    Call<PopularSeries> getPopularsSeries(@Query("page") String page);

    @GET("tv/{tv_id}")
    Call<SerieDetail> getSerieDetail(@Path("tv_id") String id, @Query("append_to_response") String append);

}
