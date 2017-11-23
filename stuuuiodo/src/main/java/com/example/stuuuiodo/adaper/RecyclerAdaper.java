package com.example.stuuuiodo.adaper;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.stuuuiodo.R;
import com.example.stuuuiodo.bean.RecyclerBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2017/11/22.
 */

public class RecyclerAdaper extends RecyclerView.Adapter<RecyclerAdaper.MyViewHolder> implements View.OnClickListener{
   Context context;

    List<RecyclerBean.DataBean> list;
    public RecyclerAdaper(Context context){
        this.context=context;
    }
    public void adddata(List<RecyclerBean.DataBean> list){
      if(this.list==null){
          this.list=new ArrayList<>();

      }
      this.list.addAll(list);
        notifyDataSetChanged();



    }
    @Override
    public RecyclerAdaper.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.adaper_item,null);
        MyViewHolder holder=new MyViewHolder(view);

        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerAdaper.MyViewHolder holder, int position) {
        ImageLoader.getInstance().displayImage(list.get(position).getUserImg(),holder.iv);

        holder.tv1.setText(list.get(position).getIntroduction());
        holder.tv2.setText(list.get(position).getTitle());
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return list==null ? 0: list.size();
    }

    @Override
    public void onClick(View v) {
          if(mOnItemClickListener!=null)
          {
              mOnItemClickListener.onItemClick(v,(int)v.getTag());
          }
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView iv;
        TextView tv1;
        TextView tv2;

        public MyViewHolder(View itemView) {
            super(itemView);
            iv=itemView.findViewById(R.id.iv);
            tv1=itemView.findViewById(R.id.tv1);
            tv2=itemView.findViewById(R.id.tv2);
        }
    }
    private OnItemClickListener mOnItemClickListener = null;
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
    public static interface OnItemClickListener {
        void onItemClick(View view , int position);
    }
}
