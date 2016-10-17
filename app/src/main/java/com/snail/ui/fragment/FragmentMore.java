package com.snail.ui.fragment;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;

import com.snail.R;
import com.snail.utils.Player;

import java.io.File;
import java.io.IOException;

/**
 * Created by lichengcai on 2016/9/29.
 */

public class FragmentMore extends Fragment {
    private Button btnPause, btnPlayUrl, btnStop;
    private SeekBar skbProgress;
    private Player player;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_more,container,false);

        btnPlayUrl = (Button) view.findViewById(R.id.btnPlayUrl);
        btnPlayUrl.setOnClickListener(new ClickEvent());

        btnPause = (Button) view.findViewById(R.id.btnPause);
        btnPause.setOnClickListener(new ClickEvent());

        btnStop = (Button) view.findViewById(R.id.btnStop);
        btnStop.setOnClickListener(new ClickEvent());

        skbProgress = (SeekBar) view.findViewById(R.id.skbProgress);
        skbProgress.setOnSeekBarChangeListener(new SeekBarChangeEvent());
//        player = new Player(skbProgress);
        player = new Player();
        return view;
    }

    class ClickEvent implements View.OnClickListener {

        @Override
        public void onClick(View arg0) {
            if (arg0 == btnPause) {
                player.pause();
            } else if (arg0 == btnPlayUrl) {
                //在百度MP3里随便搜索到的,大家可以试试别的链接
                String url="http://app1.showapi.com/en_word/45090_4509/0b355a5022ee446fb0ce7155ba8e43ae.mp3";
                player.playUrl(url);
            } else if (arg0 == btnStop) {
                player.stop();
            }
        }
    }

    class SeekBarChangeEvent implements SeekBar.OnSeekBarChangeListener {
        int progress;

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress,
                                      boolean fromUser) {
            // 原本是(progress/seekBar.getMax())*player.mediaPlayer.getDuration()
            this.progress = progress * player.mediaPlayer.getDuration()
                    / seekBar.getMax();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            // seekTo()的参数是相对与影片时间的数字，而不是与seekBar.getMax()相对的数字
            player.mediaPlayer.seekTo(progress);
        }
    }

}
