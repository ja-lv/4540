package com.example.rkjc.news_app_2;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.rkjc.news_app_2.scheduler.NotificationFirebaseJobService;
import com.example.rkjc.news_app_2.scheduler.NotificationUtils;
import com.example.rkjc.news_app_2.scheduler.ScheduleUtils;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private NewsItemViewModel mNewsItemViewModel;
    private static final int LOADER_ID = 1;
    private RecyclerView mRecyclerView;
    private NewsRecyclerViewAdapter mRecyclerAdapter;
    private ArrayList<NewsItem> news = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set recycler view
        mRecyclerView = (RecyclerView) findViewById(R.id.news_recyclerview);

        mRecyclerAdapter = new NewsRecyclerViewAdapter(this, news);

        //set news item data
        mNewsItemViewModel = ViewModelProviders.of(this).get(NewsItemViewModel.class);

        mNewsItemViewModel.getAllNewsItems().observe(this, new Observer<List<NewsItem>>() {
            @Override
            public void onChanged(@Nullable final List<NewsItem> newsItems) {
                // Update the cached copy of the words in the adapter.
                mRecyclerAdapter.setNewsList(newsItems);
                mRecyclerAdapter.notifyDataSetChanged();
            }
        });

        mRecyclerView.setAdapter(mRecyclerAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //run scheduler
        ScheduleUtils.scheduleUpdates(this);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemThatWasClickedId = item.getItemId();
        Log.d("mycodes", "ITEM WAS SELECTED");
        if (itemThatWasClickedId == R.id.get_news) {
            mNewsItemViewModel.update();

//            NotificationUtils.notificationReminder(this);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

}
