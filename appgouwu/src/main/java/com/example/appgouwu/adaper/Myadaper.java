package com.example.appgouwu.adaper;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.appgouwu.PlusView;
import com.example.appgouwu.R;
import com.example.appgouwu.bean.ShopBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lenovo on 2017/11/21.
 */

public class Myadaper extends RecyclerView.Adapter<Myadaper.MyViewHolder> {
    Context context;
    List<ShopBean.DataBean.ListBean> list;
    //显示商户 用一个map集合在bean里面增加三个属性，来判断商户的的选择，和商品的选择
    //和来显示和隐藏商户
    // 1 显示商家  2 隐藏商家
//    private int isFirst;

    // true 表示商家选中 false 相反
//    private boolean shopSelected;

    // true 表示 当前商品是选中的 false 相反
//    private boolean itemSelected;

    private Map<String,String> map=new HashMap<>();

    public Myadaper(Context context) {
        this.context = context;
    }
     //add是用来获取数据源的
    public  void add(ShopBean bean){
        if(this.list==null){
            this.list=new ArrayList<>();
        }
        for (ShopBean.DataBean shop : bean.getData()) {
            //显示商户 遍历得到商户的名字和id
            map.put(shop.getSellerid(),shop.getSellerName());
            // 遍历商品
            for (int i = 0; i < shop.getList().size(); i++) {
                this.list.add(shop.getList().get(i));
            }
        }
        //用来设置数据源和显示商家
         setFirst(this.list);
        notifyDataSetChanged();

    }


