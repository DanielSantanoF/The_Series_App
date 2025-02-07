package com.dsantano.theseriesapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.dsantano.theseriesapp.R;
import com.dsantano.theseriesapp.common.Constants;
import com.dsantano.theseriesapp.listeners.IPopularsSeriesListener;
import com.dsantano.theseriesapp.models.remote.populars.Series;
import com.dsantano.theseriesapp.ui.DetailSerie.DetailSerieScrollingActivity;
import com.dsantano.theseriesapp.ui.auth.LoginActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;


public class MainActivity extends AppCompatActivity implements IPopularsSeriesListener {

    FirebaseAuth mAuth;
    GoogleSignInClient mGoogleLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public void onPopularSeriesItemClick(Series series) {
        Intent i = new Intent(MainActivity.this, DetailSerieScrollingActivity.class);
        i.putExtra(Constants.EXTRA_SERIE_ID, String.valueOf(series.getId()));
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
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });

    }
}
