package com.gmail.mtec.sistemas.soccernews.data;

import com.gmail.mtec.sistemas.soccernews.domain.News;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SoccerNewsApi {

    @GET("news.json")
    Call<List<News>> getNewsApi();
}
