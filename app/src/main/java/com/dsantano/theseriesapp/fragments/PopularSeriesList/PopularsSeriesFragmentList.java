package com.dsantano.theseriesapp.fragments.PopularSeriesList;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dsantano.theseriesapp.R;
import com.dsantano.theseriesapp.data.TheMoviedbViewModel;
import com.dsantano.theseriesapp.listeners.IPopularsMoviesListener;
import com.dsantano.theseriesapp.models.PopularSeries;
import com.dsantano.theseriesapp.models.Series;
import com.dsantano.theseriesapp.retrofit.ServiceGenerator;
import com.dsantano.theseriesapp.retrofit.TheMoviedbService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class PopularsSeriesFragmentList extends Fragment {

    private int mColumnCount = 2;
    private IPopularsMoviesListener mListener;
    Context context;
    RecyclerView recyclerView;
    MyPopularsSeriesRecyclerViewAdapter adapter;
    TheMoviedbViewModel theMoviedbViewModel;
    List<Series> popularSeries = new ArrayList<>();
    TheMoviedbService service;

    public PopularsSeriesFragmentList() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //theMoviedbViewModel = new ViewModelProvider(getActivity()).get(TheMoviedbViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_popularsseries_list, container, false);

        if (view instanceof RecyclerView) {
            context = view.getContext();
            recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
//            adapter = new MyPopularsSeriesRecyclerViewAdapter(getActivity(), popularSeries, mListener);
//            recyclerView.setAdapter(adapter);
            //loadPopularsMovies();
            service = ServiceGenerator.createService(TheMoviedbService.class);
            new DowloadPopularSeries().execute();
        }
        return view;
    }

//    public void loadPopularsMovies(){
//        theMoviedbViewModel.getAllPopulars().observe(getActivity(), new Observer<Populars>() {
//            @Override
//            public void onChanged(Populars populars) {
//                popularMovies = populars.results;
//                adapter.setData(popularMovies);
//            }
//        });
//    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof IPopularsMoviesListener) {
            mListener = (IPopularsMoviesListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement IPopularsMoviesListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public class DowloadPopularSeries extends AsyncTask<Void, Void, List<Series>>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<Series> doInBackground(Void... voids) {
            Call<PopularSeries> callPopularSeries =  service.getPopularsSeries();
            Response<PopularSeries> responsePopularSeries = null;
            try {
                responsePopularSeries = callPopularSeries.execute();
                return responsePopularSeries.body().results;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<Series> series) {
            popularSeries.addAll(series);
            adapter = new MyPopularsSeriesRecyclerViewAdapter(getActivity(), popularSeries, mListener);
            recyclerView.setAdapter(adapter);
        }
    }
}
