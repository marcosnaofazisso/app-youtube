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
    private YouTubePlayer.PlaybackEventListener playbackEventListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        youTubePlayerView = findViewById(R.id.viewYoutubePlayer);

        youTubePlayerView.initialize(GOOGLE_API_KEY, this);

        playbackEventListener = new YouTubePlayer.PlaybackEventListener() {
            @Override
            public void onPlaying() {
                Toast.makeText(MainActivity.this,
                        "Vídeo executando",
                        Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onPaused() {
                Toast.makeText(MainActivity.this,
                        "Vídeo pausado",
                        Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onStopped() {
                Toast.makeText(MainActivity.this,
                        "Vídeo parado",
                        Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onBuffering(boolean b) {
                Toast.makeText(MainActivity.this,
                        "Vídeo pré carregando...",
                        Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onSeekTo(int i) {
                Toast.makeText(MainActivity.this,
                        "Movimentando o seekbar",
                        Toast.LENGTH_SHORT).show();

            }
        };
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
        Toast.makeText(this, "Sucesso ao iniciar o Player", Toast.LENGTH_SHORT).show();

        //youTubePlayer.loadVideo("qRXkEQOtQ98");

        Log.i("estado_player", "Estado: " + wasRestored);

        String videoId = "qRXkEQOtQ98";
        String playlistId = "PLWz5rJ2EKKc8vv9y8Z9LPjJJjLA0r4_GV";

        //Passamos o event listener como parâmetro para o método que configura o objeto
        youTubePlayer.setPlaybackEventListener(playbackEventListener);

        if (!wasRestored) {
            //youTubePlayer.cueVideo("videoId");
            youTubePlayer.cuePlaylist(playlistId);

        }


    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(this,
                "Erro ao iniciar o Player! ERROR: "
                        + youTubeInitializationResult.toString(),
                Toast.LENGTH_SHORT).show();
    }
}
