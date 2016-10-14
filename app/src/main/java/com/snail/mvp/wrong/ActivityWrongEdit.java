package com.snail.mvp.wrong;

import android.animation.Animator;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.Types.BoomType;
import com.nightonke.boommenu.Types.ButtonType;
import com.nightonke.boommenu.Types.PlaceType;
import com.nightonke.boommenu.Util;
import com.snail.R;
import com.snail.mvp.wrong.persenter.WrongPresenter;
import com.snail.mvp.wrong.persenter.WrongPresenterImpl;
import com.snail.mvp.wrong.view.WrongView;
import com.snail.ui.activity.ActivityBase;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lichengcai on 2016/10/14.
 */

public class ActivityWrongEdit extends ActivityBase implements WrongView,BoomMenuButton.OnSubButtonClickListener{
    @BindView(R.id.boom)
    BoomMenuButton mBoom;
    @BindView(R.id.image_wrong)
    ImageView mImageWrong;
    @BindView(R.id.edit_wrong_content)
    EditText mEditContent;
    @BindView(R.id.edit_wrong_title)
    EditText mEditTitle;


    private File mImageFile;
    private WrongPresenter mPresenter;
    private static final int INTENT_TO_CAMERA = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wrong_edit);
        ButterKnife.bind(this);

        init();
        addAllListener();
    }

    private void init() {
        mPresenter = new WrongPresenterImpl(this);
    }

    private void addAllListener() {
        mImageWrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentToCamera();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case INTENT_TO_CAMERA:
                    mImageWrong.setImageBitmap(BitmapFactory.decodeFile(mImageFile.getAbsolutePath()));
                    break;
            }
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        initBoomButton();
    }

    /**
     * 调用相机拍照
     */
    private void intentToCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        initImageFile();
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mImageFile));
        startActivityForResult(intent,INTENT_TO_CAMERA);
    }
    /**
     * 初始化图片文件保存到本地
     */
    public void initImageFile() {
        if (android.os.Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED)) {
            mImageFile = new File(Environment.getExternalStorageDirectory(),  "snail.jpg");
            try {
                mImageFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.d("ActivityTest","imageFile---" + mImageFile.length());
        }
    }

    /**
     * 初始化BoomMenuButton
     */
    private void initBoomButton() {
        int[][] subButtonColors = new int[3][2];
        for (int i = 0; i < 3; i++) {
            subButtonColors[i][1] = ContextCompat.getColor(ActivityWrongEdit.this, R.color.colorPrimary);
        }

        new BoomMenuButton.Builder()
                .addSubButton(ContextCompat.getDrawable(ActivityWrongEdit.this, R.drawable.mark), subButtonColors[1], "save")
                .addSubButton(ContextCompat.getDrawable(ActivityWrongEdit.this, R.drawable.refresh), subButtonColors[0], "cancel")
                .addSubButton(ContextCompat.getDrawable(ActivityWrongEdit.this, R.drawable.copy), subButtonColors[0], "share")
                .button(ButtonType.CIRCLE)
                .boom(BoomType.PARABOLA)
                .place(PlaceType.CIRCLE_3_1)
                .subButtonTextColor(ContextCompat.getColor(ActivityWrongEdit.this, R.color.black))
                .subButtonsShadow(Util.getInstance().dp2px(2), Util.getInstance().dp2px(2))
                .onSubButtonClick(this)
                .init(mBoom);
    }

    @Override
    public void setWrongImage(Bitmap bitmap) {
        mImageWrong.setImageBitmap(bitmap);
    }

    @Override
    public void showUploadSuccess() {
        Toast.makeText(ActivityWrongEdit.this,"upload success",Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void showUploadFail() {
        Toast.makeText(ActivityWrongEdit.this,"upload fail",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(int buttonIndex) {
        switch (buttonIndex) {
            case 0:
                Animation animation = AnimationUtils.loadAnimation(ActivityWrongEdit.this,R.anim.shake);
                if (mImageFile != null) {
                    if (!TextUtils.isEmpty(mEditTitle.getText().toString())) {
                        if (!TextUtils.isEmpty(mEditContent.getText().toString())) {
                            mPresenter.uploadImageFile(mImageFile,mEditTitle.getText().toString(),mEditContent.getText().toString());
                        }else {
                            mEditContent.setAnimation(animation);
                            Toast.makeText(ActivityWrongEdit.this,getResources().getString(R.string.wrong_content),Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        mEditTitle.setAnimation(animation);
                        Toast.makeText(ActivityWrongEdit.this,getResources().getString(R.string.wrong_title),Toast.LENGTH_SHORT).show();
                    }
                }else {
                    mImageWrong.setAnimation(animation);
                    Toast.makeText(ActivityWrongEdit.this,getResources().getString(R.string.wrong_image),Toast.LENGTH_SHORT).show();
                }


                break;
            case 1:
                break;
            case 2:
                break;
        }
    }
}
