package com.snail.ui.fragment;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

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

        TextView richView= (TextView) view.findViewById(R.id.rich_view);
        String html="<font color='red'> <big>关于我 </big></font> <br>";
        html+="<font color='#0000FF'> <big> <i> 潘岩 </i> </big> <font><br>";
        html+="<font color='@"+android.R.color.white+"'> <tt> <b> <big> <u> 一名高中生 </u> </big> </b> </tt> </font> <br>";
        html += "<font color='red'><big>Snail 的创意来源蜗牛</big></font> <br>";
        html += "<font color='red'><big>学习就像蜗牛一样,一点点的积累</big></font> <br>";
        html += "<font color='red'><big>我要一步一步往上爬</big></font> <br>";
        CharSequence charSequence = Html.fromHtml(html);
        richView.setText(charSequence);

        richView.setMovementMethod(LinkMovementMethod.getInstance());

        return view;
    }



}
