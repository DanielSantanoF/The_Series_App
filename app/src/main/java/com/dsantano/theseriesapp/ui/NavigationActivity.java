package com.dsantano.theseriesapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.dsantano.theseriesapp.R;
import com.dsantano.theseriesapp.common.Constants;
import com.dsantano.theseriesapp.listeners.IFavoriteSeriesListener;
import com.dsantano.theseriesapp.listeners.IPopularsSeriesListener;
import com.dsantano.theseriesapp.listeners.ISerieProgressListener;
import com.dsantano.theseriesapp.models.local.LocalSeries;
import com.dsantano.theseriesapp.models.local.dao.LocalSeriesDao;
import com.dsantano.theseriesapp.models.remote.favorites.FavoriteSeries;
import com.dsantano.theseriesapp.models.remote.populars.Series;
import com.dsantano.theseriesapp.models.remote.serieprogress.SerieProgress;
import com.dsantano.theseriesapp.ui.DetailSerie.DetailSerieScrollingActivity;
import com.dsantano.theseriesapp.ui.auth.LoginActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import static com.dsantano.theseriesapp.data.local.roomdatabase.LocalSeriesRoomDatabase.databaseWriteExecutor;

public class NavigationActivity extends AppCompatActivity implements IPopularsSeriesListener, IFavoriteSeriesListener, ISerieProgressListener {

    FirebaseAuth mAuth;
    GoogleSignInClient mGoogleLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

    @Override
    public void onPopularSeriesItemClick(Series series) {
        Intent i = new Intent(NavigationActivity.this, DetailSerieScrollingActivity.class);
        i.putExtra(Constants.EXTRA_SERIE_ID, String.valueOf(series.getId()));
        startActivity(i);
    }

    @Override
    public void onFavoriteSeriesItemClick(FavoriteSeries favoriteSeries) {
        Intent i = new Intent(NavigationActivity.this, DetailSerieScrollingActivity.class);
        i.putExtra(Constants.EXTRA_SERIE_ID, favoriteSeries.getSerieId());
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuItemLogOut:
                logOut();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void logOut() {
        mAuth = FirebaseAuth.getInstance();

        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions
                .Builder()
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleLogin = GoogleSignIn.getClient(this, googleSignInOptions);
        FirebaseAuth.getInstance().signOut();
        mGoogleLogin.signOut().addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Intent i = new Intent(NavigationActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });

    }

    @Override
    public void onSerieProgressItemClick(SerieProgress serieProgress) {
        Intent i = new Intent(NavigationActivity.this, DetailSerieScrollingActivity.class);
        i.putExtra(Constants.EXTRA_SERIE_ID, serieProgress.getSerieId());
        startActivity(i);
    }
}
