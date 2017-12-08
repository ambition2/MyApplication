package com.example.donghua;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;

public class MainActivity extends Activity {
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv=findViewById(R.id.imageView);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // model1();第一个动画集合
                model2();
            }
        });
    }

    private void model2() {
        AnimatorSet animatorset=new AnimatorSet();
        //平移
        ObjectAnimator transy = ObjectAnimator.ofFloat(iv, View.TRANSLATION_Y, 0, 150);
        //旋转
        ObjectAnimator rotateq = ObjectAnimator.ofFloat(iv, View.ROTATION, 0, 360);
        //缩放
        ObjectAnimator aFloatx = ObjectAnimator.ofFloat(iv, View.SCALE_X, 0.3f, 1.0f);
         //透明度
        ObjectAnimator aFloatq = ObjectAnimator.ofFloat(iv, View.ALPHA, 0.3f, 1.0f);
        //playTogether这个属性是一起执行
       // animatorset.playTogether(transy,rotateq);
        //playSequentially是按照顺序执行一个一个执行
        //animatorset.playSequentially(transy,rotateq);
        //play只能执行一个 after先执行,with平移和缩放一起执行,before最后执行
        //animatorset.play(transy).with(rotateq).before(aFloatq).after(aFloatx);
        //设置每个动画开始的时间
        transy.setStartDelay(1000l);
        rotateq.setStartDelay(1000l);
        animatorset.playSequentially(transy,rotateq);
        animatorset.setDuration(3000);
        animatorset.start();

    }

    private void model1() {
        //平移
        PropertyValuesHolder trans = PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, 0, 150);
       //旋转
        PropertyValuesHolder rotate = PropertyValuesHolder.ofFloat(View.ROTATION, 0, 360);
       //缩放
        PropertyValuesHolder scale = PropertyValuesHolder.ofFloat(View.SCALE_X, 0.3f, 1.0f);
        //透明度
        PropertyValuesHolder aFloat = PropertyValuesHolder.ofFloat(View.ALPHA, 0.3f, 1.0f);
        //类似于一个集合，把动画放进去
        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(iv, trans, rotate, scale, aFloat);
        //设置时间
        objectAnimator.setDuration(9000);
        //这个插值器这个类似于弹簧的效果
        objectAnimator.setInterpolator(new BounceInterpolator());
        objectAnimator.start();
    }
}
