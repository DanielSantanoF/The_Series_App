package com.dsantano.myapplication;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dsantano.myapplication.data.TheMoviedbViewModel;
import com.dsantano.myapplication.listeners.IPopularsMoviesListener;
import com.dsantano.myapplication.models.Movie;
import com.dsantano.myapplication.models.Populars;

import java.util.ArrayList;
import java.util.List;

public class PopularsMoviesFragmentList extends Fragment {

    private int mColumnCount = 1;
    private IPopularsMoviesListener mListener;
    Context context;
    RecyclerView recyclerView;
    MyPopularsMoviesRecyclerViewAdapter adapter;
    TheMoviedbViewModel theMoviedbViewModel;
    List<Movie> popularMovies = new ArrayList<>();

    public PopularsMoviesFragmentList() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        theMoviedbViewModel = new ViewModelProvider(getActivity()).get(TheMoviedbViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_popularsmovies_list, container, false);

        if (view instanceof RecyclerView) {
            context = view.getContext();
            recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            adapter = new MyPopularsMoviesRecyclerViewAdapter(getActivity(), popularMovies, mListener);
            recyclerView.setAdapter(adapter);
            loadPopularsMovies();
        }
        return view;
    }

    public void loadPopularsMovies(){
        theMoviedbViewModel.getAllPopulars().observe(getActivity(), new Observer<Populars>() {
            @Override
            public void onChanged(Populars populars) {
                popularMovies = populars.results;
                adapter.setData(popularMovies);
            }
        });
    }


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
}
