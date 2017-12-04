package com.example.webviewbanner.adaper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (ViewHolder) view.getTag();
        }
        Catagorybean.DataBean dataBean = list.get(position);
        holder.tv.setText(dataBean.getName());
        return view;
    }

    class ViewHolder {
        TextView tv;
    }
}
