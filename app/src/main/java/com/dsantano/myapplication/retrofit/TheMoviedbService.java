package com.dsantano.myapplication.retrofit;

import com.dsantano.myapplication.models.Populars;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TheMoviedbService {

    @GET("/movie/popular")
    Call<Populars> getPopulars();

}
