package com.example.webviewbanner.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.webviewbanner.R;
import com.example.webviewbanner.adaper.RecyclerAdaper;
import com.example.webviewbanner.adaper.Recyclertwoadaper;
import com.example.webviewbanner.bean.RecyclerBean;
import com.example.webviewbanner.precenter.Showrecyclerpercenter;
import com.example.webviewbanner.view.IShowrecyclerView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

public class NextActivity extends Activity implements IShowrecyclerView{
  XRecyclerView xre;
    Button bu1;
    boolean panduan=true;
    EditText et;
    ImageView iv;
    RecyclerAdaper adaper;
    Handler handler=new Handler();

    Showrecyclerpercenter showrecyclerpercenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
        xre=findViewById(R.id.xre);
        bu1=findViewById(R.id.bu1);
       et=findViewById(R.id.et);
        iv=findViewById(R.id.image);

        Intent intent = getIntent();
        final String pscid = intent.getStringExtra("pscid");


        showrecyclerpercenter=new Showrecyclerpercenter(this);
        showrecyclerpercenter.showrecycler(pscid);

        LinearLayoutManager manager=new LinearLayoutManager(NextActivity.this);
        //给控件附上
        xre.setLayoutManager(manager);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(NextActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        et.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(NextActivity.this, "我不好!", Toast.LENGTH_LONG).show();

                Intent intent=new Intent(NextActivity.this,TitleActivity.class);
                startActivity(intent);
            }
        });
        xre.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        showrecyclerpercenter.showrecycler(pscid);
                    }
                },1000);
            }

            @Override
            public void onLoadMore() {

            }
        });



    }

    @Override
    public void showrecycler(final List<RecyclerBean.DataBean> beanList) {


       adaper=new RecyclerAdaper(this,beanList);
        xre.setAdapter(adaper);
        adaper.setOnItemClickListener(new RecyclerAdaper.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                Toast.makeText(NextActivity.this, "recycler的点击", Toast.LENGTH_LONG).show();
                Intent intent=new Intent(NextActivity.this,DetailsActivity.class);
                String pid = String.valueOf(beanList.get(position).getPid());
                intent.putExtra("pid",pid);
                startActivity(intent);
            }
        });

        bu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //如果变量==true
                if(panduan==true){
                    //先给它个图片
                    bu1.setBackgroundResource(R.drawable.kind_grid);
                    //然后设置另一个的布局管理器  在创建一个新的adaper 新的布局
                    GridLayoutManager manager=new GridLayoutManager(NextActivity.this,2);
                    xre.setLayoutManager(manager);
                    //new出另一个布局的adaper
                    Recyclertwoadaper mytwoAdaper=new Recyclertwoadaper(NextActivity.this,beanList);
                    xre.setAdapter(mytwoAdaper);
                    mytwoAdaper.setOnTwoItemClickListener(new Recyclertwoadaper.OnTwoItemClickListener() {
                        @Override
                        public void onTwoItemClick(View view, int position) {
                            Toast.makeText(NextActivity.this, "第二个recycler的点击", Toast.LENGTH_LONG).show();
                        }
                    });

                    //刷新适配器
                    mytwoAdaper.notifyDataSetChanged();
                    //执行完以后把变量设置为false 如果不设置的切换的时候回切不过来
                    panduan=false;

                }else
                {
                    //先给它个图片
                  bu1.setBackgroundResource(R.drawable.kind_liner);
                    //设置管理器，写一开始的布局，创建适配器，刷新就可以了
                    LinearLayoutManager manager=new LinearLayoutManager(NextActivity.this);
                    xre.setLayoutManager(manager);

                    final RecyclerAdaper myadaper=new RecyclerAdaper(NextActivity.this,beanList);
                    xre.setAdapter(myadaper);

                    //执行到在这里已经是false了，把它改成true，方便切换
                    panduan=true;
                }
            }
        });

    }

}
