package com.example.appgouwucar.fragment;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.appgouwucar.R;
import com.example.appgouwucar.bean.XiangqingBean;
import com.example.appgouwucar.precener.XiangPrecneter;
import com.example.appgouwucar.utils.MyBanner;
import com.example.appgouwucar.view.IShowXiangView;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2017/12/13.
 */

public class ShopFrangment extends Fragment implements IShowXiangView {
    TextView tv;
    Banner banner;
    List<String> list=new ArrayList<>();
    TextView title1;
    TextView miaoshu;
    TextView price;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.shop_fragment, null);
         banner= view.findViewById(R.id.banner);
        title1=view.findViewById(R.id.title1);
        miaoshu=view.findViewById(R.id.miaoshu);
        price=view.findViewById(R.id.price);
        Bundle bundle = getArguments();
        String pid = bundle.getString("pid");
        XiangPrecneter precneter=new XiangPrecneter(this);
        precneter.showdata(pid);

        return view;
    }

    @Override
    public void showdata(XiangqingBean bean) {

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
        banner.setImageLoader(new MyBanner());

        //样式小圆点
        banner.setBannerStyle(Banner.ACCESSIBILITY_LIVE_REGION_POLITE);


        banner.start();
        TextPaint paint = price.getPaint();
        paint.setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        title1.setText(bean.getData().getTitle());
        miaoshu.setText(bean.getSeller().getName());
        price.setText(bean.getData().getPrice()+"");
    }
}
