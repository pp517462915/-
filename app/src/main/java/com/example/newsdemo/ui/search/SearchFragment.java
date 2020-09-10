package com.example.newsdemo.ui.search;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsdemo.R;
import com.example.newsdemo.ui.news.NewsAdapter;

public class SearchFragment extends Fragment {
    private static final String TAG = "SearchFragment";

    private RecyclerView recyclerView;
    private EditText searchEditView;
    private NewsAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    public SearchViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerView = getActivity().findViewById(R.id.searchRecyclerView);
        searchEditView = getActivity().findViewById(R.id.searchEditView);
        viewModel = ViewModelProviders.of(this).get(SearchViewModel.class);
        layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new NewsAdapter(this, viewModel.getNewsList());
        recyclerView.setAdapter(adapter);

        viewModel.newsListLiveData.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    adapter.notifyDataSetChanged();
                }
            }
        });

        searchEditView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable s) {
                String content = s.toString();
                if (content != null && !content.equals("")) {
                    recyclerView.setVisibility(View.VISIBLE);
                    viewModel.setSearchContent(content);
                } else {
                    recyclerView.setVisibility(View.GONE);
                }
            }
        });
    }
}
