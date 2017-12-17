package com.example.webviewbanner.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.webviewbanner.R;
import com.example.webviewbanner.activity.MainActivity;
import com.example.webviewbanner.activity.WebviewActivity;
import com.example.webviewbanner.bean.BannerBean;
import com.example.webviewbanner.bean.XiangQingBean;
import com.example.webviewbanner.precenter.ShowDataShopPrecenter;
import com.example.webviewbanner.utils.MyBannerapp;
import com.example.webviewbanner.view.IShowAddCarView;
import com.example.webviewbanner.view.IShowDataShopView;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2017/12/11.
 */

public class ShopFragment extends Fragment implements IShowDataShopView{
     Banner banner;
    List<String> list=new ArrayList<>();
    TextView home,title,miaoshu;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shop_frag, null);
        ShowDataShopPrecenter precenter=new ShowDataShopPrecenter(this);

       banner=view.findViewById(R.id.banner);
        home=view.findViewById(R.id.home);
        title=view.findViewById(R.id.title);
        miaoshu=view.findViewById(R.id.miaoshu);
        Bundle bundle = getArguments();
        String pid = bundle.getString("pid", "");
        precenter.showdata(1+"");

        return view;
    }

    @Override
    public void showshopdata(XiangQingBean bean) {
        String[] split = bean.getData().getImages().split("\\|");
        for(int i=0;i<split.length;i++){
             list.add(split[i]);
        }
        banner.isAutoPlay(true);
        //将图片集合放入，加载图片
        banner.setImages(list);

        //每隔3秒切换一次
        banner.setDelayTime(3000);
        // 加载
        banner.setImageLoader(new MyBannerapp());

        //样式小圆点
        banner.setBannerStyle(Banner.ACCESSIBILITY_LIVE_REGION_POLITE);


        banner.start();
        home.setText(bean.getSeller().getName());
        title.setText(bean.getData().getTitle());
        miaoshu.setText(bean.getData().getSubhead());


    }


}
