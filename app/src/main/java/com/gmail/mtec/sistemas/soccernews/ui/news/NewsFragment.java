package com.gmail.mtec.sistemas.soccernews.ui.news;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.gmail.mtec.sistemas.soccernews.databinding.FragmentNewsBinding;
import com.gmail.mtec.sistemas.soccernews.ui.adapters.NewsAdapter;


public class NewsFragment extends Fragment {

    private NewsAdapter newsAdapter;
    private FragmentNewsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        NewsViewModel newsViewModel = new ViewModelProvider(this).get(NewsViewModel.class);

        binding = FragmentNewsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        initRecyclerView();
        newsViewModel.getNews().observe(getViewLifecycleOwner(), news ->{
            newsAdapter = new NewsAdapter(news);
            binding.rvNews.setAdapter(newsAdapter);

        });
        return root;
    }

    private void initRecyclerView() {

        LinearLayoutManager layout = new LinearLayoutManager(getContext());
        binding.rvNews.setLayoutManager(layout);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}