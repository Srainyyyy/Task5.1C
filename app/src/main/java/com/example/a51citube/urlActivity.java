package com.example.a51citube;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class urlActivity extends AppCompatActivity {
    private EditText editTextVideoLink;
    private Button buttonPlay;
    private Button buttonAddToPlaylist;
    private Button buttonMyPlaylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_url);

        editTextVideoLink = findViewById(R.id.editTextVideoLink);
        buttonPlay = findViewById(R.id.buttonPlay);
        buttonAddToPlaylist = findViewById(R.id.buttonAddToPlaylist);
        buttonMyPlaylist = findViewById(R.id.buttonMyPlaylist);

        // 设置播放按钮的点击事件监听器
        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 处理播放视频逻辑
                // 获取用户输入的视频链接
                String videoLink = editTextVideoLink.getText().toString().trim();

                if (!TextUtils.isEmpty(videoLink)) {
                    // 创建意图以启动播放页面，并传递视频链接数据
                    Intent playIntent = new Intent(urlActivity.this, PlayActivity.class);
                    playIntent.putExtra("VIDEO_LINK", videoLink);
                    startActivity(playIntent);
                } else {
                    // 提示用户输入视频链接
                    Toast.makeText(urlActivity.this, "Please enter a video link", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // 设置添加到播放列表按钮的点击事件监听器
        buttonAddToPlaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 获取用户输入的视频链接
                String videoLink = editTextVideoLink.getText().toString().trim();

                if (!TextUtils.isEmpty(videoLink)) {
                    // 创建 PlaylistDAO 实例
                    PlaylistDAO playlistDAO = new PlaylistDAO(urlActivity.this);
                    playlistDAO.open();

                    // 将视频链接添加到播放列表
                    long result = playlistDAO.addToPlaylist(videoLink);

                    // 关闭数据库连接
                    playlistDAO.close();

                    if (result != -1) {
                        // 提示用户视频链接已添加到播放列表
                        Toast.makeText(urlActivity.this, "Video added to playlist", Toast.LENGTH_SHORT).show();
                    } else {
                        // 提示添加失败
                        Toast.makeText(urlActivity.this, "Failed to add video to playlist", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // 提示用户输入视频链接
                    Toast.makeText(urlActivity.this, "Please enter a video link", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // 设置我的播放列表按钮的点击事件监听器
        buttonMyPlaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // 获取用户输入的视频链接
                String videoLink = editTextVideoLink.getText().toString().trim();

                // 创建意图以启动播放列表页面，并传递视频链接数据
                Intent playlistIntent = new Intent(urlActivity.this, PlaylistActivity.class);
                playlistIntent.putExtra("VIDEO_LINK", videoLink);
                startActivity(playlistIntent);
            }
        });
    }
}
