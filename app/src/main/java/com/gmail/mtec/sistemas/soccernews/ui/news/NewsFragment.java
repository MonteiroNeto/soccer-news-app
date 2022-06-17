package com.gmail.mtec.sistemas.soccernews.ui.news;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.loader.content.AsyncTaskLoader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.gmail.mtec.sistemas.soccernews.MainActivity;
import com.gmail.mtec.sistemas.soccernews.data.data_local.AppDatabase;
import com.gmail.mtec.sistemas.soccernews.databinding.FragmentNewsBinding;
import com.gmail.mtec.sistemas.soccernews.domain.News;
import com.gmail.mtec.sistemas.soccernews.interfaces.OnclickFavoriteInterface;
import com.gmail.mtec.sistemas.soccernews.ui.adapters.NewsAdapter;


public class NewsFragment extends Fragment  {

    private NewsAdapter newsAdapter;
    private FragmentNewsBinding binding;




    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        NewsViewModel newsViewModel = new ViewModelProvider(this).get(NewsViewModel.class);

        binding = FragmentNewsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();







        initRecyclerView();
        newsViewModel.getNews().observe(getViewLifecycleOwner(), news ->{
            newsAdapter = new NewsAdapter(news,itemOnCLickFavoritarInterface -> {

                MainActivity activity = (MainActivity) getActivity();

                //evento de insersao com room
                //vaiforçar o comando do DB rodar sem erro, serve para que rode com permissão maxima -> AsyncTask.execute
                AsyncTask.execute(()->
                        activity.getDb().newsDAO().insert(itemOnCLickFavoritarInterface));

            });

            binding.rvNews.setAdapter(newsAdapter);

        });

        newsViewModel.getState().observe(getViewLifecycleOwner(),state -> {
            switch (state){

                case DOING:
                    //TODO: incluir SwipperRefreshLayout(mostrar que esta carregando)
                    break;
                case DONE:
                    //TODO: finalizar SwipperRefreshLayout(ocultar que esta carregando)
                    break;
                case ERROR:
                    //TODO: finalizar SwipperRefreshLayout(ocultar que esta carregando)
                    //TODO: Mostrar um erro generico
                    break;
                default:
            }
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