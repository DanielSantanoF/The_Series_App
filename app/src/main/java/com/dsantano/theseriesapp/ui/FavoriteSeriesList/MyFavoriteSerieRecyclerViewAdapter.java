package com.dsantano.theseriesapp.ui.FavoriteSeriesList;

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
import com.dsantano.theseriesapp.listeners.IFavoriteSeriesListener;
import com.dsantano.theseriesapp.models.favorites.FavoriteSeries;

import java.util.List;

public class MyFavoriteSerieRecyclerViewAdapter extends RecyclerView.Adapter<MyFavoriteSerieRecyclerViewAdapter.ViewHolder> {

    private final Context ctx;
    private List<FavoriteSeries> mValues;
    private final IFavoriteSeriesListener mListener;

    public MyFavoriteSerieRecyclerViewAdapter(Context ctx, List<FavoriteSeries> mValues, IFavoriteSeriesListener mListener) {
        this.ctx = ctx;
        this.mValues = mValues;
        this.mListener = mListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_serie_favorite, parent, false);
        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if(mValues != null) {
            holder.mItem = mValues.get(position);
            Glide
                    .with(ctx)
                    .load(Constants.POSTER_PATH_URL_W500 + holder.mItem.getPosterPath())
                    .error(Glide.with(ctx).load(R.drawable.image_not_loaded_icon))
                    .thumbnail(Glide.with(ctx).load(R.drawable.loading_gif).transform(new CenterCrop()))
                    .into(holder.ivPoster);

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != mListener) {
                        mListener.onFavoriteSeriesItemClick(holder.mItem);
                    }
                }
            });
        }
    }

    public void setData(List<FavoriteSeries> list){
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
        public final ImageView ivPoster;
        public final TextView mContentView;
        public FavoriteSeries mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            ivPoster = view.findViewById(R.id.imageViewPosterPath);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

    }

}
