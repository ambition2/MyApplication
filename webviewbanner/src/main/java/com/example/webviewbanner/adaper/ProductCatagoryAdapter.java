package com.example.webviewbanner.adaper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.example.webviewbanner.R;
import com.example.webviewbanner.bean.ProductCatagorybean;

import java.util.List;



/**
 * Created by peng on 2017/12/1.
 */

public class ProductCatagoryAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<ProductCatagorybean.DataBean> group;
    private List<List<ProductCatagorybean.DataBean.ListBean>> child;
    private LayoutInflater inflater;


    public ProductCatagoryAdapter(Context context, List<ProductCatagorybean.DataBean> group, List<List<ProductCatagorybean.DataBean.ListBean>> child) {
        this.context = context;
        this.group = group;
        this.child = child;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getGroupCount() {
        return group.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return group.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return child.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder holder;
        View view;
        if (convertView == null) {
            holder = new GroupViewHolder();
            view = inflater.inflate(R.layout.product_catagory_parent, null);
            holder.tv = view.findViewById(R.id.tv);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (GroupViewHolder) view.getTag();
        }
        ProductCatagorybean.DataBean dataBean = group.get(groupPosition);
        holder.tv.setText(dataBean.getName());
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder holder;
        View view;
        if (convertView == null) {
            holder = new ChildViewHolder();
            view = inflater.inflate(R.layout.product_catagory_child, null);
            holder.gv = view.findViewById(R.id.gv);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (ChildViewHolder) view.getTag();
        }
        List<ProductCatagorybean.DataBean.ListBean> listBeen = child.get(groupPosition);
        GvAdapter adapter = new GvAdapter(context, listBeen);
        holder.gv.setAdapter(adapter);
        holder.gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                linster.setdata(view,i);
            }
        });

        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {

        return true;
    }

    class GroupViewHolder {
        TextView tv;
    }

    class ChildViewHolder {
        GridView gv;
    }

    public  updatalinster linster;
    public  void setLinster(updatalinster linster){
        this.linster=linster;
    }
    public  interface updatalinster{
        //接口回调把一个状态回调出去
        public  void setdata(View view,int position);
    }


}
