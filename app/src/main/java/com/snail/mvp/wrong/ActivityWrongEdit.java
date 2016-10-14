package com.snail.mvp.wrong;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;
import android.widget.TextView;

import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.Types.BoomType;
import com.nightonke.boommenu.Types.ButtonType;
import com.nightonke.boommenu.Types.PlaceType;
import com.nightonke.boommenu.Util;
import com.snail.R;
import com.snail.mvp.news.listener.OnWindowFocusListener;
import com.snail.ui.activity.ActivityBase;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lichengcai on 2016/10/14.
 */

public class ActivityWrongEdit extends ActivityBase implements OnWindowFocusListener{
    @BindView(R.id.boom)
    BoomMenuButton mBoom;
    @BindView(R.id.image_wrong)
    ImageView mImageWrong;
    @BindView(R.id.text_wrong_content)
    TextView mTextContent;
    @BindView(R.id.text_wrong_title)
    TextView mTextTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wrong_edit);
        ButterKnife.bind(this);
    }

    @Override
    public void onWidowFocus() {
        initBoomButton();
    }

    /**
     * 初始化BoomMenuButton
     */
    private void initBoomButton() {
        int[][] subButtonColors = new int[3][2];
        for (int i = 0; i < 3; i++) {
            subButtonColors[i][1] = ContextCompat.getColor(ActivityWrongEdit.this, R.color.colorPrimary);
        }

        // Now with Builder, you can init BMB more convenient
        new BoomMenuButton.Builder()
                .addSubButton(ContextCompat.getDrawable(ActivityWrongEdit.this, R.drawable.mark), subButtonColors[1], "add")
                .addSubButton(ContextCompat.getDrawable(ActivityWrongEdit.this, R.drawable.refresh), subButtonColors[0], "cancel")
                .addSubButton(ContextCompat.getDrawable(ActivityWrongEdit.this, R.drawable.copy), subButtonColors[0], "share")
                .button(ButtonType.CIRCLE)
                .boom(BoomType.PARABOLA)
                .place(PlaceType.CIRCLE_3_1)
                .subButtonTextColor(ContextCompat.getColor(ActivityWrongEdit.this, R.color.black))
                .subButtonsShadow(Util.getInstance().dp2px(2), Util.getInstance().dp2px(2))
                .init(mBoom);
    }
}
