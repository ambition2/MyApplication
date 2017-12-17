package com.example.webviewbanner.adaper;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.webviewbanner.R;
import com.example.webviewbanner.bean.Catagorybean;

import java.util.List;

/**
 * Created by lenovo on 2017/12/1.
 */

public class MylistViewAdaper extends BaseAdapter{
    private Context context;
    private List<Catagorybean.DataBean> list;
    private final LayoutInflater inflater;

    public MylistViewAdaper(Context context, List<Catagorybean.DataBean> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        View view;
        if (convertView == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.listview_item, null);
            holder.tv = view.findViewById(R.id.tv);
            holder.ll=view.findViewById(R.id.ll);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (ViewHolder) view.getTag();
        }
        Catagorybean.DataBean dataBean = list.get(position);
        holder.tv.setText(dataBean.getName());
        if(dataBean.ischecked()){
            holder.ll.setBackgroundColor(Color.parseColor("#33000000"));
        }else
        {
            holder.ll.setBackgroundColor(Color.WHITE);
        }
        return view;
    }

    class ViewHolder {
        TextView tv;
        LinearLayout ll;
    }
    //这是设置背景的方法
    public  void setchanggeitem(int position){
        //先遍历这个数据
         for(int i=0;i<list.size();i++){
             //得到bean对象
             Catagorybean.DataBean dataBean = list.get(i);
             //默认设置为false
             dataBean.setIschecked(false);
         }
         //遍历完以后，得到当前的选中的position
        Catagorybean.DataBean dataBean = list.get(position);
        //设置为true
        dataBean.setIschecked(true);
        //然后刷新
        notifyDataSetChanged();
    }
}
