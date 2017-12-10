package com.example.monisunday.adaper;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.monisunday.R;
import com.example.monisunday.bena.RecycykerBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by lenovo on 2017/12/9.
 */

public class Myadaper extends RecyclerView.Adapter {
    private Context context;
    private List<RecycykerBean.DataBean> list;
    boolean flag=true;




    public Myadaper(Context context, List<RecycykerBean.DataBean> list,boolean flag) {
        this.context = context;
        this.list = list;
        this.flag=flag;

    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(flag){
            View view = View.inflate(context, R.layout.recyclerone, null);
            Myviewhollder1 myviewhollder1=new Myviewhollder1(view);
            return myviewhollder1;
        }else
        {
            View view = View.inflate(context, R.layout.adapertwo, null);
            Myviewholder2 myviewhollder2=new Myviewholder2(view);
            return myviewhollder2;

        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
          if(flag)
          {
              if(holder instanceof Myviewhollder1){
                Myviewhollder1 myviewhollder1= (Myviewhollder1) holder;
                  String[] split = list.get(position).getImages().split("\\|");
          ImageLoader.getInstance().displayImage(split[0], myviewhollder1.ivv);
                  myviewhollder1.tvv.setText(list.get(position).getTitle());

              }
          }else {
                if(holder instanceof  Myviewholder2){
                    Myviewholder2 myviewholder2= (Myviewholder2) holder;
                    String[] split = list.get(position).getImages().split("\\|");
                    ImageLoader.getInstance().displayImage(split[0], myviewholder2.iv);
                    myviewholder2.tv.setText(list.get(position).getTitle());


                }
          }
    }







    @Override
    public int getItemCount() {
        return list==null ? 0 : list.size();
    }

    class Myviewhollder1 extends RecyclerView.ViewHolder{
        ImageView ivv;
        TextView tvv;

        public Myviewhollder1(View itemView) {
            super(itemView);
            ivv = itemView.findViewById(R.id.ivv);
            tvv = itemView.findViewById(R.id.tvv);
        }
    }
    class Myviewholder2 extends RecyclerView.ViewHolder{
        ImageView iv;
        TextView tv;
        public Myviewholder2(View itemView) {
            super(itemView);
            iv=itemView.findViewById(R.id.ivImage);
            tv=itemView.findViewById(R.id.tv);
        }
    }
}
