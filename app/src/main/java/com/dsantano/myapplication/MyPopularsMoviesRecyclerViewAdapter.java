package com.dsantano.myapplication;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dsantano.myapplication.listeners.IPopularsMoviesListener;
import com.dsantano.myapplication.models.Movie;

import java.util.List;

public class MyPopularsMoviesRecyclerViewAdapter extends RecyclerView.Adapter<MyPopularsMoviesRecyclerViewAdapter.ViewHolder> {

    private final Context ctx;
    private List<Movie> mValues;
    private final IPopularsMoviesListener mListener;

    public MyPopularsMoviesRecyclerViewAdapter(Context ctx, List<Movie> mValues, IPopularsMoviesListener mListener) {
        this.ctx = ctx;
        this.mValues = mValues;
        this.mListener = mListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_popularsmovies, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if(mValues != null){
            holder.mItem = mValues.get(position);
            holder.txtTittle.setText(holder.mItem.getTitle());
            Glide
                    .with(ctx)
                    .load("https://image.tmdb.org/t/p/w500/" + holder.mItem.getPosterPath())
                    .into(holder.ivPosterPath);

            //https://image.tmdb.org/t/p/w500/
            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != mListener) {
                        mListener.onPopularMoviesItemClick(holder.mItem);
                    }
                }
            });
        }
    }

    public void setData(List<Movie> list){
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
        public final TextView txtTittle;
        public final ImageView ivPosterPath;
        public final TextView mContentView;
        public Movie mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            txtTittle = view.findViewById(R.id.textViewTittle);
            ivPosterPath = view.findViewById(R.id.imageViewPosterPath);
            mContentView = (TextView) view.findViewById(R.id.content);
        }
    }
}
