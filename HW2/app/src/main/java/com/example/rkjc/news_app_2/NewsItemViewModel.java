package com.example.rkjc.news_app_2;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class NewsItemViewModel extends AndroidViewModel{
    private Repository mRepository;

    private LiveData<List<NewsItem>> mAllNewsItems;

    public NewsItemViewModel (Application application) {
        super(application);
        mRepository = new Repository(application);
        mAllNewsItems = mRepository.getAllNewsItems();
    }

    public LiveData<List<NewsItem>> getAllNewsItems() {
        return mAllNewsItems;
    }

    public void update() {
        mRepository.updateAll();
    }

    public void deleteAll(){
        mRepository.deleteAll();
    }
}
