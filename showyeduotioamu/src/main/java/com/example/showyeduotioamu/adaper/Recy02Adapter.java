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

public class Recy02Adapter extends RecyclerView.Adapter<Recy02Adapter.MyViewHolder> implements View.OnClickListener {
    private OnItemClickListener mOnItemClickListener = null;
    List<ShouyeBean.DataBean.Ad5Bean> list;
    Context context;
    public Recy02Adapter(Context context,List<ShouyeBean.DataBean.Ad5Bean> data){
        this.context = context;
        if (list==null){
            list= new ArrayList<>();
        }
        list.addAll(data);
        notifyDataSetChanged();
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.recy_01_grid,null);
        MyViewHolder myviewHolder = new MyViewHolder(view);
        view.setOnClickListener(this);
        return myviewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.simpleDraweeView.setImageURI(list.get(position).getImage());
        holder.textView.setText(list.get(position).getTitle());
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    @Override
    public void onClick(View view) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取position
            mOnItemClickListener.onItemClick(view,(int)view.getTag());
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private final SimpleDraweeView simpleDraweeView;
        private final TextView textView;
        public MyViewHolder(View itemView) {
            super(itemView);
            simpleDraweeView = itemView.findViewById(R.id.simple_view);
            textView = itemView.findViewById(R.id.text_view);
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
    public static interface OnItemClickListener {
        void onItemClick(View view , int position);
    }
}
