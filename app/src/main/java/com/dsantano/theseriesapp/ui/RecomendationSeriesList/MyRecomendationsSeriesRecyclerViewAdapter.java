package com.dsantano.theseriesapp.ui.RecomendationSeriesList;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.dsantano.theseriesapp.R;
import com.dsantano.theseriesapp.common.Constants;
import com.dsantano.theseriesapp.listeners.IRecomendationsSeriesListener;
import com.dsantano.theseriesapp.models.remote.recomendations.SerieRecomended;

import java.util.List;

public class MyRecomendationsSeriesRecyclerViewAdapter extends RecyclerView.Adapter<MyRecomendationsSeriesRecyclerViewAdapter.ViewHolder> {

    private final Context context;
    private List<SerieRecomended> mValues;
    private final IRecomendationsSeriesListener mListener;

    public MyRecomendationsSeriesRecyclerViewAdapter(Context context, List<SerieRecomended> mValues, IRecomendationsSeriesListener mListener) {
        this.context = context;
        this.mValues = mValues;
        this.mListener = mListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_series_recomendations, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if(mValues != null) {
            holder.mItem = mValues.get(position);
            Glide
                    .with(context)
                    .load(Constants.POSTER_PATH_URL_W500 + holder.mItem.getPosterPath())
                    .error(Glide.with(context).load(R.drawable.image_not_loaded_icon))
                    .thumbnail(Glide.with(context).load(R.drawable.loading_gif).transform( new CenterCrop()))
                    .into(holder.ivPosterPath);

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != mListener) {
                        mListener.onRecomendedSerieClick(holder.mItem);
                    }
                }
            });
        }
    }

    public void setData(List<SerieRecomended> list){
        this.mValues = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(mValues != null){
            return mValues.size();
        } else {
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView ivPosterPath;
        public final TextView mContentView;
        public SerieRecomended mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            ivPosterPath = view.findViewById(R.id.imageViewPosterPath);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

    }
}
