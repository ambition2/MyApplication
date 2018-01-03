package com.example.filetouxiang;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import zhangphil.iosdialog.widget.ActionSheetDialog;

public class MainActivity extends Activity {
    protected static final int TAKE_PICTURE = 1;
    protected static final int CHOOSE_PICTURE = 0;
    protected static Uri tempUri;
    private static final int CROP_SMALL_PICTURE = 2;




    ImageView iv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv=findViewById(R.id.imageView);
        Intent intent = getIntent();
        String image = intent.getStringExtra("image");
        ImageLoader.getInstance().displayImage(image,iv);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showChoosePicDialog();
            }
        });

    }

    private void showChoosePicDialog() {
        new ActionSheetDialog(MainActivity.this)
                .builder()

                .setCancelable(true)
                .setCanceledOnTouchOutside(true)
                .addSheetItem("本地图片", ActionSheetDialog.SheetItemColor.Red
                        , new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                //填写事件
                             showlocation();

                            }

                        })
                .addSheetItem("照相", ActionSheetDialog.SheetItemColor.Red
                        , new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                           showphone();
                            }

                        })

                .show();
    }

    private void showphone() {
        Intent openCameraIntent = new Intent(
                MediaStore.ACTION_IMAGE_CAPTURE);
        tempUri = Uri.fromFile(new File(Environment
                .getExternalStorageDirectory(), "image.jpg"));
        // 指定照片保存路径（SD卡），image.jpg为一个临时文件，每次拍照后这个图片都会被替换
        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);
        startActivityForResult(openCameraIntent, TAKE_PICTURE);
    }

    private void showlocation() {
        Intent openAlbumIntent = new Intent(
                Intent.ACTION_GET_CONTENT);
        openAlbumIntent.setType("image/*");
        startActivityForResult(openAlbumIntent,TAKE_PICTURE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) { // 如果返回码是可以用的
            switch (requestCode) {
                case TAKE_PICTURE:
                    tempUri = data.getData();
                    startPhotoZoom(tempUri); // 开始对图片进行裁剪处理
                    break;
                case CHOOSE_PICTURE:
                    startPhotoZoom(data.getData()); // 开始对图片进行裁剪处理
                    break;
                case CROP_SMALL_PICTURE:
                    if (data != null) {

                        setImageToView(data); // 让刚才选择裁剪得到的图片显示在界面上

                    }
                    break;
            }
        }
    }

    /**
     * 裁剪图片方法实现
     */
    protected void startPhotoZoom(Uri uri) {
        if (uri == null) {
            Log.i("tag", "The uri is not exist.");
        }
        tempUri = uri;
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CROP_SMALL_PICTURE);
    }

    /**
     * 保存裁剪之后的图片数据
     *
     */
    protected void setImageToView(Intent data) {
        Bundle extras = data.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            photo = ImageUtils.toRoundBitmap(photo); // 这个时候的图片已经被处理成圆形的了
            iv.setImageBitmap(photo);
            setFile(photo);
            uploadPic();
        }
    }

    private void setFile(Bitmap photo) {
        File file=new File("mnt/sdcard/mo.jpg");
        try {
            BufferedOutputStream bout=new BufferedOutputStream(new FileOutputStream(file));
            photo.compress(Bitmap.CompressFormat.JPEG,100,bout);
            bout.flush();
            bout.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void uploadPic() {
        File img=new File("mnt/sdcard/mo.jpg");
        System.out.println(img.getName() + img.toString() + "===========================");


        OkHttpClient okHttpClient1 = new OkHttpClient();

        MultipartBody.Builder builder1 = new MultipartBody.Builder();
        builder1.setType(MultipartBody.FORM);


        builder1.addFormDataPart("uid", 71+"");

        builder1.addFormDataPart("file", img.getName(), RequestBody.create(MediaType.parse("application/octet-stream"), img));

        Request request1 = new Request.Builder().post(builder1.build()).url("https://www.zhaoapi.cn/file/upload").build();

        okHttpClient1.newCall(request1).enqueue(new Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                System.out.println("++++++++++++Fileure++++++++++++++");
            }

            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException {

                if (response.isSuccessful()) {
                    System.out.println("++++++++++++Success++++++++++++++");
                    System.out.println("fileuploadsuccess：" + response.body().string());
                }
            }
        });
    }

//    @Override
//    public void showUserInfo() {
//
//
//        ImageLoader.getInnstance().displayImage(tx,ivTx);
//
//    }



}
