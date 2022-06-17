package com.gmail.mtec.sistemas.soccernews.ui.favorites;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Room;

import com.gmail.mtec.sistemas.soccernews.MainActivity;
import com.gmail.mtec.sistemas.soccernews.data.data_local.AppDatabase;
import com.gmail.mtec.sistemas.soccernews.databinding.FragmentFavoritesBinding;
import com.gmail.mtec.sistemas.soccernews.domain.News;
import com.gmail.mtec.sistemas.soccernews.ui.adapters.NewsAdapter;

import java.util.List;

public class FavoritesFragment extends Fragment {

    private FragmentFavoritesBinding binding;

    private NewsAdapter newsAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        FavoritesViewModel dashboardViewModel =
                new ViewModelProvider(this).get(FavoritesViewModel.class);

        binding = FragmentFavoritesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();



        initRecyclerView();
        loadLocalDbRoom();


        return root;
    }

    private void loadLocalDbRoom() {
        MainActivity activity = (MainActivity) getActivity();
        List<News> favoriteNews = activity.getDb().newsDAO().loadFavoritesNews();
        binding.rvNews.setAdapter(new NewsAdapter(favoriteNews, updateNews->{
            activity.getDb().newsDAO().insert(updateNews);
            loadLocalDbRoom();

        }));
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