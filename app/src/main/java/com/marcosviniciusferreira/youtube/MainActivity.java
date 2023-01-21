package com.marcosviniciusferreira.youtube;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class MainActivity extends YouTubeBaseActivity
        implements YouTubePlayer.OnInitializedListener {

    private static final String GOOGLE_API_KEY = "";
    private YouTubePlayerView youTubePlayerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        youTubePlayerView = findViewById(R.id.viewYoutubePlayer);


        youTubePlayerView.initialize(GOOGLE_API_KEY, this);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
        Toast.makeText(this, "Sucesso ao iniciar o Player", Toast.LENGTH_SHORT).show();

        //youTubePlayer.loadVideo("qRXkEQOtQ98");

        Log.i("estado_player", "Estado: " + wasRestored);
        if (!wasRestored) {
            youTubePlayer.cueVideo("qRXkEQOtQ98");

        }


    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(this, "Erro ao iniciar o Player", Toast.LENGTH_SHORT).show();
    }
}
