package com.dsantano.theseriesapp.ui.FavoriteSeriesList;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dsantano.theseriesapp.R;
import com.dsantano.theseriesapp.listeners.IFavoriteSeriesListener;

import java.util.List;

public class FavoriteSerieFragmentList extends Fragment {

    private int mColumnCount = 2;
    private IFavoriteSeriesListener mListener;
    MyFavoriteSerieRecyclerViewAdapter adapter;
    Context context;

    public FavoriteSerieFragmentList() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_serie_favorite_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            //adapter = new MyFavoriteSerieRecyclerViewAdapter(context, DummyContent.ITEMS, mListener);
            recyclerView.setAdapter(adapter);
        }
        return view;
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

}
