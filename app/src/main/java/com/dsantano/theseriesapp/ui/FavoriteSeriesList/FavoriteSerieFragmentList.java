package com.dsantano.theseriesapp.ui.FavoriteSeriesList;

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
import com.dsantano.theseriesapp.data.viewmodel.SerieFavoritesViewModel;
import com.dsantano.theseriesapp.listeners.IFavoriteSeriesListener;
import com.dsantano.theseriesapp.models.favorites.FavoriteSeries;

import java.util.ArrayList;
import java.util.List;

public class FavoriteSerieFragmentList extends Fragment {

    private int mColumnCount = 2;
    private IFavoriteSeriesListener mListener;
    MyFavoriteSerieRecyclerViewAdapter adapter;
    Context context;
    RecyclerView recyclerView;
    List<FavoriteSeries> favoriteSeriesList = new ArrayList<>();
    SerieFavoritesViewModel serieFavoritesViewModel;

    public FavoriteSerieFragmentList() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        serieFavoritesViewModel = new ViewModelProvider(getActivity()).get(SerieFavoritesViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_serie_favorite_list, container, false);

        if (view instanceof RecyclerView) {
            context = view.getContext();
            recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            adapter = new MyFavoriteSerieRecyclerViewAdapter(context, favoriteSeriesList, mListener);
            recyclerView.setAdapter(adapter);
            loadFavoritesSeries();
        }
        return view;
    }

    public void loadFavoritesSeries(){
        serieFavoritesViewModel.getAllFavorites().observe(getActivity(), new Observer<List<FavoriteSeries>>() {
            @Override
            public void onChanged(List<FavoriteSeries> favoriteSeries) {
                favoriteSeriesList = favoriteSeries;
                adapter.setData(favoriteSeriesList);
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof IFavoriteSeriesListener) {
            mListener = (IFavoriteSeriesListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement IFavoriteSeriesListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        recyclerView.setVisibility(View.GONE);
        loadFavoritesSeries();
        recyclerView.setVisibility(View.VISIBLE);
    }
}
