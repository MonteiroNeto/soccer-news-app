package com.gmail.mtec.sistemas.soccernews.data.data_local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.gmail.mtec.sistemas.soccernews.domain.News;

@Database(entities = {News.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract NewsDAO newsDAO();
}
