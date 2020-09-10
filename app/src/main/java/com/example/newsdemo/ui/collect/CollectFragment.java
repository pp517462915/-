package com.example.newsdemo.ui.collect;

import android.content.Intent;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsdemo.R;
import com.example.newsdemo.ui.NewsActivity;
import com.example.newsdemo.ui.news.NewsAdapter;

public class CollectFragment extends Fragment {

    private final String TAG = "CollectFragment";

    private RecyclerView recyclerView;
    public CollectViewModel viewModel;
    private NewsAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ccollect, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerView = getActivity().findViewById(R.id.collectRecyclerView);
        viewModel = ViewModelProviders.of(this).get(CollectViewModel.class);

        layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new NewsAdapter(this, viewModel.getNewsList());
        recyclerView.setAdapter(adapter);

        viewModel.newsListLiveData.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    // 提醒更新列表
                    adapter.notifyDataSetChanged();
                }
            }
        });

        // 使上面的图标变色
        if (getActivity() instanceof NewsActivity) {
            NewsActivity newsActivity = (NewsActivity)getActivity();
            for (MenuItem menuItem: newsActivity.getIconOfMenuItem().keySet()) {
                menuItem.setIcon(newsActivity.getIconOfMenuItem().get(menuItem));
            }
            for (MenuItem menuItem: newsActivity.getIconOfMenuItem().keySet()) {
                if (menuItem.getItemId() == R.id.collectItem) {
                    menuItem.setIcon(R.drawable.collect_toolbar_icon);
                }
            }
        }

        viewModel.setLiveData();
    }

}
