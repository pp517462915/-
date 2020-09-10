package com.example.newsdemo.ui.newsChannal;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newsdemo.R;
import com.example.newsdemo.logic.room.entity.NewsChannal;
import com.example.newsdemo.ui.NewsActivity;
import com.example.newsdemo.ui.news.NewsAdapter;
import com.example.newsdemo.ui.news.NewsFragment;
import com.example.newsdemo.ui.utils.NewsUtils;

import java.util.List;

public class NewsChannalAdapter extends RecyclerView.Adapter {

    private final String TAG = "NewsChannalAdapter";

    private List<NewsChannal> newsChannalList;
    private NewsChannalFragment newsChannalFragment;

    public NewsChannalAdapter(NewsChannalFragment newsChannalFragment, List<NewsChannal> newsChannalList) {
        this.newsChannalFragment = newsChannalFragment;
        this.newsChannalList = newsChannalList;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView newsChannalItemImageView;
        TextView newsChannalNameItem;
        Button deleteNewsChannalBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            newsChannalItemImageView = itemView.findViewById(R.id.newsChannalItemImageView);
            newsChannalNameItem = itemView.findViewById(R.id.newsChannalNameItem);
            deleteNewsChannalBtn = itemView.findViewById(R.id.deleteNewsChannalBtn);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_news_channal_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);


        viewHolder.deleteNewsChannalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                NewsChannal newsChannal = newsChannalList.get(position);
                newsChannalFragment.getViewModel().deleteNewsChannalByChannalName(newsChannal.getChannalName());
                newsChannalFragment.getViewModel().getNewsChannalData();
            }
        });

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                if (position >= newsChannalList.size()) {
                    // 弹框
                    AddNewsChannalDialog addNewsChannalDialog = new AddNewsChannalDialog(newsChannalFragment, newsChannalList);
                    addNewsChannalDialog.show(newsChannalFragment.getActivity().getSupportFragmentManager(), "addNewsChannalDialog");
                } else {
                    if (newsChannalFragment.getActivity() instanceof NewsActivity) {
                        String channalName = newsChannalList.get(position).getChannalName();
                        NewsActivity newsActivity = (NewsActivity)newsChannalFragment.getActivity();
                        newsActivity.setNowNewsCategory(channalName);
                        FragmentManager fragmentTransaction = newsActivity.getSupportFragmentManager();
                        NewsFragment newsFragment = new NewsFragment();
                        fragmentTransaction.beginTransaction().replace(R.id.newsActivityFragment, newsFragment).commit();
                    }
                }
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder)holder;
        if (position >= newsChannalList.size()) {
            viewHolder.newsChannalNameItem.setText("添加新闻频道");
            Log.d(TAG, "test");
            Glide.with(newsChannalFragment).load(R.drawable.add_icon).into(viewHolder.newsChannalItemImageView);
            viewHolder.deleteNewsChannalBtn.setVisibility(View.GONE);
        }else {
            NewsChannal newsChannal = newsChannalList.get(position);
            String channalName = newsChannal.getChannalName();
            if (channalName != null) {
                viewHolder.newsChannalNameItem.setText(newsChannal.getChannalName());
            }
            int url = NewsUtils.getChannalNameToPic().get(channalName);
            Glide.with(newsChannalFragment).load(url).into(viewHolder.newsChannalItemImageView);
            viewHolder.deleteNewsChannalBtn.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return newsChannalList.size() + 1;
    }
}
