package com.gmail.mtec.sistemas.soccernews.ui.adapters;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gmail.mtec.sistemas.soccernews.R;
import com.gmail.mtec.sistemas.soccernews.databinding.NewsItemBinding;
import com.gmail.mtec.sistemas.soccernews.domain.News;
import com.gmail.mtec.sistemas.soccernews.interfaces.OnclickFavoriteInterface;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.myViewHolder> {
    List<News> newsList;
    OnclickFavoriteInterface onclickFavoriteInterface;

    public NewsAdapter(List<News> newsList, OnclickFavoriteInterface onCLickFavoritarInterface) {
        this.newsList = newsList;
        this.onclickFavoriteInterface = onCLickFavoritarInterface;
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

        //metodoCLick compartilhar link
        holder.binding.ivShare.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT,item.getLink());
            holder.itemView.getContext().startActivity(Intent.createChooser(intent,"share"));
        });

        //Evento de CLICK no FAVORITAR, o EVENTO sera tratado no FRagmento para poder usar a persistencia de dados do ROOM
        holder.binding.ivFavorite.setOnClickListener(view -> {
            if (item.favorito == null){
                item.favorito = false;
            }

            item.favorito = !item.favorito;//se for marcado como favorito inverte , se não estiver fica como favorito

            //passar item para o Fragment
            onclickFavoriteInterface.onCLickFavorite(item);



            notifyItemChanged(position);
        });
        //alterar cor do item Color
        if (item.favorito != null){
            //Toast.makeText(holder.itemView.getContext(), "**"+item.favorito, Toast.LENGTH_SHORT).show();
            if (item.favorito){
                holder.binding.ivFavorite.setColorFilter(
                        holder.itemView.getContext().getResources().getColor(R.color.purple_200));
            }else {
                holder.binding.ivFavorite.setColorFilter(
                        holder.itemView.getContext().getResources().getColor(R.color.black));
            }
        }


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
