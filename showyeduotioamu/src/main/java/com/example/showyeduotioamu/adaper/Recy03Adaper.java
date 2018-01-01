package com.example.showyeduotioamu.adaper;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.showyeduotioamu.R;
import com.example.showyeduotioamu.bean.ShouyeBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2017/12/30.
 */

public class Recy03Adaper extends RecyclerView.Adapter<Recy03Adaper.MyViewholder> {

    List<ShouyeBean.DataBean.DefaultGoodsListBean> list;
    Context context;
    public Recy03Adaper(Context context,List<ShouyeBean.DataBean.DefaultGoodsListBean> data){
        this.context = context;
        if (list==null){
            list= new ArrayList<>();
        }
        list.addAll(data);
        notifyDataSetChanged();
    }
    @Override
    public MyViewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = View.inflate(context, R.layout.layout_01list,null);
        Recy03Adaper.MyViewholder myviewHolder = new Recy03Adaper.MyViewholder(view);
        return myviewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewholder holder, int position) {
        holder.simpleDraweeView.setImageURI(list.get(position).getGoods_img());
        holder.textView.setText(list.get(position).getGoods_name());
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    class MyViewholder extends RecyclerView.ViewHolder{
        private final SimpleDraweeView simpleDraweeView;
        private final TextView textView;
        public MyViewholder(View itemView) {
            super(itemView);
            simpleDraweeView=itemView.findViewById(R.id.simple1_view);
            textView=itemView.findViewById(R.id.text1_view);
        }
    }
}
