package com.example.webviewbanner.adaper;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.webviewbanner.R;
import com.example.webviewbanner.bean.ProductCatagorybean;
import com.example.webviewbanner.bean.RecyclerBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2017/12/5.
 */

public class RecyclerAdaper extends RecyclerView.Adapter<RecyclerAdaper.MyviewHolder> implements View.OnClickListener {
    private Context context;
    private List<RecyclerBean.DataBean> list;
    private OnItemClickListener mOnItemClickListener = null;



    public RecyclerAdaper(Context context, List<RecyclerBean.DataBean> list) {
        this.context = context;
        this.list = list;

    }

    @Override
    public MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.recycler_one, null);
        //holder类new出来
        MyviewHolder holder = new MyviewHolder(view);
        //返回这个holder
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyviewHolder holder, int position) {

        //给控件赋值
        //裁剪字符串，因为这个图片的url是好几个，所以要分割，得到每一个
        String[] split = list.get(position).getImages().split("\\|");
         ImageLoader.getInstance().displayImage(split[0],holder.ivv);
        holder.tvv.setText(list.get(position).getTitle());
        holder.itemView.setTag(position);

    }

    @Override
    public int getItemCount() {
        return list==null ? 0 : list.size();
    }

    @Override
    public void onClick(View view) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取position
            mOnItemClickListener.onItemClick(view,(int)view.getTag());
        }
    }

    class MyviewHolder extends RecyclerView.ViewHolder{

        ImageView ivv;
        TextView tvv;

        public MyviewHolder(View itemView) {
            super(itemView);
            ivv = itemView.findViewById(R.id.ivv);
            tvv = itemView.findViewById(R.id.tvv);
        }
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
      public interface OnItemClickListener {
        void onItemClick(View view , int position);
    }

}
