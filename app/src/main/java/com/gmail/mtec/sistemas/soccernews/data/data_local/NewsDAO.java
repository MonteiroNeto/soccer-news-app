package com.gmail.mtec.sistemas.soccernews.data.data_local;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


import com.gmail.mtec.sistemas.soccernews.domain.News;

import java.util.List;

@Dao
public interface NewsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(News news);

    @Query("SELECT * FROM news WHERE favorito  = 1")
    List<News> loadFavoritesNews();


}
