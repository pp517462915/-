package com.example.newsdemo.ui.news;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newsdemo.R;
import com.example.newsdemo.logic.Repository;
import com.example.newsdemo.logic.room.entity.News;
import com.example.newsdemo.ui.collect.CollectFragment;
import com.example.newsdemo.ui.search.SearchFragment;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter {
    private final String TAG = "NewsAdapter";
    private Fragment fragment;
    private List<News> newsList;

    public NewsAdapter(Fragment fragment, List<News> newsList) {
        this.fragment = fragment;
        this.newsList = newsList;
    }

    class ViewHolder extends  RecyclerView.ViewHolder {
        ImageView newsItemImageView;
        TextView newsTitleItemTextView;
        TextView newsDateItemTextView;
        TextView newsCategoryItemTextView;
        Button collectItemBtn;
        Button deleteItemBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.newsItemImageView = itemView.findViewById(R.id.newsItemImageView);
            this.newsTitleItemTextView = itemView.findViewById(R.id.newsTitleItemTextView);
            this.newsDateItemTextView = itemView.findViewById(R.id.newsDateItemTextView);
            newsCategoryItemTextView =itemView.findViewById(R.id.newsCategoryItemTextView);
            this.collectItemBtn = itemView.findViewById(R.id.collectItemBtn);
            this.deleteItemBtn = itemView.findViewById(R.id.deleteItemBtn);
        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_news_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                News news = newsList.get(newsList.size() - position - 1);
                Intent intent = new Intent(fragment.getActivity(), WebViewActivity.class);
                intent.putExtra("url", news.getUrl());
                intent.putExtra("title", news.getTitle());
                fragment.startActivity(intent);
            }
        });

        viewHolder.deleteItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                News news = newsList.get(newsList.size() - position - 1);
                news.setIsDelete(true);
                Repository.getInstance().updateNews(news);
                if (fragment instanceof  NewsFragment) {
                    String category = news.getCategory();
                    NewsFragment newsFragment = (NewsFragment)fragment;
                    newsFragment.viewModel.searchNewsType(category);
                }
                if (fragment instanceof SearchFragment) {
                    SearchFragment searchFragment = (SearchFragment)fragment;
                    searchFragment.viewModel.setSearchContent(searchFragment.viewModel.getSearchContent());
                }
                if (fragment instanceof CollectFragment) {
                    CollectFragment collectFragment = (CollectFragment)fragment;
                    collectFragment.viewModel.setLiveData();
                }
            }
        });

        viewHolder.collectItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                News news = newsList.get(newsList.size() - position - 1);
                boolean isCollect = news.getIsCollect();
                news.setIsCollect(!isCollect);
                Repository.getInstance().updateNews(news);
                if (fragment instanceof  NewsFragment) {
                    String category = news.getCategory();
                    NewsFragment newsFragment = (NewsFragment)fragment;
                    newsFragment.viewModel.searchNewsType(category);
                }
                if (fragment instanceof SearchFragment) {
                    SearchFragment searchFragment = (SearchFragment)fragment;
                    searchFragment.viewModel.setSearchContent(searchFragment.viewModel.getSearchContent());
                }
                if (fragment instanceof CollectFragment) {
                    CollectFragment collectFragment = (CollectFragment)fragment;
                    collectFragment.viewModel.setLiveData();
                }
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        // 逆序展示
        News news = newsList.get(newsList.size() - position - 1);
        if (news != null) {
            ViewHolder viewHolder = (ViewHolder)holder;
            String url = news.getPicUrl();
            String title = news.getTitle();
            String date = news.getDate();
            String category = news.getCategory();
            boolean isCollect = news.getIsCollect();

            if (isCollect) {
                viewHolder.collectItemBtn.setBackgroundResource(R.drawable.collect_solid_icon);
            } else {
                viewHolder.collectItemBtn.setBackgroundResource(R.drawable.collect_icon);
            }
            if (url != null || !url.equals("")) {
                Glide.with(fragment).load(url).into(viewHolder.newsItemImageView);
            }
            if (title != null) {
                viewHolder.newsTitleItemTextView.setText(title);
            }
            if (date != null) {
                viewHolder.newsDateItemTextView.setText(date);
            }
            if (category != null) {
                viewHolder.newsCategoryItemTextView.setText("频道：" + category);
            }



        }
    }

    @Override
    public int getItemCount() {
        if (newsList == null) {
            return 0;
        }
        return newsList.size();
    }
}
