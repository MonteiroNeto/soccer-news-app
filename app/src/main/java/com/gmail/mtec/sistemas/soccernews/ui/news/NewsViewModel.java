package com.gmail.mtec.sistemas.soccernews.ui.news;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gmail.mtec.sistemas.soccernews.data.SoccerNewsApi;
import com.gmail.mtec.sistemas.soccernews.domain.News;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsViewModel extends ViewModel {

    private final MutableLiveData<List<News>> mNews = new MutableLiveData<>();

    private final SoccerNewsApi serviceApi;

    public NewsViewModel() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://monteironeto.github.io/soccer-news-api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        serviceApi = retrofit.create(SoccerNewsApi.class);

        this.setupService();

    }

    private void setupService() {
        serviceApi.getNewsApi().enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                if (response.isSuccessful()){
                    mNews.setValue(response.body());
                }else{
                    //TODO criar metodo para tratar o errro
                }
            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {
                //TODO criar metodo para tratar o errro
            }
        });

    }

    public LiveData<List<News>> getNews() {
        return mNews;
    }
}