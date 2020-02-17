package com.dsantano.theseriesapp.data;

import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.dsantano.theseriesapp.common.MyApp;
import com.dsantano.theseriesapp.models.SerieDetail;
import com.dsantano.theseriesapp.retrofit.ServiceGenerator;
import com.dsantano.theseriesapp.retrofit.TheMoviedbService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SerieDetailRepository {

    TheMoviedbService service;
    ServiceGenerator serviceGenerator;

    LiveData<SerieDetail> serieDetail;

    String serieId;

    SerieDetailRepository(/*String idSerie*/){
        service = serviceGenerator.createService(TheMoviedbService.class);
        //serieDetail = getSerieDetail();
        //serieId = idSerie;
    }

    public LiveData<SerieDetail> getSerieDetail(String idSerie){
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
                } else {
                    Toast.makeText(MyApp.getContext(), "Error on the response from the Api", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SerieDetail> call, Throwable t) {
                //Toast.makeText(MyApp.getContext(), "Error in the connection", Toast.LENGTH_SHORT).show();
            }
        });
        serieDetail = data;
        return data;
    }
}
