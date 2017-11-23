package com.example.appgouwu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.appgouwu.adaper.Myadaper;
import com.example.appgouwu.bean.ShopBean;
import com.example.appgouwu.presenter.Myviewpresenter;
import com.example.appgouwu.view.Mainview;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity implements Mainview {
    Myadaper adaper;
    Myviewpresenter presenter;
    @BindView(R.id.third_recyclerview)
    RecyclerView thirdRecyclerview;
    @BindView(R.id.third_allselect)
    CheckBox thirdAllselect;
    @BindView(R.id.third_totalprice)
    TextView thirdTotalprice;
    @BindView(R.id.third_totalnum)
    TextView thirdTotalnum;
    @BindView(R.id.third_submit)
    TextView thirdSubmit;
    @BindView(R.id.third_pay_linear)
    LinearLayout thirdPayLinear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenter = new Myviewpresenter(this);
        //获取数据
        presenter.getdata();


        adaper = new Myadaper(this);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        thirdRecyclerview.setLayoutManager(manager);
        thirdRecyclerview.setAdapter(adaper);

        //从adaper里面获取算价格和数量定义的接口
        adaper.setLinster(new Myadaper.updatalinster() {
            @Override
            public void setdata(String toa, String num,boolean allcheck) {
                //进行赋值
                thirdAllselect.setChecked(allcheck);
                thirdTotalnum.setText("一共有"+toa+"个商品");
                thirdTotalprice.setText("总价格为："+num);


            }
        });

        thirdSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent=new Intent(MainActivity.this,RecyclerActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void success(ShopBean bean) {
        // System.out.println("bean" + bean.toString());

        adaper.add(bean);
    }

    @Override
    public void error(Exception e) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.Destory();
    }

    @OnClick(R.id.third_allselect)
    public void onViewClicked() {
        //给全选的checkbox设置点击事件
        //adaper里面创建一个方法，activity里面调用这个判断选择
     adaper.selectall(thirdAllselect.isChecked());
    }
}
