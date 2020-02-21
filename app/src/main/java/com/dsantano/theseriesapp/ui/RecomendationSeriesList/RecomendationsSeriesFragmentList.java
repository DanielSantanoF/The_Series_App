package com.dsantano.theseriesapp.ui.RecomendationSeriesList;

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
import com.dsantano.theseriesapp.data.remote.viewmodel.SerieRecomendationsViewModel;
import com.dsantano.theseriesapp.listeners.IRecomendationsSeriesListener;
import com.dsantano.theseriesapp.models.remote.recomendations.SerieRecomendations;
import com.dsantano.theseriesapp.models.remote.recomendations.SerieRecomended;

import java.util.ArrayList;
import java.util.List;

public class RecomendationsSeriesFragmentList extends Fragment {

    private int mColumnCount = 2;
    private IRecomendationsSeriesListener mListener;
    Context context;
    RecyclerView recyclerView;
    MyRecomendationsSeriesRecyclerViewAdapter adapter;
    SerieRecomendationsViewModel serieRecomendationsViewModel;
    List<SerieRecomended> serieRecomendedList = new ArrayList<>();

    public RecomendationsSeriesFragmentList() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        serieRecomendationsViewModel = new ViewModelProvider(getActivity()).get(SerieRecomendationsViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_series_recomendations_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            context = view.getContext();
            recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            adapter = new MyRecomendationsSeriesRecyclerViewAdapter(getActivity(), serieRecomendedList, mListener);
            recyclerView.setAdapter(adapter);
            loadRecomendedSeries();
        }
        return view;
    }

    public void loadRecomendedSeries(){
        serieRecomendationsViewModel.getSeriesRecomended().observe(getActivity(), new Observer<SerieRecomendations>() {
            @Override
            public void onChanged(SerieRecomendations serieRecomendations) {
                serieRecomendedList = serieRecomendations.results;
                adapter.setData(serieRecomendedList);
            }
        });
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof IRecomendationsSeriesListener) {
            mListener = (IRecomendationsSeriesListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement IRecomendationsSeriesListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

}
