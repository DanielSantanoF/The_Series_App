package com.dsantano.theseriesapp.data.repository;

import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.dsantano.theseriesapp.common.MyApp;
import com.dsantano.theseriesapp.models.PopularSeries;
import com.dsantano.theseriesapp.models.SerieDetail;
import com.dsantano.theseriesapp.retrofit.ServiceGenerator;
import com.dsantano.theseriesapp.retrofit.TheMoviedbService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TmdbSeriesRepository {

    TheMoviedbService service;
    ServiceGenerator serviceGenerator;
    MutableLiveData<PopularSeries> allPopulars;
    MutableLiveData<SerieDetail> serieDetail;

    public TmdbSeriesRepository() {
        service = serviceGenerator.createService(TheMoviedbService.class);
    }

    public MutableLiveData<PopularSeries> getAllPopulars() {
        final MutableLiveData<PopularSeries> data = new MutableLiveData<>();

        Call<PopularSeries> call = service.getPopularsSeries("1");
        call.enqueue(new Callback<PopularSeries>() {
            @Override
            public void onResponse(Call<PopularSeries> call, Response<PopularSeries> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                } else {
                    Toast.makeText(MyApp.getContext(), "Error on the response from the Api", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PopularSeries> call, Throwable t) {
                Toast.makeText(MyApp.getContext(), "Error in the connection", Toast.LENGTH_SHORT).show();
            }
        });
        allPopulars = data;
        return data;
    }

    public MutableLiveData<SerieDetail> getSerieDetail(String idSerie){
        final MutableLiveData<SerieDetail> data = new MutableLiveData<>();

        Call<SerieDetail> call = service.getSerieDetail(idSerie, "1");
        call.enqueue(new Callback<SerieDetail>() {
            @Override
            public void onResponse(Call<SerieDetail> call, Response<SerieDetail> response) {
                if (response.isSuccessful()) {
                    if(response.body().createdBy.isEmpty()){
                        response.body().createdBy = null;
                    }
                    data.setValue(response.body());
                    serieDetail = data;
                } else {
                    Toast.makeText(MyApp.getContext(), "Error on the response from the Api", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SerieDetail> call, Throwable t) {
                //Toast.makeText(MyApp.getContext(), "Error in the connection", Toast.LENGTH_SHORT).show();
            }
        });
        return data;
    }

}


