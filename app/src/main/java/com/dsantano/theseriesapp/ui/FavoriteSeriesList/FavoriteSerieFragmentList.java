package com.dsantano.theseriesapp.ui.FavoriteSeriesList;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dsantano.theseriesapp.R;
import com.dsantano.theseriesapp.listeners.IFavoriteSeriesListener;
import com.dsantano.theseriesapp.models.FavoriteSeries;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class FavoriteSerieFragmentList extends Fragment {

    private int mColumnCount = 2;
    private IFavoriteSeriesListener mListener;
    MyFavoriteSerieRecyclerViewAdapter adapter;
    Context context;
    RecyclerView recyclerView;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String uid;
    List<FavoriteSeries> favoriteSeriesList = new ArrayList<>();
    SwipeRefreshLayout swipeRefreshLayout;

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

        if (view instanceof RecyclerView) {
            context = view.getContext();
            recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            loadData();
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayoutFavoriteSeries);
//        //swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
//        //swipeRefreshLayout.setProgressBackgroundColorSchemeResource(R.color.colorAccent);
//        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                loadData();
//            }
//        });
    }

    public void loadData(){
        uid = FirebaseAuth.getInstance().getUid();
        db.collection("users").document(uid).collection("favorites")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            favoriteSeriesList = task.getResult().toObjects(FavoriteSeries.class);
                            adapter = new MyFavoriteSerieRecyclerViewAdapter(context, favoriteSeriesList, mListener);
                            recyclerView.setAdapter(adapter);
                        } else {
                            Log.w("FB", "Error getting documents.", task.getException());
                        }
                    }
                });
        //swipeRefreshLayout.setRefreshing(false);
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
