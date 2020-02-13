package com.dsantano.theseriesapp.data;

import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.dsantano.theseriesapp.common.MyApp;
import com.dsantano.theseriesapp.models.PopularSeries;
import com.dsantano.theseriesapp.retrofit.ServiceGenerator;
import com.dsantano.theseriesapp.retrofit.TheMoviedbService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TheMoviedbRepository {

    TheMoviedbService service;
    ServiceGenerator serviceGenerator;

    LiveData<PopularSeries> allPopulars;

    TheMoviedbRepository(){
        service = serviceGenerator.createService(TheMoviedbService.class);
        allPopulars = getAllPopulars();
    }

    public LiveData<PopularSeries> getAllPopulars(){
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
        return data;
    }
}
