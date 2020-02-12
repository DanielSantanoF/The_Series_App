package com.dsantano.theseriesapp.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.dsantano.theseriesapp.models.Populars;
import com.dsantano.theseriesapp.retrofit.ServiceGenerator;
import com.dsantano.theseriesapp.retrofit.TheMoviedbService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TheMoviedbRepository {

    TheMoviedbService service;
    ServiceGenerator serviceGenerator;

    LiveData<Populars> allPopulars;

    TheMoviedbRepository(){
        service = serviceGenerator.createService(TheMoviedbService.class);
        allPopulars = getAllPopulars();
    }

    public LiveData<Populars> getAllPopulars(){
        final MutableLiveData<Populars> data = new MutableLiveData<>();

        Call<Populars> call = service.getPopularsMovies();
        call.enqueue(new Callback<Populars>() {
            @Override
            public void onResponse(Call<Populars> call, Response<Populars> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                } else {
                    //Toast.makeText(MyApp.getContext(), "Error al realizar la petición", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Populars> call, Throwable t) {
                //Toast.makeText(MyApp.getContext(), "Error en la conexión", Toast.LENGTH_SHORT).show();
            }
        });
        return data;
    }
}
