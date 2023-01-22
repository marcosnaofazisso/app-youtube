package com.marcosviniciusferreira.youtube.api;

import com.marcosviniciusferreira.youtube.model.Resultado;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface YoutubeService {

    /*
     * Exemplo de Query
     *
     * https://www.googleapis.com/youtube/v3/
     * search
     * ?part=snippet
     * &order=date
     * &maxResults=20
     * &key=GOOGLE_API_KEY
     * &channelId=CHANNEL_ID
     *
     * */


    //Poderíamos fazer com parâmetros mas dessa vez vamos montar uma QUERY
    @GET("search")
    Call<Resultado> recuperarVideos(@Query("part") String part,
                                    @Query("order") String order,
                                    @Query("maxResults") String maxResults,
                                    @Query("key") String key,
                                    @Query("channelId") String channelId,
                                    @Query("q") String q
    );


}
