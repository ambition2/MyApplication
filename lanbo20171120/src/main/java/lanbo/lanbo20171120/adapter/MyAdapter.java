package lanbo.lanbo20171120.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import lanbo.lanbo20171120.R;
import lanbo.lanbo20171120.bean.Bean;

/**
 * Created by asus on 2017/11/20.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {
    Context context;
    Bean bean;
    public MyAdapter(Context context, Bean bean) {
        this.context = context;
        this.bean = bean;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=View.inflate(context,R.layout.item_layout,null);
        MyHolder myHolder=new MyHolder(view);

        return myHolder;
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {
        if (bean.getData().get(position).getStatus()==1){
            holder.bt.setText("查看订单");
            holder.state.setText("已支付");
        }
        if(bean.getData().get(position).getStatus()==0){
            holder.bt.setText("取消订单");
            holder.state.setText("待支付");
        }
        if(bean.getData().get(position).getStatus()==2){
            holder.bt.setText("已取消");
            holder.state.setText("已取消");
        }

        holder.name.setText(bean.getData().get(position).getTitle());
        holder.price.setText(bean.getData().get(position).getPrice()+"");
        holder.createtime.setText(bean.getData().get(position).getCreatetime());
        if(holder.bt.getText().equals("取消订单")){
            holder.bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    bean.getData().get(position).setStatus(2);
                    Toast.makeText(context,"修改成功",Toast.LENGTH_SHORT).show();
                    bean.getData().remove(position);
                    notifyDataSetChanged();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return bean.getData()==null?0:bean.getData().size();
    }

    class MyHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView price;
        TextView state;
        TextView createtime;
        Button bt;
        public MyHolder(View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.ddname);
            price=itemView.findViewById(R.id.dd_price);
            state=itemView.findViewById(R.id.state);
            createtime=itemView.findViewById(R.id.createtime);
            bt=itemView.findViewById(R.id.btdd);
        }

    }
}
