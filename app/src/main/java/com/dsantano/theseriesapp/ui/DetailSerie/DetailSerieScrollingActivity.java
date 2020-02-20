package com.dsantano.theseriesapp.ui.DetailSerie;

import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.dsantano.theseriesapp.common.Constants;
import com.dsantano.theseriesapp.data.viewmodel.SerieDetailViewModel;
import com.dsantano.theseriesapp.models.detail.SerieDetail;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.dsantano.theseriesapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class DetailSerieScrollingActivity extends AppCompatActivity {

    ImageView ivToolbar, ivDetail, ivAuthor;
    TextView txtTittle, txtDescription, txtCreatedAt, txtCreatedBy;
    RatingBar ratingBar;
    String serieId, uid;
    SerieDetailViewModel serieDetailViewModel;
    SerieDetail serieDetailData;
    Toolbar toolbar;
    CollapsingToolbarLayout toolbarLayout;
    Map<String, Object> serieDetailfb;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    boolean isFavorite = false;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_serie_scrolling);
        toolbar = findViewById(R.id.toolbar);
        toolbarLayout = findViewById(R.id.toolbar_layout);
        setSupportActionBar(toolbar);

        serieId = getIntent().getExtras().get(Constants.EXTRA_SERIE_ID).toString();

        serieDetailViewModel = new ViewModelProvider(DetailSerieScrollingActivity.this).get(SerieDetailViewModel.class);
        serieDetailViewModel.setSerieId(serieId);

        ivToolbar = findViewById(R.id.imageViewDetailSerieToolbar);
        txtTittle = findViewById(R.id.textViewTittleSerieDetail);
        txtDescription = findViewById(R.id.textViewDescriptionSerieDetail);
        ratingBar = findViewById(R.id.ratingBarSerieDetail);
        ivDetail = findViewById(R.id.imageViewSerieDetail);
        ivAuthor = findViewById(R.id.imageViewAuthorDetail);
        txtCreatedAt = findViewById(R.id.textViewCreatedAtDetail);
        txtCreatedBy = findViewById(R.id.textViewAuthorNameDetail);

        uid = FirebaseAuth.getInstance().getUid();

        loadSerieDetails();

        fab = (FloatingActionButton) findViewById(R.id.fabSerieDetail);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isFavorite){
                    db.collection(Constants.FIREBASE_COLLECTION_USERS).document(uid).collection(Constants.FIREBASE_COLLECTION_FAVORITES).document(serieId)
                            .delete()
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d("FB", "DocumentSnapshot successfully deleted!");
                                    fab.setImageResource(R.drawable.ic_favorite_white_24dp);
                                    isFavorite = false;
                                    Toast.makeText(DetailSerieScrollingActivity.this, getResources().getString(R.string.delete_from_favorites_serie_detail), Toast.LENGTH_SHORT).show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w("FB", "Error deleting document", e);
                                }
                            });
                } else {
                    String name = serieDetailData.getName();
                    String posterPath = serieDetailData.getPosterPath();
                    String defaultPhoto = Constants.DEFAULT_SERIE_PHOTO;
                    serieDetailfb = new HashMap<>();
                    serieDetailfb.put("serieId", serieId);
                    if(!posterPath.isEmpty()){
                        serieDetailfb.put("posterPath", posterPath);
                    } else {
                        serieDetailfb.put("posterPath", defaultPhoto);
                    }
                    serieDetailfb.put("serieName", name);
                    DocumentReference docIdRef = db.collection(Constants.FIREBASE_COLLECTION_USERS).document(uid).collection(Constants.FIREBASE_COLLECTION_FAVORITES).document(serieId);
                    docIdRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot document = task.getResult();
                                if (document.exists()) {
                                    Log.d("FB", "Document exists!");
                                    db.collection(Constants.FIREBASE_COLLECTION_USERS)
                                            .document(uid)
                                            .collection("favorites")
                                            .document(serieId)
                                            .update(serieDetailfb);
                                } else {
                                    Log.d("FB", "Document does not exist!");
                                    db.collection(Constants.FIREBASE_COLLECTION_USERS)
                                            .document(uid)
                                            .collection("favorites")
                                            .document(serieId)
                                            .set(serieDetailfb);
                                }
                                isFavorite = true;
                                fab.setImageResource(R.drawable.ic_favorite_red_24dp);
                                Toast.makeText(DetailSerieScrollingActivity.this, getResources().getString(R.string.added_to_favorites_serie_detail), Toast.LENGTH_SHORT).show();
                            } else {
                                Log.d("FB", "Failed with: ", task.getException());
                            }
                        }
                    });
                }
            }
        });
    }

    public void checkIsFavorite(){
        DocumentReference docIdRef = db.collection(Constants.FIREBASE_COLLECTION_USERS).document(uid).collection(Constants.FIREBASE_COLLECTION_FAVORITES).document(serieId);
        docIdRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d("FB", "Document exists!");
                        isFavorite = true;
                        fab.setImageResource(R.drawable.ic_favorite_red_24dp);
                    } else {
                        Log.d("FB", "Document does not exist!");
                        isFavorite = false;
                    }
                } else {
                    Log.d("FB", "Failed with: ", task.getException());
                    isFavorite = false;
                }
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
                checkIsFavorite();
                serieDetailData = serieDetail;
                toolbarLayout.setTitle(serieDetail.getName());
                txtTittle.setText(serieDetail.getName());
                txtDescription.setText(serieDetail.getOverview());
                txtCreatedAt.setText(getResources().getString(R.string.created_at_serie_detail) + " " + serieDetail.getFirstAirDate());
                if(serieDetail.getCreatedBy() == null){
                    txtCreatedBy.setText("");
                    Glide
                            .with(DetailSerieScrollingActivity.this)
                            .load(R.drawable.image_not_loaded_icon)
                            .thumbnail(Glide.with(DetailSerieScrollingActivity.this).load(R.drawable.loading_gif).transform(new CircleCrop()))
                            .transform(new CircleCrop())
                            .into(ivAuthor);
                } else {
                    txtCreatedBy.setText(getResources().getString(R.string.created_by_serie_detail) + " " + serieDetail.getCreatedBy().get(0).getName());
                    Glide
                            .with(DetailSerieScrollingActivity.this)
                            .load(Constants.POSTER_PATH_URL_W500 + serieDetail.createdBy.get(0).profilePath)
                            .error(Glide.with(DetailSerieScrollingActivity.this).load(R.drawable.image_not_loaded_icon).transform(new CircleCrop()))
                            .thumbnail(Glide.with(DetailSerieScrollingActivity.this).load(R.drawable.loading_gif).transform(new CircleCrop()))
                            .transform(new CircleCrop())
                            .into(ivAuthor);
                }
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
