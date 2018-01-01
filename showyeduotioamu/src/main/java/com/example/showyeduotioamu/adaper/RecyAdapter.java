package com.example.showyeduotioamu.adaper;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.showyeduotioamu.R;
import com.example.showyeduotioamu.bean.ShouyeBean;
import com.example.showyeduotioamu.utils.GildeImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.security.KeyStore;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2017/12/30.
 */

public class RecyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    int ONE = 0;
    int TWO = 1;
    int THREE = 2;
    int FOUR = 3;
    Context context;
    List<ShouyeBean.DataBean> list;
    public RecyAdapter(Context context,List<ShouyeBean.DataBean> data){
        this.context=context;
        if (list==null){
            list= new ArrayList<>();
        }
        list.addAll(data);
        //刷新适配器的方法
        notifyDataSetChanged();

    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==ONE){

            View view = View.inflate(context, R.layout.recy_01,null);
            ViewHolder1 viewHolder1 = new ViewHolder1(view);
            return viewHolder1;
        }else if(viewType==TWO){
            View view = View.inflate(context, R.layout.recy_02,null);
            ViewHolder2 viewHolder2 = new ViewHolder2(view);
            return viewHolder2;
        }else if(viewType==THREE){
            View view = View.inflate(context, R.layout.recy_03,null);
            ViewHolder3 viewHolder3 = new ViewHolder3(view);
            return viewHolder3;
        }else {
            View view = View.inflate(context, R.layout.recy_04,null);
            ViewHolder4 viewHolder4 = new ViewHolder4(view);
            return viewHolder4;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //判断viewholder
        if(holder instanceof ViewHolder1){
            List<String> images = new ArrayList<>();

            if(list!=null) {
                images.add(list.get(0).getAd1().get(0).getImage());
                images.add(list.get(0).getAd1().get(1).getImage());
                images.add(list.get(0).getAd1().get(2).getImage());
                images.add(list.get(0).getAd1().get(3).getImage());
                ViewHolder1 holder1 = (ViewHolder1) holder;
                //设置图片加载器
                holder1.banner.setImageLoader(new GildeImageLoader());
                //设置图片集合
                holder1.banner.setImages(images);
                //banner设置方法全部调用完毕时最后调用
                holder1.banner.start();
            }
        }else if (holder instanceof ViewHolder2){
            if(list!=null) {
                ViewHolder2 holder2 = (ViewHolder2) holder;
                Recy02Adapter recy02Adapter = new Recy02Adapter(context,list.get(0).getAd5());
                recy02Adapter.setOnItemClickListener(new Recy02Adapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Toast.makeText(context, "recycler的点击"+position, Toast.LENGTH_LONG).show();
                    }
                });
                holder2.recyclerView.setLayoutManager(new GridLayoutManager(context,4));

                holder2.recyclerView.setAdapter(recy02Adapter);
            }

        }else if (holder instanceof ViewHolder3){
            List<String> images = new ArrayList<>();
            if(list!=null) {

                images.add(list.get(0).getSubjects().get(0).getImage());
                images.add(list.get(0).getSubjects().get(1).getImage());
                images.add(list.get(0).getSubjects().get(2).getImage());
                images.add(list.get(0).getSubjects().get(3).getImage());
                images.add(list.get(0).getSubjects().get(4).getImage());
                images.add(list.get(0).getSubjects().get(5).getImage());
                ViewHolder3 holder3 = (ViewHolder3) holder;
                //设置图片加载器
                holder3.banner.setImageLoader(new GildeImageLoader());
                //设置图片集合
                holder3.banner.setImages(images);
                //banner设置方法全部调用完毕时最后调用
                holder3.banner.start();
                holder3.banner.setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {
                        Toast.makeText(context, "banner的点击"+position, Toast.LENGTH_LONG).show();
                    }
                });

            }


        }else
         {
          if(list!=null)
          {
              ViewHolder4 viewHolder4= (ViewHolder4) holder;
              Recy03Adaper recy03Adaper=new Recy03Adaper(context,list.get(0).getDefaultGoodsList());
             viewHolder4.recyclerView.setLayoutManager(new GridLayoutManager(context,2));
              viewHolder4.recyclerView.setAdapter(recy03Adaper);
          }
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    @Override
    public int getItemViewType(int position) {
        //判断是哪种条目类型
        if(position==0){
            return ONE;
        }else if(position==1){
            return TWO;
        }else if(position==2){
            return THREE;
        }else {
            return FOUR;
        }
    }

    public static class ViewHolder1 extends RecyclerView.ViewHolder{

        private final Banner banner;

        public ViewHolder1(View itemView) {
            super(itemView);
            banner = itemView.findViewById(R.id.banner);
        }
    }
    public static class ViewHolder2 extends RecyclerView.ViewHolder{


        private final RecyclerView recyclerView;

        public ViewHolder2(View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.recy3_recyciew);

        }
    }
    public static class ViewHolder3 extends RecyclerView.ViewHolder{
        private final Banner banner;

        public ViewHolder3(View itemView) {
            super(itemView);
            banner = itemView.findViewById(R.id.banner);
        }
    }
    public static class ViewHolder4 extends RecyclerView.ViewHolder{
        private final RecyclerView recyclerView;

        public ViewHolder4(View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.recy4_recyciew);
        }
    }
}
