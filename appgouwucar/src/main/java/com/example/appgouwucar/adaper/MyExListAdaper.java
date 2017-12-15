package com.example.appgouwucar.adaper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.appgouwucar.R;
import com.example.appgouwucar.SelectCarActivity;
import com.example.appgouwucar.bean.CountAndPrice;
import com.example.appgouwucar.bean.SelectCarBean;

import java.util.List;

/**
 * Created by lenovo on 2017/12/13.
 */

public class MyExListAdaper extends BaseExpandableListAdapter {
    private Context context;
   private List<SelectCarBean.DataBean> group;
    private List<List<SelectCarBean.DataBean.ListBean>> child;
    LayoutInflater infater;

    public MyExListAdaper(Context context, List<SelectCarBean.DataBean> group, List<List<SelectCarBean.DataBean.ListBean>> child) {
        this.context = context;
        this.group = group;
        this.child = child;
         infater= LayoutInflater.from(context);

    }

    @Override
    public int getGroupCount() {
        return group.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return child.get(i).size();
    }

    @Override
    public Object getGroup(int i) {
        return group.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return child.get(i).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int i, boolean b, View view, ViewGroup viewGroup) {
       View view1;
        final MyGroupViewHolder myGroupViewHolder;
        if(view==null)
        {
            view1 = infater.inflate(R.layout.group_item, null);
            myGroupViewHolder=new MyGroupViewHolder();
            myGroupViewHolder.tv=view1.findViewById(R.id.tvGroup);
            myGroupViewHolder.cbGroup= view1.findViewById(R.id.cbGroup);
            view1.setTag(myGroupViewHolder);

        } else {
            view1=view;
           myGroupViewHolder= (MyGroupViewHolder) view1.getTag();

        }
        final SelectCarBean.DataBean dataBean = group.get(i);
        myGroupViewHolder.tv.setText(dataBean.getSellerName());
        //先默认个父条目设置为未选中
        myGroupViewHolder.cbGroup.setChecked(dataBean.isChecked());
        myGroupViewHolder.cbGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //先改变bean里面的状态  改变自身
               dataBean.setChecked(myGroupViewHolder.cbGroup.isChecked());
                //改变二级列表的  先判断 如果一级列表被选中，那么子条目都要选中
                //如果没选中 反之不会选
                setGroupAndChildChecked(i,myGroupViewHolder.cbGroup.isChecked());
                //改变全选 如果所有的父条目选中 全选也要选中
                ((SelectCarActivity) context).setallchecked(AllgroupChecked());
                setPrictAndCount();
                notifyDataSetChanged();

            }
        });


        return view1;
    }

    @Override
    public View getChildView(final int i, final int i1, boolean b, View view, ViewGroup viewGroup) {
        View view1;
        final MyChildViewHolder myChildViewHolder;
        if(view==null)
        {
            view1 = infater.inflate(R.layout.child_item, null);
            myChildViewHolder= new MyChildViewHolder();
            myChildViewHolder.image=view1.findViewById(R.id.imageq);
            myChildViewHolder.tvTitle = view1.findViewById(R.id.tvTitle);
            myChildViewHolder.tvSubhead = view1.findViewById(R.id.tvSubhead);
            myChildViewHolder.tvPrice = view1.findViewById(R.id.tvPrice);
            myChildViewHolder.cbChild = view1.findViewById(R.id.cbChild);
            myChildViewHolder.btDel = view1.findViewById(R.id.btDel);
            myChildViewHolder.tvNum = view1.findViewById(R.id.tvNum);
            myChildViewHolder.ivDel = view1.findViewById(R.id.ivDel);
            myChildViewHolder.ivAdd = view1.findViewById(R.id.ivAdd);

            view1.setTag(myChildViewHolder);

        } else {
            view1=view;
            myChildViewHolder= (MyChildViewHolder) view1.getTag();

        }
        final SelectCarBean.DataBean.ListBean listBean = child.get(i).get(i1);
        String images = listBean.getImages();
        String[] split = images.split("\\|");
        Glide.with(context).load(split[0]).into(myChildViewHolder.image);
        myChildViewHolder.tvTitle.setText(listBean.getTitle());
        //给子条目设置为未选中
        myChildViewHolder.cbChild.setChecked(child.get(i).get(i1).isChecked());
        myChildViewHolder.tvSubhead.setText(listBean.getSubhead());
        myChildViewHolder.tvPrice.setText(listBean.getPrice() + "元");
        myChildViewHolder.tvNum.setText(listBean.getCount() + "");
         //给子条目的多选框设置点击事件
        myChildViewHolder.cbChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 //点击的时候先把bean的状态改变
                listBean.setChecked(myChildViewHolder.cbChild.isChecked());
                //第二个改变一级列表的状态
                //在在改变一级列表选中状态的时候要判断子条目是否全选中
                //如果二级的全部选中 那么父条目的状态也要改变
                //如果所有的一级被选中那么多选框也要选中
                //因此要写个方法 这个是判断子条目是否选中
                group.get(i).setChecked(AllChiledChecked(i));
                //第三部改变全选的状态 首先要判断一级的是否全部选中
                //因为多选的选中状态是在activity里面所以强转为activity调用里面的方法
                ((SelectCarActivity) context).setallchecked(AllgroupChecked());
                //算数量和价钱
                setPrictAndCount();
                notifyDataSetChanged();
            }
        });
        //给加号设置点击事件
        myChildViewHolder.ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //得到bean里面的数量
                int count = listBean.getCount();
                count++;
                //把bean重新赋值
                listBean.setCount(count);
                setPrictAndCount();
                notifyDataSetChanged();
            }
        });
        //给减号设置点击事件
        myChildViewHolder.ivDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = listBean.getCount();
                //先得到数量 如果数量等于小于1就让他等于1，否则减减就可以了
                if(count<=1)
                {
                    count=1;
                }else
                {
                    count--;
                }
                //重新赋值
                listBean.setCount(count);
                //算数量和价钱
                setPrictAndCount();
                notifyDataSetChanged();
            }
        });
        //删除的方法
        myChildViewHolder.btDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  //得到子条目的bean
                List<SelectCarBean.DataBean.ListBean> listBeens = child.get(i);
                //bean对象大于0说明有数据，可以执行删除的操作
              if(listBeens.size()>0){
                  //删除子条目
                  //i是父条目的position i1是子条目的position
                   listBeens.remove(i1);
              }
              //如果bean等于0的话，就说明bean里面没有数据
               if(listBeens.size()==0){
                   //没有数据了也要把当前的父条目的position移除掉
                   //child.remove(i);是解决一个问题 当前的条目是没有了，
                   //可是child里面还有东西  要把里面的也移除掉
                   child.remove(i);
                   //同样也要把父条目删除
                   //子条目都没有了，还要父条目干啥
                   group.remove(i);
               }
                setPrictAndCount();
                ((SelectCarActivity) context).setallchecked(AllgroupChecked());
                notifyDataSetChanged();
            }
        });

        return view1;
    }

    @Override
    public boolean isChildSelectable(int i, int i1)  {
        return true;
    }
    class MyGroupViewHolder{
         TextView tv;
        CheckBox cbGroup;

    }
    class MyChildViewHolder{
        ImageView image;
        TextView tvTitle;
        TextView tvSubhead;
        TextView tvPrice;
        CheckBox cbChild;
        Button btDel;
        TextView tvNum;
        ImageView ivDel;
        ImageView ivAdd;
    }

    /**
     * 判断子条目是否全部被选中 如果全部选中则把父条目的选中
     * 需要穿一个子条目的position进去
     */
     private boolean AllChiledChecked(int i2){
         //得到子条目的集合
         List<SelectCarBean.DataBean.ListBean> listBeen = child.get(i2);
         //遍历集合每一个子条目
         for (int i = 0; i <listBeen.size() ; i++) {
             //如果有一个条目没有选中返回false 否则 在遍历完以后返回true
                 if(!listBeen.get(i).isChecked()){
                     return  false;
                 }


         }
         return  true;
         

     }
    /**
     * 选中一级，来改变二级
     * int i2 是要改变那个条目的position
     */
    private void setGroupAndChildChecked(int i2,boolean bool){
        //得到二级列表的bean
        List<SelectCarBean.DataBean.ListBean> listBeen = child.get(i2);
        //遍历 得到每一个
        for (int i = 0; i < listBeen.size(); i++) {
            //有一个选中就设置为true 没有就false 需要传一个booler值进去
             listBeen.get(i).setChecked(bool);
        }
    }

    /**
     * 判断全选 要先判断父条目是否选中
     * 这个是如果父条目全部选中则全选也要选中
     */
    private  boolean AllgroupChecked(){
        if(group.size()==0)
        {
            return  false;
        }
        //遍历所有的父条目
        for (int i = 0; i < group.size(); i++) {
            //判断每一个父条目是否选中

            if(!group.get(i).isChecked()){
                return false;
            }
        }
        return  true;
    }

    //把总数和价钱发送到activity里面显示
    //setPrictAndCount() 这个方法是在本adaper里面使用的
    public void setPrictAndCount(){
        //可以把activity强制出来用context，因为这个context就是从avtivity里面传进来的
        //调用activity里面的方法setPriceAndCount
        ((SelectCarActivity) context).setPriceAndCount(cout());
    }



    /**
     * 计算选择的钱和数量
     * CountAndPrice是一个类 在bean里面
     */
      private CountAndPrice cout(){
          //先设置个默认值
          double price = 0;
          int count=0;
          //先遍历父条目对象
          for (int i = 0; i < group.size(); i++) {
              //然后得到每一个父条目中的子条目
              List<SelectCarBean.DataBean.ListBean> listBeens = child.get(i);
              //遍历子条目 得到子条目里面每一个条目
              for (int j=0;j<listBeens.size();j++)
              {
                  //判断是否为选中
                  if(listBeens.get(j).isChecked()){
                      //选中的话就算出价格和数量
                         price+=listBeens.get(j).getPrice() * listBeens.get(j).getCount();
                        count+=listBeens.get(j).getCount();
                  }
              }

          }
          return  new CountAndPrice(price,count);
      }

    //是点击全部选中按钮的功能，点击它所有的多选框选中需要穿一个booler值
    public  void AllOrNoChecked(boolean bool){
        //先遍历整个父条目
        for (int i = 0; i <group.size() ; i++) {
            //判断父条目选中或者不选
            group.get(i).setChecked(bool);
            //调用之前的方法这个方法是拿父条目来选中子条目的
            //全选选中，那么父条目会选中 则子条目也会选中
            setGroupAndChildChecked(i,bool);

        }
        //计算
        setPrictAndCount();
        //一定要刷新
        notifyDataSetChanged();

    }

    }


