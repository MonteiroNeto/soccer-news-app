package com.gmail.mtec.sistemas.soccernews.ui.news;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gmail.mtec.sistemas.soccernews.domain.News;

import java.util.ArrayList;
import java.util.List;

public class NewsViewModel extends ViewModel {

    private final MutableLiveData<List<News>> mNews;

    public NewsViewModel() {
        this.mNews = new MutableLiveData<>();


        //TODO remover lista de noticias
        List<News> news = new ArrayList<>();
        news.add(new News("Brasil Vence","ue et adipiscing cubilia a viverra morbi posuere, placerat condimentum risus per urna pharetra faucibus montes, semper consequat vel aliquam blandit sit ultrices volutpat."));
        news.add(new News("Copa do Mundo Inicia","ue et adipiscing cubilia a viverra morbi posuere, placerat condimentum risus per urna pharetra faucibus montes, semper consequat vel aliquam blandit sit ultrices volutpat."));
        news.add(new News("Primeiro Jogo Na França","ue et adipiscing cubilia a viverra morbi posuere, placerat condimentum risus per urna pharetra faucibus montes, semper consequat vel aliquam blandit sit ultrices volutpat."));
        this.mNews.setValue(news);
    }

    public LiveData<List<News>> getNews() {
        return mNews;
    }
}