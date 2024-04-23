package com.example.a51citube;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class PlaylistActivity extends AppCompatActivity {

    private ListView playList;
    private List<String> playlistData;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);
        playList = findViewById(R.id.playList);
        // 加载播放列表数据
        loadPlaylistData();
    // 设置播放列表项点击事件监听器
        playList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 获取被点击的视频链接
                String videoLink = playlistData.get(position);

                // 创建意图以启动播放页面，并传递视频链接数据
                Intent playIntent = new Intent(PlaylistActivity.this, PlayActivity.class);
                playIntent.putExtra("VIDEO_LINK", videoLink);
                startActivity(playIntent);
            }
        });

    }

    private void loadPlaylistData() {
        // 加载播放列表数据的逻辑
        // 获取用户输入的链接
        String userInputLink = getIntent().getStringExtra("VIDEO_LINK");

        // 创建用于保存播放列表数据的列表
        List<String> playlistData = new ArrayList<>();

        // 如果用户输入的链接不为空，则将其添加到播放列表数据中
        if (!TextUtils.isEmpty(userInputLink)) {
            playlistData.add(userInputLink);
        }

        // 创建适配器
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, playlistData);

        // 将适配器设置到 ListView 上
        if (playList != null) {
            playList.setAdapter(adapter);
        } else {
            Log.e("PlaylistActivity", "playList is null");
        }
    }

}
