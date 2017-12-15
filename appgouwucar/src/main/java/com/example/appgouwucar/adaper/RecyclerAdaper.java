package com.example.appgouwucar.adaper;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.appgouwucar.R;
import com.example.appgouwucar.bean.RecyclerBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by lenovo on 2017/12/11.
 */

public class RecyclerAdaper extends RecyclerView.Adapter<RecyclerAdaper.MyViewholder> implements View.OnClickListener{
    Context context;
    List<RecyclerBean.DataBean> list;
    private OnItemClickListener mOnItemClickListener = null;
    public  RecyclerAdaper(Context context,List<RecyclerBean.DataBean> list){
        this.context=context;
        this.list=list;
    }

    @Override
    public MyViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
       //加载布局
        View view = View.inflate(context, R.layout.recycler_adaper, null);
        MyViewholder holder=new MyViewholder(view);
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewholder holder, int position) {
        //给控件赋值
        String[] split = list.get(position).getImages().split("\\|");
        ImageLoader.getInstance().displayImage(split[0], holder.iv);
        holder.tv1.setText(list.get(position).getTitle());

        holder.tv2.setText(list.get(position).getPrice()+"");
        holder.itemView.setTag(position);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onClick(View view) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取position
            mOnItemClickListener.onItemClick(view,(int)view.getTag());
        }
    }


    class MyViewholder extends  RecyclerView.ViewHolder{
      ImageView iv;
        TextView tv1;
        TextView tv2;
        //找控件
        public MyViewholder(View itemView) {
            super(itemView);
           iv=itemView.findViewById(R.id.iv);
           tv1=itemView.findViewById(R.id.tv1);
            tv2=itemView.findViewById(R.id.tv2);

        }
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public static interface OnItemClickListener {
        void onItemClick(View view , int position);
    }


}
