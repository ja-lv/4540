package com.example.rkjc.news_app_2;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

//import com.example.rkjc.news_app_2.NewsItem;
//import com.example.rkjc.news_app_2.NewsItemViewModel;


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

        //set news item data
        mNewsItemViewModel = ViewModelProviders.of(this).get(NewsItemViewModel.class);

        mRecyclerAdapter = new NewsRecyclerViewAdapter(this, news);

        mNewsItemViewModel.getAllNewsItems().observe(this, new Observer<List<NewsItem>>() {
            @Override
            public void onChanged(@Nullable final List<NewsItem> newsItems) {
                // Update the cached copy of the words in the adapter.
                mRecyclerAdapter.setNewsList(newsItems);
            }
        });

        mRecyclerView.setAdapter(mRecyclerAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemThatWasClickedId = item.getItemId();
        Log.d("mycodes", "ITEM WAS SELECTED");
        if (itemThatWasClickedId == R.id.get_news) {
            mNewsItemViewModel.update();
            mRecyclerAdapter.mNews.addAll((ArrayList) mNewsItemViewModel.getAllNewsItems().getValue());
            mRecyclerAdapter.notifyDataSetChanged();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    //returns status
    private Integer newsDataProcess(String jsonString){



        return 1;
    }

//    private class NewsQueryTask extends AsyncTask<URL, Integer, String> {
//
//
//        @Override
//        protected String doInBackground(URL... params) {
//            Log.d("mycode", "RUNNING BGKDSS");
//            try {
//                return NetworkUtils.getResponseFromHttpUrl(NetworkUtils.buildURL());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return "1";
//        }
//
//        @Override
//        protected void onPostExecute(String result) {
//            Log.d("mycode", result);
//            /*textView.setText("Response  is: " + result);*/
//
//
//            //run code to execute recycler, adapter
//
////            news = JsonUtils.parseNews(result);
//
//            mNewsItemViewModel.deleteAll();
//            mAdapter.mNews.addAll(JsonUtils.parseNews(result));
//            mAdapter.notifyDataSetChanged();
//
//
//            super.onPostExecute(result);
//        }
//    }

}
