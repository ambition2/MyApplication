package com.example.chenyunfei20171129.adaper;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chenyunfei20171129.R;
import com.example.chenyunfei20171129.bean.TwoDataBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2017/11/29.
 */

public class MyreviewAdaper extends RecyclerView.Adapter {
    Context context;
    List<TwoDataBean.DataBean> list;
    public  MyreviewAdaper(Context context){
        this.context=context;
    }
    public  void add(TwoDataBean bean){
        if(this.list==null)
        {
            this.list=new ArrayList<>();
        }
        for (int i=0;i<bean.getData().size();i++){
            list.add(bean.getData().get(i));
        }

        notifyDataSetChanged();

    }
    public int getItemViewType(int position) {
        if(position%2==0)
        {
            return  0;
        }else
            return 1;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==0){
              View view1=LayoutInflater.from(context).inflate(R.layout.frag_item1,null);
             MyviewHolder1 holder1=new MyviewHolder1(view1);
            return  holder1;
        }else
        {
            View view2=LayoutInflater.from(context).inflate(R.layout.frag_item2,null);
            MyviewHolder2 holder2=new MyviewHolder2(view2);
            return  holder2;
        }



    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof MyviewHolder1){
            MyviewHolder1 holder1 = (MyviewHolder1) holder;
            //设置

            String[]  split = list.get(position).getImages().split("\\|");
            ImageLoader.getInstance().displayImage(split[0],holder1.iv);
            holder1.tv1.setText(list.get(position).getTitle());
            holder1.tv2.setText(list.get(position).getPrice()+"");



        }else{
            MyviewHolder2 holder2 = (MyviewHolder2) holder;
            //设置
            holder2.name.setText(list.get(position).getSubhead());
            holder2.price.setText(list.get(position).getBargainPrice()+"");


        }
    }






    @Override
    public int getItemCount() {

        return list==null ?0:list.size();
    }

    class MyviewHolder1 extends RecyclerView.ViewHolder{
        ImageView iv;
        TextView tv1;
        TextView tv2;

        public MyviewHolder1(View itemView) {
            super(itemView);
            iv=itemView.findViewById(R.id.iv);
           tv1=itemView.findViewById(R.id.tv1);
            tv2=itemView.findViewById(R.id.tv2);

        }
    }

    class MyviewHolder2 extends RecyclerView.ViewHolder{
        TextView name;
        TextView price;
        public MyviewHolder2(View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            price=itemView.findViewById(R.id.price);


        }
    }
}
