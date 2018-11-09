package com.example.rkjc.news_app_2;

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



public class MainActivity extends AppCompatActivity {
    private static final int LOADER_ID = 1;
    private RecyclerView mRecyclerView;
    private NewsRecyclerViewAdapter mAdapter;
    private ArrayList<NewsItem> news = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set recycler view
        mRecyclerView = (RecyclerView)findViewById(R.id.news_recyclerview);

        mAdapter = new NewsRecyclerViewAdapter(this, news);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemThatWasClickedId = item.getItemId();
        Log.d("mycode", "ITEM WAS SELECTED");
        if (itemThatWasClickedId == R.id.get_news) {
            NewsQueryTask task = new NewsQueryTask();

            task.execute();

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

    private class NewsQueryTask extends AsyncTask<URL, Integer, String> {


        @Override
        protected String doInBackground(URL... params) {
            Log.d("mycode", "RUNNING BGKDSS");
            try {
                return NetworkUtils.getResponseFromHttpUrl(NetworkUtils.buildURL());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "1";
        }

        @Override
        protected void onPostExecute(String result) {
            Log.d("mycode", result);
            /*textView.setText("Response  is: " + result);*/


            //run code to execute recycler, adapter

            news = JsonUtils.parseNews(result);
            mAdapter.mNews.addAll(news);
            mAdapter.notifyDataSetChanged();


            super.onPostExecute(result);
        }
    }

}
