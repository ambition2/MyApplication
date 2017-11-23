
package com.example.chuanzhi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

public class OtherActivity extends Activity {


    ImageView iv;

    TextView name;

    TextView qq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
        iv=findViewById(R.id.ivv1);
        name=findViewById(R.id.name);
        qq=findViewById(R.id.qq);

        Intent intent = getIntent();


        String userImg = intent.getStringExtra("userImg");
        String title = intent.getStringExtra("title");

        ImageLoader.getInstance().displayImage(userImg,iv);
        name.setText(title);
    }
}
