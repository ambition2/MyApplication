package com.example.frescostudy;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.bitmaps.SimpleBitmapReleaser;
import com.facebook.imagepipeline.core.ImagePipeline;

import org.greenrobot.eventbus.EventBus;

public class MainActivity extends Activity {
    SimpleDraweeView  sdv;
    TextView tv;
    Button bt,clear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
          tv=findViewById(R.id.tv1);
        bt=findViewById(R.id.btt);
        clear=findViewById(R.id.clear);
//        Uri uri = Uri.parse("http://img.huofar.com/data/jiankangrenwu/shizi.gif");
        sdv=findViewById(R.id.main_adv);
        //sdv.setImageURI(uri);
        //重新加载的一个方法
//        DraweeController builder = (DraweeController) Fresco.newDraweeControllerBuilder()
//                .setUri(uri)
//                .setTapToRetryEnabled(true)
//                .setOldController(sdv.getController())
//                .build();
        Uri uri = Uri.parse("http://img.huofar.com/data/jiankangrenwu/shizi.gif");
        final DraweeController  draweeController =
                Fresco.newDraweeControllerBuilder()
                        .setUri(uri)
                        .setAutoPlayAnimations(true) // 设置加载图片完成后是否直接进行播放
                        .build();
        sdv.setController(draweeController);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animatable animatable = draweeController.getAnimatable();
                if(animatable.isRunning()){
                     animatable.stop();
                }else
                {
                    animatable.start();
                }
            }

        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePipeline imagePipeline = Fresco.getImagePipeline();
                imagePipeline.clearMemoryCaches();
                imagePipeline.clearDiskCaches();
// combines above two lines
                imagePipeline.clearCaches();
            }
        });
        //设置点击事件
        sdv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,SenActivity.class);
                startActivity(intent);
                //使用EventBus进行传值
                EventBus.getDefault().postSticky(tv.getText().toString());
            }
        });



    }
}
