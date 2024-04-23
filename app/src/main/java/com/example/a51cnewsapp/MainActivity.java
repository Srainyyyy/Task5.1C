package com.example.a51cnewsapp;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 获取四个新闻 LinearLayout
        LinearLayout news1Layout = findViewById(R.id.content_news1);
        LinearLayout news2Layout = findViewById(R.id.content_news2);
        LinearLayout news3Layout = findViewById(R.id.content_news3);
        LinearLayout news4Layout = findViewById(R.id.content_news4);

        // 设置点击事件，点击不同的新闻进入对应的详情页面
        news1Layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 点击新闻1，打开新闻1的详情页面
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, NewsDetailFragment.newInstance(R.drawable.news_image,"NEWS1", "Here is the detailed text of News 1"))
                        .addToBackStack(null)
                        .commit();
            }
        });

        news2Layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 点击新闻2，打开新闻2的详情页面
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, NewsDetailFragment.newInstance(R.drawable.news_image,"NEWS2", "Here is the detailed text of News 2"))
                        .addToBackStack(null)
                        .commit();
            }
        });

        news3Layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 点击新闻3，打开新闻3的详情页面
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, NewsDetailFragment.newInstance(R.drawable.news_image,"NEWS3", "Here is the detailed text of News 3"))
                        .addToBackStack(null)
                        .commit();
            }
        });

        news4Layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 点击新闻4，打开新闻4的详情页面
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, NewsDetailFragment.newInstance(R.drawable.news_image,"NEWS4", "Here is the detailed text of News 4"))
                        .addToBackStack(null)
                        .commit();
            }
        });
    }
}