    private  void setFirst(List<ShopBean.DataBean.ListBean> list){
        //来判断商户隐藏和显示
        if(list.size() > 0){
            list.get(0).setIsFirst(1);
            for(int i=1;i<list.size();i++){
                if(list.get(i).getSellerid() == list.get(i-1).getSellerid()){
                    list.get(i).setIsFirst(2);
                }else{
                    list.get(i).setIsFirst(1);
                    //解决一个bug这个是删除的时候不会勾选
                    //所以在这里判断一下条目的选择状态
                    if(list.get(i).isItemSelected()){
                        list.get(i).setShopSelected(list.get(i).isItemSelected());

                    }

                }
            }

        }

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.adapter_layout, null);
        MyViewHolder holder=new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final Myadaper.MyViewHolder holder, final int position) {
        if(list.get(position).getIsFirst() == 1){
            //显示商家
            holder.shopCheckbox.setVisibility(View.VISIBLE);
            holder.tvItemShopcartShopname.setVisibility(View.VISIBLE);
            holder.shopCheckbox.setChecked(list.get(position).isShopSelected());

//            显示商家的名称
//            list.get(position).getSellerid() 取到商家的id
//            map.get（）取到 商家的名称
            holder.tvItemShopcartShopname.setText(map.get(String.valueOf(list.get(position).getSellerid())));
        } else {
            holder.shopCheckbox.setVisibility(View.GONE);
            holder.tvItemShopcartShopname.setVisibility(View.GONE);
        }

    //给商品设置选择，和商户的点击事件进行的联系
        holder.itemCheckbox.setChecked(list.get(position).isItemSelected());


   //得到数据的图片，进行一个分割
        String[] url=list.get(position).getImages().split("\\|");
        //用来显示图片
        ImageLoader.getInstance().displayImage(url[0],holder.ima);
        holder.name.setText(list.get(position).getTitle());
        holder.jiage.setText(list.get(position).getPrice()+"");
      //解决editText的复用 所以在这里也要更新一下
        holder.plusViewId.setEditText(list.get(position).getNum());


        //s商户的多选框的点击事件

        holder.shopCheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //在bean里面定义的setShopSelected给它设置上
                list.get(position).setShopSelected(holder.shopCheckbox.isChecked());
             //遍历集合
                for(int i=0;i<list.size();i++){
                    //如果list的商户id和遍历的商户id相等的话
                    if(list.get(position).getSellerid() == list.get(i).getSellerid()){
                        //list里面设置item为选中
                        list.get(i).setItemSelected(holder.shopCheckbox.isChecked());
                    }
                }

                notifyDataSetChanged();
                sun(list);
//                notifyDataSetChanged();
            }
        });





        //判断商品的选中状态 如果有一个商品未选中则商户选择不显示
        holder.itemCheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            //给商品增加选择框得到当前选中的这个数据源
                list.get(position).setItemSelected(holder.itemCheckbox.isChecked());

                 //通过来个循环来判断那个商品没有选中
                for(int i=0;i<list.size();i++){
                    //
                    for (int j=0;j<list.size();j++){
                        //如果来个id相同或者没有被选中
                        if(list.get(i).getSellerid() == list.get(j).getSellerid() && !list.get(j).isItemSelected()){
                            list.get(i).setShopSelected(false);
                            break;
                        }else {
                            list.get(i).setShopSelected(true);
                        }
                    }
                }

                notifyDataSetChanged();
                sun(list);


            }
        });




        holder.itemDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.remove(position);
                //为什么要调用这个方法因为一个商家里面如果有俩个商品
                //直接删除商品，商户可以不用管，删除第一个的话也可能商户也删除
                //所以在这里要在更新数据源setFirst(list);这个方法
                setFirst(list);
         notifyDataSetChanged();
                sun(list);
            }
        });




        //给EditText设置默认值 加减号自定义的view
        //这个方法对应PlusView的接口，得到定义的接口来得到count值
        holder.plusViewId.setListener(new PlusView.ClickListener() {
            @Override
            public void click(int count) {
                //给数量设置一个默认值
                    list.get(position).setNum(count);
                notifyDataSetChanged();
                sun(list);
            }
        });



    }

    @Override
    public int getItemCount() {
        return list==null ? 0: list.size();
    }




    //商品的总价和总数量
    private  void  sun(List<ShopBean.DataBean.ListBean> list){
        int tonum=0;
        float tonmonry=0.0f;
        //先把allcheck默认设置为true
        boolean allcheck=true;
        for (int i=0;i<list.size();i++){
              if(list.get(i).isItemSelected()){
                  //这里的总数要从list里面取
                  tonum+=list.get(i).getNum();
                  tonmonry+=list.get(i).getNum() * list.get(i).getPrice();
              }else
              {
                  allcheck=false;
              }
        }
        //调用回调的方法
        linster.setdata(tonum+"",tonmonry+"",allcheck);

    }
    //全选的功能
    public void selectall(boolean check){
        for (int i=0;i<list.size();i++){
            //从list里面的得到商户个条目的checked
                  list.get(i).setShopSelected(check);
                  list.get(i).setItemSelected(check);
        }
        notifyDataSetChanged();
        sun(list);
    }




     class MyViewHolder extends  RecyclerView.ViewHolder{
        @BindView(R.id.view)
        View view;
        @BindView(R.id.shop_checkbox)
        CheckBox shopCheckbox;
        @BindView(R.id.tv_item_shopcart_shopname)
        TextView tvItemShopcartShopname;
        @BindView(R.id.ll_shopcart_header)
        LinearLayout llShopcartHeader;
        @BindView(R.id.item_checkbox)
        CheckBox itemCheckbox;
        @BindView(R.id.image)
        ImageView ima;
        @BindView(R.id.price)
        TextView jiage;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.tv_item_shopcart_cloth_size)
        TextView tvItemShopcartClothSize;
        @BindView(R.id.plus_view_id)
        PlusView plusViewId;
        @BindView(R.id.item_del)
        ImageView itemDel;

        MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    //接口回调 用于算总价个数量 回调到Activity
    public  updatalinster linster;
    public  void setLinster(updatalinster linster){
          this.linster=linster;
    }
     public  interface updatalinster{
         //接口回调把一个状态回调出去
     public  void setdata(String toa,String num,boolean allcheck);
    }


}
