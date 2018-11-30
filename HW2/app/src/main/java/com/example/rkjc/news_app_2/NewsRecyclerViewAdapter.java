package com.example.rkjc.news_app_2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class NewsRecyclerViewAdapter extends RecyclerView.Adapter<NewsRecyclerViewAdapter.NewsItemViewHolder> {
    Context mContext;
    ArrayList<NewsItem> mNews;

    public NewsRecyclerViewAdapter(Context context, ArrayList<NewsItem> news){
        this.mContext = context;
        this.mNews = news;
    }

    @Override
    public NewsRecyclerViewAdapter.NewsItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(R.layout.news_item, parent, shouldAttachToParentImmediately);
        NewsItemViewHolder viewHolder = new NewsItemViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NewsRecyclerViewAdapter.NewsItemViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mNews.size();
    }

    void setNewsList(List<NewsItem> newsItems){
        mNews = (ArrayList) newsItems;
    }

    public class NewsItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView author;
        TextView title;
        TextView description;
        TextView url;
        TextView urlToImage;
        TextView publishedAt;


        public NewsItemViewHolder (View itemView) {
            super(itemView);
            author = (TextView) itemView.findViewById(R.id.author);
            title = (TextView) itemView.findViewById(R.id.title);
            description = (TextView) itemView.findViewById(R.id.description);
            url = (TextView) itemView.findViewById(R.id.url);
            urlToImage = (TextView) itemView.findViewById(R.id.urlToImage);
            publishedAt = (TextView) itemView.findViewById(R.id.publishedAt);
        }

        void bind(final int listIndex) {
            author.setText(mNews.get(listIndex).getAuthor());
            title.setText(mNews.get(listIndex).getTitle());
            description.setText(mNews.get(listIndex).getDescription());
            url.setText(mNews.get(listIndex).getUrl());
            urlToImage.setText(mNews.get(listIndex).getUrlToImage());
            publishedAt.setText(mNews.get(listIndex).getPublishedAt());
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            String urlString = mNews.get(getAdapterPosition()).getUrl();
            Intent intent = new Intent(mContext, WebActivity.class);
            intent.putExtra("urlString", urlString);
            mContext.startActivity(intent);
        }
    }

}
