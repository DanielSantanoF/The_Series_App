package com.dsantano.theseriesapp.ui.PopularSeriesList;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dsantano.theseriesapp.R;
import com.dsantano.theseriesapp.data.SeriesGetAllPopularsViewModel;
import com.dsantano.theseriesapp.listeners.IPopularsSeriesListener;
import com.dsantano.theseriesapp.models.PopularSeries;
import com.dsantano.theseriesapp.models.Series;

import java.util.ArrayList;
import java.util.List;

public class PopularsSeriesFragmentList extends Fragment {

    private int mColumnCount = 2;
    private IPopularsSeriesListener mListener;
    Context context;
    RecyclerView recyclerView;
    MyPopularsSeriesRecyclerViewAdapter adapter;
    SeriesGetAllPopularsViewModel seriesGetAllPopularsViewModel;
    List<Series> popularSeriesList = new ArrayList<>();

    public PopularsSeriesFragmentList() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        seriesGetAllPopularsViewModel = new ViewModelProvider(getActivity()).get(SeriesGetAllPopularsViewModel.class);
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
            adapter = new MyPopularsSeriesRecyclerViewAdapter(getActivity(), popularSeriesList, mListener);
            recyclerView.setAdapter(adapter);
            loadPopularsMovies();
        }
        return view;
    }

    public void loadPopularsMovies(){
        seriesGetAllPopularsViewModel.getAllPopulars().observe(getActivity(), new Observer<PopularSeries>() {
            @Override
            public void onChanged(PopularSeries popularSeries) {
                popularSeriesList = popularSeries.results;
                adapter.setData(popularSeriesList);
            }
        });
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof IPopularsSeriesListener) {
            mListener = (IPopularsSeriesListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement IPopularsSeriesListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
