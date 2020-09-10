package com.example.newsdemo.ui.news;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.newsdemo.R;

import gdut.bsx.share2.Share2;
import gdut.bsx.share2.ShareContentType;

public class WebViewActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private WebView newsWebView;

    private String nowUrl;
    private String nowTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        newsWebView = findViewById(R.id.newsWebView);
        toolbar = findViewById(R.id.newsWebViewActivityToolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.return_icon);

        newsWebView.getSettings().setJavaScriptEnabled(true);
        newsWebView.setWebViewClient(new WebViewClient());
        Intent intent = getIntent();
        nowUrl = intent.getStringExtra("url");
        nowTitle = intent.getStringExtra("title");
        if (!nowUrl.equals("") || nowUrl != null) {
            newsWebView.loadUrl(nowUrl);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.shareNewsIcon:
                String message = "【新闻DemoApp】" + nowTitle + "请点击：" + nowUrl;
                new Share2.Builder(WebViewActivity.this)
                        .setContentType(ShareContentType.TEXT)
                        .setTextContent(message)
                        .setTitle("Share Text")
                        .build()
                        .shareBySystem();
                break;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.web_view_toolbar, menu);
        return true;
    }
}