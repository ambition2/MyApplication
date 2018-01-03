package com.example.filetouxiang;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.filetouxiang.bean.PeopleBean;
import com.example.filetouxiang.presenter.ShowPercneter;
import com.example.filetouxiang.view.IShowDataView;
import com.jauker.widget.BadgeView;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class ShowyeActivity extends Activity implements IShowDataView {
  ImageView ivv;
    TextView name;
    ShowPercneter percneter;
    List<PeopleBean.DataBean> list=new ArrayList<>();
    int flag=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showye);
        ivv=findViewById(R.id.ivvv);
        name=findViewById(R.id.name);
        percneter=new ShowPercneter(this);

//        percneter.showdata();
        //数字提示
        BadgeView badgeView=new BadgeView(this);
        badgeView.setTargetView(ivv);
        badgeView.setBadgeCount(20);
        badgeView.setBadgeGravity(Gravity.RIGHT);



    }
  //得到焦点
    //反回来的时候，这个avtivity会重新获得焦点，然后再去重新调用网络请求p层的方法
    //相当于再把这个activity在刷新一遍
    @Override
    protected void onResume() {
        super.onResume();
        Log.i("---------------", "onResume: ");
        percneter.showdata();
    }

    @Override
    public void shiwdata(final PeopleBean bean) {
//          list.add(bean.getData());
     //一开始在这里写的时候是把bean放到了list里面
        //一单重新执行这个方法的时候，list里面就会增加一天
        //而我下面给图片赋值的时候一直是取得第0跳，所以图片就不会再重新加载
        //直接使用bean给他赋值就好了
        ImageLoader.getInstance().displayImage(bean.getData().getIcon(),ivv);
//        name.setText(list.get(0).getUsername());
        ivv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ShowyeActivity.this,MainActivity.class);
                intent.putExtra("image",bean.getData().getIcon());
//                flag
                startActivity(intent);

            }
        });
    }
}
