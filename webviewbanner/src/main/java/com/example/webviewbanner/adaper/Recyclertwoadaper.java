package com.example.webviewbanner.adaper;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.webviewbanner.R;
import com.example.webviewbanner.bean.RecyclerBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by lenovo on 2017/12/5.
 */

public class Recyclertwoadaper extends RecyclerView.Adapter<Recyclertwoadaper.MyTwoholder> implements View.OnClickListener {
    private Context context;
    private List<RecyclerBean.DataBean> list;
    private OnTwoItemClickListener OnTwoItemClickListener = null;



    public Recyclertwoadaper(Context context, List<RecyclerBean.DataBean> list) {
        this.context = context;
        this.list = list;

    }
    @Override
    public MyTwoholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.twoview,null);
        //holder类new出来
        MyTwoholder myTwoholder=new MyTwoholder(view);
        //返回这个holder
        view.setOnClickListener(this);
        return myTwoholder;
    }

    @Override
    public void onBindViewHolder(MyTwoholder holder, int position) {
        String[] split = list.get(position).getImages().split("\\|");
        ImageLoader.getInstance().displayImage(split[0],holder.ivv1);
        holder.tvv1.setText(list.get(position).getTitle());
        holder.itemView.setTag(position);


    }

    @Override
    public int getItemCount() {
        return list==null ? 0: list.size();
    }

    @Override
    public void onClick(View view) {
        if (OnTwoItemClickListener != null) {
            //注意这里使用getTag方法获取position
            OnTwoItemClickListener.onTwoItemClick(view,(int)view.getTag());
        }
    }

    class MyTwoholder extends RecyclerView.ViewHolder{

        ImageView ivv1;
        TextView tvv1;
        public MyTwoholder(View itemView) {
            super(itemView);
            ivv1= itemView.findViewById(R.id.ivv1);
            tvv1=  itemView.findViewById(R.id.tvv1);
        }
    }
    public void setOnTwoItemClickListener(OnTwoItemClickListener listener) {
        this.OnTwoItemClickListener = listener;
    }
    public interface OnTwoItemClickListener {
        void onTwoItemClick(View view , int position);
    }

}
