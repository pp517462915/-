package com.example.newsdemo.ui.newsChannal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsdemo.R;
import com.example.newsdemo.logic.Repository;
import com.example.newsdemo.ui.NewsActivity;

public class NewsChannalFragment extends Fragment {

    private RecyclerView newsChannalRecyclerView;
    private NewsChannalAdapter newsChannalAdapter;
    private NewsChannalViewModel viewModel;
    private MenuItem menuItem;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news_channal, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        viewModel = ViewModelProviders.of(this).get(NewsChannalViewModel.class);

        newsChannalRecyclerView = getActivity().findViewById(R.id.newsChannalRecyclerView);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        newsChannalRecyclerView.setLayoutManager(layoutManager);

        newsChannalAdapter = new NewsChannalAdapter(this, viewModel.getNewsChannalList());
        newsChannalRecyclerView.setAdapter(newsChannalAdapter);

        // 使上面的图标变色
        if (getActivity() instanceof NewsActivity) {
            NewsActivity newsActivity = (NewsActivity)getActivity();
            for (MenuItem menuItem: newsActivity.getIconOfMenuItem().keySet()) {
                menuItem.setIcon(newsActivity.getIconOfMenuItem().get(menuItem));
            }
            for (MenuItem menuItem: newsActivity.getIconOfMenuItem().keySet()) {
                if (menuItem.getItemId() == R.id.newsChannalItem) {
                    menuItem.setIcon(R.drawable.news_channal_icon);
                }
            }
        }

        viewModel.getNewsChannalLiveData.observe(this, new Observer() {
            @Override
            public void onChanged(Object o) {
                newsChannalAdapter.notifyDataSetChanged();
            }
        });

    }

    public NewsChannalViewModel getViewModel() {
        return viewModel;
    }
}
