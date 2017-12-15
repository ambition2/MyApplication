package com.example.appgouwucar;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.appgouwucar.adaper.MyExListAdaper;
import com.example.appgouwucar.bean.CountAndPrice;
import com.example.appgouwucar.bean.SelectCarBean;
import com.example.appgouwucar.precener.SelectCarPrecenter;
import com.example.appgouwucar.view.IShowSelectCarView;

import java.util.List;

public class SelectCarActivity extends Activity implements IShowSelectCarView{
 ExpandableListView lv;
    TextView  tvTotal;
    TextView tvCount;
    MyExListAdaper adaper;
    CheckBox cb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_car);
        //3629
        initdata();
        SelectCarPrecenter precenter=new SelectCarPrecenter(this);
        SharedPreferences user = getSharedPreferences("user", Context.MODE_PRIVATE);
        String uid = user.getString("uid", "");
        precenter.selectcar(uid);
        //是全部选中
        cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //如果多选选中则会调用adaper全选里面的方法 反之 不会
                adaper.AllOrNoChecked(cb.isChecked());
            }
        });
    }

    private void initdata() {
        lv=findViewById(R.id.lv);
        tvTotal=findViewById(R.id.tvTotal);
        tvCount=findViewById(R.id.tvCount);
        cb=findViewById(R.id.cb);
    }
    //在avtivity里面显示总数和计算 得到adaper里面的方法
    public void setPriceAndCount(CountAndPrice countAndPrice){
          tvTotal.setText("合计"+countAndPrice.getPrice());
          tvCount.setText("去结算（"+countAndPrice.getCount()+"）");
    }
    //这个方法是如果一级列表全部选中则全选也选中
    public void setallchecked(boolean bool){
        cb.setChecked(bool);
    }



    @Override
    public void showselectcar(List<SelectCarBean.DataBean> group, List<List<SelectCarBean.DataBean.ListBean>> child) {
        adaper =new MyExListAdaper(this,group,child);
        lv.setAdapter(adaper);
        lv.setGroupIndicator(null);
        for (int i = 0; i < group.size(); i++) {
            lv.expandGroup(i);

        }

    }

}
