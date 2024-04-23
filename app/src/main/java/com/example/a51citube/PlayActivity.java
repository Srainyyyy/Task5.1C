package com.example.a51citube;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;


public class PlayActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        // 初始化视频播放器
        initializeVideoPlayer();


    }

    private void initializeVideoPlayer() {
        // 初始化视频播放器
        // 获取上一个页面传递过来的视频链接
        String videoLink = getIntent().getStringExtra("VIDEO_LINK");

        // 初始化 WebView
        webView = findViewById(R.id.webview);
        //String video="<iframe width=\"100%\" height=\"50%\" src=\""+ videoLink +"\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>";
        //webView.loadData(video,"text/html","utf-8");
        //webView.getSettings().setJavaScriptEnabled(true);
        //webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient()); // 在应用内打开链接
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true); // 启用 JavaScript
        webView.loadUrl(videoLink); // 加载视频链接
    }
}
