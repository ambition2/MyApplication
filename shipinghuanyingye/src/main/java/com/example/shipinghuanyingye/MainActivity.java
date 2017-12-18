package com.example.shipinghuanyingye;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {
 CustomVideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        videoView=findViewById(R.id.video);
         initview();
    }

    private void initview() {
        //设置播放加载路径
        videoView.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.w));
        //播放
        videoView.start();
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                Toast.makeText(MainActivity.this, "你好!", Toast.LENGTH_LONG).show();
                Intent intent=new Intent(MainActivity.this,VideoActivity.class);
                startActivity(intent);
            }
        });


    }
}
