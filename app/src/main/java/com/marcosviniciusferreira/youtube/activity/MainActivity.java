package com.marcosviniciusferreira.youtube.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.marcosviniciusferreira.youtube.R;
import com.marcosviniciusferreira.youtube.adapter.AdapterVideo;
import com.marcosviniciusferreira.youtube.model.Video;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends YouTubeBaseActivity
        implements YouTubePlayer.OnInitializedListener {

    private static final String GOOGLE_API_KEY = "";

    private RecyclerView recyclerVideos;
    private List<Video> videos = new ArrayList<>();
    private AdapterVideo adapterVideo;
    private MaterialSearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inicializar componentes
        recyclerVideos = findViewById(R.id.recyclerVideos);
        searchView = findViewById(R.id.searchView);

        //Configurar toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("YouTube");

        recuperarVideos();

        //Configurar adapter
        adapterVideo = new AdapterVideo(videos, this);

        //Configurar recylerview
        recyclerVideos.setLayoutManager(new LinearLayoutManager(this));
        recyclerVideos.setHasFixedSize(true);
        recyclerVideos.setAdapter(adapterVideo);

        //Configurar métodos para o SearchView
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {

            }

            @Override
            public void onSearchViewClosed() {

            }
        });


    }

    private void recuperarVideos() {
        Video video1 = new Video();
        video1.setTitulo("Video 1 muito interessante!");
        videos.add(video1);

        Video video2 = new Video();
        video2.setTitulo("Video 2 muito legal!");
        videos.add(video2);

        Video video3 = new Video();
        video3.setTitulo("Video 3 muito top!");
        videos.add(video3);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        MenuItem item = menu.findItem(R.id.menu_search);
        searchView.setMenuItem(item);
        return true;
    }


    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }
}
