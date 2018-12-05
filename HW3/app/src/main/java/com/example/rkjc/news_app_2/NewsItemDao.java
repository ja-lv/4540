package com.example.rkjc.news_app_2;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

// For more information on how to use DAOs with Room, see https://developer.android.com/training/data-storage/room/accessing-data

@Dao
public interface NewsItemDao {

    @Insert
    void insert(List<NewsItem> items);

    //If conflicts are possible, you can use @Insert(onConflict = OnConflictStrategy.REPLACE)

    @Query("DELETE FROM news_item")
    void clearAll();

    @Query("SELECT * FROM news_item ORDER BY title ASC")
    LiveData<List<NewsItem>> loadAllItems();
}
