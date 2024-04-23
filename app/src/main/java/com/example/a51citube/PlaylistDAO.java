package com.example.a51citube;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class PlaylistDAO {
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    public PlaylistDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    // 将视频链接添加到播放列表
    public long addToPlaylist(String videoLink) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_VIDEO_LINK, videoLink);
        return database.insert(DatabaseHelper.TABLE_PLAYLIST, null, values);
    }
}
