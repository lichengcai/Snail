package com.snail.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.GetDataCallback;
import com.avos.avoscloud.ProgressCallback;
import com.avos.avoscloud.SaveCallback;
import com.snail.R;
import com.snail.utils.ImageLoader;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chengcai on 2016/10/11.
 */

public class ActivityTest extends ActivityBase {
    @BindView(R.id.image)
    ImageView mImage;
    @BindView(R.id.image_real)
    ImageView mImage_real;

    private AVFile avfile ;
    private String url = null;


    private File file;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case  10:
//                    mImage_real.setImageBitmap(BitmapFactory.decodeFile(imageFileFinal.getAbsolutePath()));
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);

    }

    public void camera(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent,0);
        }

//        download(avfile);
//        AVFile avFile = new AVFile("test.png",url,null);
//        download(avFile);
//        testDownloadImage();
//        String url = "http://ac-3www917q.clouddn.com/H0In9XhBO7ImB8AUr2XABTeJ6bP9pVRgyHOJIlAj.png";
//        ImageLoader.getInstance().displayImage(ActivityTest.this,url,mImage_real);
    }


    public void real(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        initImg();
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        startActivityForResult(intent,1);
    }

    //    Bitmap对象保存味图片文件
    public File saveBitmapFile(Bitmap bitmap){
        File file=new File("/mnt/sdcard/pic/01.jpg");//将要保存图片的路径
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();
            return file;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            //缩略图
            if (requestCode == 0) {
                Bundle bundle = data.getExtras();
                Bitmap bitmap = (Bitmap) bundle.get("data");
//                File file = saveBitmapFile(bitmap);
//                Log.d("onActivityResult","file size---" + file.length());
                mImage.setImageBitmap(bitmap);
            }
            if (requestCode == 1) {
                Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
//                mImage_real.setImageBitmap(bitmap);
                uploadCamerImage(file.getPath());
            }
        }else {
            Toast.makeText(getApplicationContext(),"fail",Toast.LENGTH_SHORT).show();

        }
    }

    public void testDownloadImage () {
        String url = "http://ac-3www917q.clouddn.com/H0In9XhBO7ImB8AUr2XABTeJ6bP9pVRgyHOJIlAj.png";
        AVFile file = new AVFile("test.png", url, new HashMap<String, Object>());
        file.getDataInBackground(new GetDataCallback() {
            @Override
            public void done(byte[] bytes, AVException e) {
                // bytes 就是文件的数据流
                mImage_real.setImageBitmap(BitmapFactory.decodeByteArray(bytes,0,bytes.length));
            }
        }, new ProgressCallback() {
            @Override
            public void done(Integer integer) {
                Log.d("download image",integer+"");
                // 上传进度数据，integer 介于 0 和 100。
            }
        });
    }

    public Bitmap download(AVFile file) {
        file.getDataInBackground(new GetDataCallback() {
            @Override
            public void done(byte[] bytes, AVException e) {
                Log.d("download","bytes length---" + bytes.length);
                Bitmap b = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                Toast.makeText(ActivityTest.this,"test",Toast.LENGTH_SHORT).show();
                mImage_real.setImageBitmap(b);
            }
        });
        return null;
    }
    /**
     * invoke leanclound interface to upload local file by camera
     * @param filePath
     */
    public void uploadCamerImage(String filePath) {
        try {
            avfile = AVFile.withAbsoluteLocalPath("test.png", filePath);
            long l = System.currentTimeMillis();
            avfile.addMetaData("time",String.valueOf(l));
            avfile.saveInBackground(new SaveCallback() {
                @Override
                public void done(AVException e) {
                    if (e == null)
                        url = avfile.getUrl();
                        Log.d("upload-net-url", avfile.getUrl());//返回一个唯一的 Url 地址
                }
            },new ProgressCallback() {
                @Override
                public void done(Integer integer) {
                    Log.d("upload",integer.toString());
                    // 上传进度数据，integer 介于 0 和 100。
                }
            });
        } catch (FileNotFoundException e) {
            Log.d("upload-exception", e.toString());
            e.printStackTrace();
        }
    }

    public  void  initImg() {
        if (android.os.Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED)) {
            String s = String.valueOf(System.currentTimeMillis());
            file = new File(Environment.getExternalStorageDirectory(),  "snail.jpg");

            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.d("ActivityTest","imageFile---" + file.length());
        }
    }

    /**
     * get tamp iamge ,
     * @return File
     */
//    public static File getTempImage() {
//        if (android.os.Environment.getExternalStorageState().equals(
//                android.os.Environment.MEDIA_MOUNTED)) {
////            File tempFile = new File(Environment.getExternalStorageDirectory(), System.currentTimeMillis() + ".jpg");
//            File tempFile = new File(Environment.getExternalStorageDirectory(), "temp.jpg");
//
//            try {
//                tempFile.createNewFile();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            Log.d("ActivityTest","imageFile---" + tempFile.length());
//            return tempFile;
//        }
//        return null;
//    }

//    public static File getTempImage() {
//        if (android.os.Environment.getExternalStorageState().equals(
//                android.os.Environment.MEDIA_MOUNTED)) {
//            File tempFile = new File(Environment.getExternalStorageDirectory(), "temp.jpg");
//
//            try {
//                tempFile.createNewFile();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            Log.d("ActivityTest","getTempImage imageFile---" + tempFile.length());
//            return tempFile;
//        }
//        return null;
//    }
}
