package com.example.newsdemo.ui.news;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.newsdemo.NewsDemoApplication;
import com.example.newsdemo.R;
import com.example.newsdemo.ui.NewsActivity;
import com.example.newsdemo.ui.utils.NewsUtils;

public class NewsFragment extends Fragment {
    private final String TAG = "NewsFragment";

    private RecyclerView recyclerView;
    private NewsAdapter newsAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private SwipeRefreshLayout swipeRefreshLayout;
    private String nowNewsCategory = "头条";


    NewsViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        viewModel = ViewModelProviders.of(this).get(NewsViewModel.class);

        recyclerView = getActivity().findViewById(R.id.newsRecyclerView);
        layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);
        swipeRefreshLayout = getActivity().findViewById(R.id.newsSwipeRefreshLayout);

        newsAdapter = new NewsAdapter(this, viewModel.getNewsList());
        recyclerView.setAdapter(newsAdapter);

        if (getActivity() instanceof NewsActivity) {
            NewsActivity newsActivity = (NewsActivity)getActivity();
            nowNewsCategory = newsActivity.getNowNewsCategory();
            for (MenuItem menuItem: newsActivity.getIconOfMenuItem().keySet()) {
                menuItem.setIcon(newsActivity.getIconOfMenuItem().get(menuItem));
            }
            for (MenuItem menuItem: newsActivity.getIconOfMenuItem().keySet()) {
                if (menuItem.getItemId() == R.id.newsItem) {
                    menuItem.setIcon(R.drawable.news_icon);
                }
            }
        }

        swipeRefreshLayout.setColorSchemeColors(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                NewsUtils.getInstance().searchNewsToDB(NewsUtils.getInstance().getTypeOfRoomToInternet().get(nowNewsCategory),
                        viewModel);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (swipeRefreshLayout.isRefreshing()) {
                            swipeRefreshLayout.setRefreshing(false);
                        }
                    }
                }).start();
            }
        });

        viewModel.getNewsListLiveData().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean flag) {
                if (flag) {
                    Log.d(TAG, "---notify---");
                    newsAdapter.notifyDataSetChanged();
                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        });



        viewModel.searchNewsType(nowNewsCategory);

    }

}
