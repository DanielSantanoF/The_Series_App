package com.dsantano.theseriesapp.ui.PopularSeriesList;

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
import com.dsantano.theseriesapp.listeners.IPopularsSeriesListener;
import com.dsantano.theseriesapp.models.populars.Series;

import java.util.List;

public class MyPopularsSeriesRecyclerViewAdapter extends RecyclerView.Adapter<MyPopularsSeriesRecyclerViewAdapter.ViewHolder> {

    private final Context ctx;
    private List<Series> mValues;
    private final IPopularsSeriesListener mListener;

    public MyPopularsSeriesRecyclerViewAdapter(Context ctx, List<Series> mValues, IPopularsSeriesListener mListener) {
        this.ctx = ctx;
        this.mValues = mValues;
        this.mListener = mListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_popularsseries, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if(mValues != null){
            holder.mItem = mValues.get(position);
            Glide
                    .with(ctx)
                    .load(Constants.POSTER_PATH_URL_W500 + holder.mItem.getPosterPath())
                    .error(Glide.with(ctx).load(R.drawable.image_not_loaded_icon))
                    .thumbnail(Glide.with(ctx).load(R.drawable.loading_gif).transform( new CenterCrop()))
                    .into(holder.ivPosterPath);

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != mListener) {
                        mListener.onPopularSeriesItemClick(holder.mItem);
                    }
                }
            });
        }
    }

    public void setData(List<Series> list){
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
        public Series mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            ivPosterPath = view.findViewById(R.id.imageViewPosterPath);
            mContentView = (TextView) view.findViewById(R.id.content);
        }
    }
}
