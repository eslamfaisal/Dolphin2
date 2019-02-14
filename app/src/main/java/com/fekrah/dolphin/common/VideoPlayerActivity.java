package com.fekrah.dolphin.common;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.fekrah.dolphin.R;
import com.pavanpathro.custom_video_player.CustomVideoPlayer;

public class VideoPlayerActivity extends AppCompatActivity implements CustomVideoPlayer.PlaybackListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);

        CustomVideoPlayer customVideoPlayer = findViewById(R.id.customVideoPlayer);

        customVideoPlayer.setMediaUrl("https://www.rmp-streaming.com/media/bbb-360p.mp4")
                .enableAutoMute(false)
                .enableAutoPlay(false)
                .hideControllers(false)
                .setOnPlaybackListener(this)
                .build();
    }

    @Override
    public void onPlayClick() {

    }

    @Override
    public void onPauseClick() {

    }

    @Override
    public void onPlayEvent() {

    }

    @Override
    public void onPauseEvent() {

    }

    @Override
    public void onCompletedEvent() {

    }
}
