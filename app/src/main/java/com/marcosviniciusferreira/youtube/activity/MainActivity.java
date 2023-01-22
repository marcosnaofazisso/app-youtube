package com.marcosviniciusferreira.youtube.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.marcosviniciusferreira.youtube.R;
import com.marcosviniciusferreira.youtube.adapter.AdapterVideo;
import com.marcosviniciusferreira.youtube.api.YoutubeService;
import com.marcosviniciusferreira.youtube.helper.RetrofitConfig;
import com.marcosviniciusferreira.youtube.helper.YoutubeConfig;
import com.marcosviniciusferreira.youtube.listener.RecyclerItemClickListener;
import com.marcosviniciusferreira.youtube.model.Item;
import com.marcosviniciusferreira.youtube.model.Resultado;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends YouTubeBaseActivity
        implements YouTubePlayer.OnInitializedListener {

    private RecyclerView recyclerVideos;
    private List<Item> videos = new ArrayList<>();
    private Resultado resultado;
    private AdapterVideo adapterVideo;
    private MaterialSearchView searchView;

    private EditText editPesquisar;
    private ImageView buttonPesquisar;

    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inicializar componentes
        recyclerVideos = findViewById(R.id.recyclerVideos);
        searchView = findViewById(R.id.searchView);
        editPesquisar = findViewById(R.id.editPesquisar);
        buttonPesquisar = findViewById(R.id.buttonPesquisar);

        //Configurar iniciais
        retrofit = RetrofitConfig.getRetrofit();


        //Configurar toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("YouTube");
        toolbar.setTitleTextColor(getResources().getColor(R.color.cinza_medio));

        recuperarVideos("");


        buttonPesquisar.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                String pesquisa = editPesquisar.getText().toString().replaceAll(" ", "+");
                editPesquisar.setText("");
                recuperarVideos(pesquisa);
                dismissKeyboardShortcutsHelper();
            }
        });


        //Configurar métodos para o SearchView
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                recuperarVideos(query);

                return true;
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
                recuperarVideos("");

            }
        });


    }

    private void recuperarVideos(String pesquisa) {

        String q = pesquisa.replaceAll(" ", "+");

        YoutubeService youtubeService = retrofit.create(YoutubeService.class);

        youtubeService.recuperarVideos(
                "snippet",
                "date",
                "20",
                YoutubeConfig.CHAVE_YOUTUBE_API,
                YoutubeConfig.CANAL_ID,
                q
        ).enqueue(new Callback<Resultado>() {
            @Override
            public void onResponse(Call<Resultado> call, Response<Resultado> response) {
                Log.d("resultado", "resultado: " + response.toString());


                if (response.isSuccessful()) {
                    resultado = response.body();
                    videos = resultado.items;

                    configurarRecyclerView();

                    //Gson gson = new GsonBuilder().setPrettyPrinting().create();
                    //String json = gson.toJson(resultado);

                    //Log.d("resultado", "resultado: " + json);
                }
            }

            @Override
            public void onFailure(Call<Resultado> call, Throwable t) {

            }
        });


    }

    public void configurarRecyclerView() {

        //Configurar adapter
        adapterVideo = new AdapterVideo(videos, this);

        //Configurar recylerview
        recyclerVideos.setLayoutManager(new LinearLayoutManager(this));
        recyclerVideos.setHasFixedSize(true);
        recyclerVideos.setAdapter(adapterVideo);

        //Configurar evento de clique do recyclewview
        recyclerVideos.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        this,
                        recyclerVideos,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Item video = videos.get(position);
                                String idVideo = video.id.videoId;

                                Intent i = new Intent(MainActivity.this, PlayerActivity.class);
                                i.putExtra("idVideo", idVideo);
                                startActivity(i);

                            }

                            @Override
                            public void onLongItemClick(View view, int position) {

                            }

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            }
                        }
                ));
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
