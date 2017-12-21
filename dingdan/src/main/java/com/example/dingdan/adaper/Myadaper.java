package com.example.dingdan.adaper;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.example.dingdan.R;
import com.example.dingdan.bean.DinfdanBean;
import com.example.dingdan.fragment.Frag_All;

import java.util.List;

/**
 * Created by lenovo on 2017/12/19.
 */

public class Myadaper extends RecyclerView.Adapter<Myadaper.MyViewHolder>{
    Context context;
   List<DinfdanBean.DataBean> bean;
    public Myadaper(Context context, List<DinfdanBean.DataBean> been) {
        this.context = context;
        this.bean=been;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.itwm,null);
        MyViewHolder myHolder=new MyViewHolder(view);

        return myHolder;

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        int status = bean.get(position).getStatus();

        holder.state.setTextColor(Color.parseColor("#000000"));
        if(status==0)
        {
            holder.state.setText("待支付");
            holder.state.setTextColor(Color.parseColor("#ff0000"));
            holder.bt.setText("取消订单");

        }else if(status==1){
            holder.state.setText("已支付");
            holder.bt.setText("查看订单");


        }else if(status==2){
            holder.state.setText("已取消");
            holder.bt.setText("删除订单");

        }

             holder.name.setText(bean.get(position).getTitle());

              holder.price.setText(bean.get(position).getPrice()+"");
              holder.price.setTextColor(Color.parseColor("#ff0000"));
             holder.createtime.setText(bean.get(position).getCreatetime());
        holder.bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initlistend();
            }
        });

    }

    private void initlistend() {
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setIcon(R.mipmap.ic_launcher);//设置图标
        builder.setTitle("若想成功必先自宫");//设置对话框的标题
        builder.setMessage("你确定要自宫吗？");//设置对话框的内容
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {  //这个是设置确定按钮

            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                Toast.makeText(context, "自宫成功", Toast.LENGTH_SHORT).show();


            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {  //取消按钮

            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                Toast.makeText(context, "取消自宫",Toast.LENGTH_SHORT).show();

            }
        });
        AlertDialog b=builder.create();
        b.show();  //
    }

    @Override
    public int getItemCount() {
        return bean==null ? 0 :bean.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView price;
        TextView state;
        TextView createtime;
        Button bt;

        public MyViewHolder(View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.ddname);
            price=itemView.findViewById(R.id.dd_price);
            state=itemView.findViewById(R.id.state);
            createtime=itemView.findViewById(R.id.createtime);
            bt=itemView.findViewById(R.id.btdd);
        }
    }


}
