package com.dsantano.theseriesapp.ui.SerieProgressList;

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
import com.dsantano.theseriesapp.listeners.ISerieProgressListener;
import com.dsantano.theseriesapp.models.remote.serieprogress.SerieProgress;

import java.util.List;

public class MySerieprogresRecyclerViewAdapter extends RecyclerView.Adapter<MySerieprogresRecyclerViewAdapter.ViewHolder> {

    private final Context ctx;
    private List<SerieProgress> mValues;
    private final ISerieProgressListener mListener;

    public MySerieprogresRecyclerViewAdapter(Context ctx, List<SerieProgress> mValues, ISerieProgressListener mListener) {
        this.ctx = ctx;
        this.mValues = mValues;
        this.mListener = mListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_serieprogres, parent, false);
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
            holder.txtName.setText(holder.mItem.getSerieName());
            holder.txtSeason.setText(holder.mItem.getSeason());
            holder.txtEpisode.setText(holder.mItem.getEpisode());
            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != mListener) {
                        mListener.onSerieProgressItemClick(holder.mItem);
                    }
                }
            });
        }
    }

    public void setData(List<SerieProgress> list){
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
        public final TextView txtName;
        public final TextView txtSeason;
        public final TextView txtEpisode;
        public SerieProgress mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            ivPoster = view.findViewById(R.id.imageViewSerieProgress);
            txtName = view.findViewById(R.id.textViewTitleSerieProgress);
            txtSeason = view.findViewById(R.id.textViewSeasonSerieProgress);
            txtEpisode = view.findViewById(R.id.textViewEpisodeSerieProgress);
        }

    }
}
