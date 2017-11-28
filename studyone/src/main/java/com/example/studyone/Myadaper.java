package com.example.studyone;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.studyone.bean.OneDataBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2017/11/28.
 */

public class Myadaper extends RecyclerView.Adapter <Myadaper.MyviewHolder>{
    Context context;
    List<OneDataBean.DataBean> list;
     public  Myadaper(Context context){
          this.context=context;
     }
     public  void add(List<OneDataBean.DataBean> list){
         if(this.list==null){
             this.list=new ArrayList<>();

         }
         this.list.addAll(list);
         notifyDataSetChanged();

     }
    @Override
    public MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.adaper_item,null);
        MyviewHolder holder=new MyviewHolder(view);


        return holder;
    }

    @Override
    public void onBindViewHolder(MyviewHolder holder, int position) {
      String [] img=list.get(position).getImages().split("\\|");
        ImageLoader.getInstance().displayImage(img[0],holder.iv);
        holder.title.setText(list.get(position).getTitle());
        holder.price.setText(list.get(position).getPrice()+"");
        holder.title1.setText(list.get(position).getSubhead());
        holder.price1.setText(list.get(position).getBargainPrice()+"");

    }

    @Override
    public int getItemCount() {
        return list==null ? 0 : list.size();
    }

    class MyviewHolder extends  RecyclerView.ViewHolder{
        ImageView iv;
        TextView title;
        TextView price;
        TextView title1;
        TextView price1;

        public MyviewHolder(View itemView) {
            super(itemView);
            iv=itemView.findViewById(R.id.iv);
            title=itemView.findViewById(R.id.title);
            price=itemView.findViewById(R.id.price);
            title1=itemView.findViewById(R.id.title1);
            price1=itemView.findViewById(R.id.price1);

        }
    }
}
