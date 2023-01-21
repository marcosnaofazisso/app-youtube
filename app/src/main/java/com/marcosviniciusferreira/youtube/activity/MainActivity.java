package com.marcosviniciusferreira.youtube.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.marcosviniciusferreira.youtube.R;
import com.marcosviniciusferreira.youtube.adapter.AdapterVideo;
import com.marcosviniciusferreira.youtube.model.Video;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String GOOGLE_API_KEY = "";

    private RecyclerView recyclerVideos;
    private List<Video> videos = new ArrayList<>();
    private AdapterVideo adapterVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inicializar componentes
        recyclerVideos = findViewById(R.id.recyclerVideos);

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

}
