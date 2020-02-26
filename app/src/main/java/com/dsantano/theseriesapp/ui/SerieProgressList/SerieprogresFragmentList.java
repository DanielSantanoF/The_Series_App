package com.dsantano.theseriesapp.ui.SerieProgressList;

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
import com.dsantano.theseriesapp.data.remote.viewmodel.SerieProgressViewModel;
import com.dsantano.theseriesapp.listeners.ISerieProgressListener;
import com.dsantano.theseriesapp.models.remote.serieprogress.SerieProgress;

import java.util.ArrayList;
import java.util.List;

public class SerieprogresFragmentList extends Fragment {

    private int mColumnCount = 1;
    private ISerieProgressListener mListener;
    MySerieprogresRecyclerViewAdapter adapter;
    Context context;
    RecyclerView recyclerView;
    List<SerieProgress> serieProgressList = new ArrayList<>();
    SerieProgressViewModel serieProgressViewModel;

    public SerieprogresFragmentList() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        serieProgressViewModel = new ViewModelProvider(getActivity()).get(SerieProgressViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_serieprogres_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            context = view.getContext();
            recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            adapter = new MySerieprogresRecyclerViewAdapter(context, serieProgressList ,mListener);
            recyclerView.setAdapter(adapter);
            loadSerieProgress();
        }
        return view;
    }

    public void loadSerieProgress(){
        serieProgressViewModel.getSerieProgress().observe(getActivity(), new Observer<List<SerieProgress>>() {
            @Override
            public void onChanged(List<SerieProgress> serieProgresses) {
                serieProgressList = serieProgresses;
                adapter.setData(serieProgressList);
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ISerieProgressListener) {
            mListener = (ISerieProgressListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement ISerieProgressListener");
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
        loadSerieProgress();
        recyclerView.setVisibility(View.VISIBLE);
    }

}
