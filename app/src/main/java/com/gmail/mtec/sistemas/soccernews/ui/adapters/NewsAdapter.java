package com.gmail.mtec.sistemas.soccernews.ui.adapters;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gmail.mtec.sistemas.soccernews.R;
import com.gmail.mtec.sistemas.soccernews.databinding.NewsItemBinding;
import com.gmail.mtec.sistemas.soccernews.domain.News;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.myViewHolder> {
    List<News> newsList;

    public NewsAdapter(List<News> newsList) {
        this.newsList = newsList;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        NewsItemBinding binding = NewsItemBinding.inflate(layoutInflater,parent,false);

        return new myViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        News item = newsList.get(position);
        holder.binding.tvTittle.setText(item.getTittle());
        holder.binding.tvDesription.setText(item.getDescription());

        //Usando a biblioteca Picasso para recuperar a imagem
        Picasso.get().load(item.getImage())
                .fit()
                .into(holder.binding.ivTimeLine);

        //metodoCLick OpenLink
        holder.binding.btOpenLink.setOnClickListener(view ->{
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(item.getLink()));
            holder.itemView.getContext().startActivity(intent);

        });
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        private final NewsItemBinding binding;

        public myViewHolder(NewsItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
