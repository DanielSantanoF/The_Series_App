package com.dsantano.theseriesapp.ui.DetailSerie;

import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.dsantano.theseriesapp.common.Constants;
import com.dsantano.theseriesapp.data.SerieDetailViewModel;
import com.dsantano.theseriesapp.data.SerieDetailViewModelFactory;
import com.dsantano.theseriesapp.data.SeriesGetAllPopularsViewModel;
import com.dsantano.theseriesapp.models.PopularSeries;
import com.dsantano.theseriesapp.models.SerieDetail;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.dsantano.theseriesapp.R;

import java.lang.reflect.ParameterizedType;

public class DetailSerieScrollingActivity extends AppCompatActivity {

    ImageView ivToolbar, ivDetail;
    TextView txtTittle, txtDescription;
    RatingBar ratingBar;
    String serieId;
    SerieDetailViewModel serieDetailViewModel;
    SerieDetail data;
    Toolbar toolbar;
    CollapsingToolbarLayout toolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_serie_scrolling);
        toolbar = findViewById(R.id.toolbar);
        toolbarLayout = findViewById(R.id.toolbar_layout);
        setSupportActionBar(toolbar);

        serieId = getIntent().getExtras().get("serieId").toString();

        serieDetailViewModel = new ViewModelProvider(DetailSerieScrollingActivity.this).get(SerieDetailViewModel.class);
        serieDetailViewModel.setSerieId(serieId);
        //serieDetailViewModel = ViewModelProviders.of(this, new SerieDetailViewModelFactory(this.getApplication(), serieId)).get(SerieDetailViewModel.class);

        ivToolbar = findViewById(R.id.imageViewDetailSerieToolbar);
        txtTittle = findViewById(R.id.textViewTittleSerieDetail);
        txtDescription = findViewById(R.id.textViewDescriptionSerieDetail);
        ratingBar = findViewById(R.id.ratingBarSerieDetail);
        ivDetail = findViewById(R.id.imageViewSerieDetail);

        loadSerieDetails();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail_serie_scrolling, menu);
        return true;
    }

    public void loadSerieDetails(){
        serieDetailViewModel.getSerieDetail().observe(DetailSerieScrollingActivity.this, new Observer<SerieDetail>() {
            @Override
            public void onChanged(SerieDetail serieDetail) {
                data = serieDetail;
                toolbarLayout.setTitle(serieDetail.getName());
                txtTittle.setText(serieDetail.getName());
                txtDescription.setText(serieDetail.getOverview());
                float rating = serieDetail.getVoteAverage().floatValue();
                ratingBar.setRating(rating);
                Glide
                        .with(DetailSerieScrollingActivity.this)
                        .load(Constants.POSTER_PATH_URL_W500 + serieDetail.backdropPath)
                        .error(Glide.with(DetailSerieScrollingActivity.this).load(R.drawable.image_not_loaded_icon))
                        .thumbnail(Glide.with(DetailSerieScrollingActivity.this).load(R.drawable.loading_gif))
                        .into(ivDetail);
                Glide
                        .with(DetailSerieScrollingActivity.this)
                        .load(Constants.POSTER_PATH_URL_W500 + serieDetail.posterPath)
                        .error(Glide.with(DetailSerieScrollingActivity.this).load(R.drawable.image_not_loaded_icon))
                        .thumbnail(Glide.with(DetailSerieScrollingActivity.this).load(R.drawable.loading_gif))
                        .into(ivToolbar);
            }
        });
    }
}
