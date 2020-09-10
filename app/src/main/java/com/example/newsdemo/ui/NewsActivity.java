package com.example.newsdemo.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.content.ClipData;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.example.newsdemo.R;
import com.example.newsdemo.ui.collect.CollectFragment;
import com.example.newsdemo.ui.news.NewsFragment;
import com.example.newsdemo.ui.newsChannal.NewsChannalFragment;

import java.util.HashMap;
import java.util.Map;

public class NewsActivity extends AppCompatActivity {
    private String nowNewsCategory = "头条";
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private Map<MenuItem, Drawable> iconOfMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        toolbar = findViewById(R.id.newsActivityToolbar);
        drawerLayout = findViewById(R.id.drawerLayout);
        iconOfMenuItem = new HashMap<>();

        setSupportActionBar(toolbar);

        FragmentManager fragmentTransaction = getSupportFragmentManager();
        NewsFragment newsFragment = new NewsFragment();
        fragmentTransaction.beginTransaction().replace(R.id.newsActivityFragment, newsFragment).commit();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.search_icon);

        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) { }
            @Override
            public void onDrawerOpened(@NonNull View drawerView) { }
            @Override
            public void onDrawerClosed(@NonNull View drawerView) {
                // 关闭输入法
                InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                manager.hideSoftInputFromWindow(drawerView.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
            @Override
            public void onDrawerStateChanged(int newState) { }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        MenuItem item = menu.findItem(R.id.newsItem);
        iconOfMenuItem.put(item, item.getIcon());
        MenuItem item2 = menu.findItem(R.id.newsChannalItem);
        iconOfMenuItem.put(item2, item2.getIcon());
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        FragmentManager fragmentTransaction = getSupportFragmentManager();
        if (!iconOfMenuItem.keySet().contains(item)) {
            iconOfMenuItem.put(item, item.getIcon());
        }

        switch (item.getItemId()) {
            case R.id.newsItem:
//                item.setIcon(R.drawable.news_icon);
                NewsFragment newsFragment = new NewsFragment();
                fragmentTransaction.beginTransaction().replace(R.id.newsActivityFragment, newsFragment).commit();
                break;
            case R.id.newsChannalItem:
//                item.setIcon(R.drawable.news_channal_icon);
                NewsChannalFragment newsChannalFragment = new NewsChannalFragment();
                fragmentTransaction.beginTransaction().replace(R.id.newsActivityFragment, newsChannalFragment).commit();
                break;
            case R.id.collectItem:
                CollectFragment collectFragment = new CollectFragment();
                fragmentTransaction.beginTransaction().replace(R.id.newsActivityFragment, collectFragment).commit();
                break;
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
        }
        return true;
    }


    public String getNowNewsCategory() {
        return nowNewsCategory;
    }

    public void setNowNewsCategory(String nowNewsCategory) {
        this.nowNewsCategory = nowNewsCategory;
    }

    public Map<MenuItem, Drawable> getIconOfMenuItem() {
        return iconOfMenuItem;
    }
}