package com.dsantano.theseriesapp.retrofit;

import com.dsantano.theseriesapp.models.remote.populars.PopularSeries;
import com.dsantano.theseriesapp.models.remote.detail.SerieDetail;
import com.dsantano.theseriesapp.models.remote.recomendations.SerieRecomendations;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TheMoviedbService {

    @GET("tv/popular")
    Call<PopularSeries> getPopularsSeries(@Query("page") String page);

    @GET("tv/{tv_id}")
    Call<SerieDetail> getSerieDetail(@Path("tv_id") String id, @Query("append_to_response") String append);

    @GET("tv/{tv_id}/recommendations")
    Call<SerieRecomendations> getSerieRecomendations(@Path("tv_id") String id);

}
