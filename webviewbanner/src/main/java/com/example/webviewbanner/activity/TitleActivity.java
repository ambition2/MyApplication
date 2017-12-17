package com.example.webviewbanner.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.webviewbanner.R;
import com.example.webviewbanner.dao.CustomViewTitle;
import com.example.webviewbanner.dao.MyUtils;

import java.util.List;

public class TitleActivity extends Activity  implements View.OnClickListener{
    private MyUtils myUtils;
    private ListView listView;
    private List<String> select;
    private Button dianji;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);
        //找到引入activity的布局id
        CustomViewTitle customViewTitle = findViewById(R.id.custom_title);
        Button btn = findViewById(R.id.btn_activity);
        listView = findViewById(R.id.listview_activity);
        //点击事件都一样只做了一个点击事件
        dianji = findViewById(R.id.dianji);
        //热搜的点击事件
        dianji.setOnClickListener(this);

        myUtils = new MyUtils(this);
       //得到标题里的搜索的点击事件使用了接口回调
        customViewTitle.getListener(new CustomViewTitle.MyListener() {
            @Override
            public void setListener(String title) {
                //点击一下添加一个数据
                myUtils.add(title);
                //getData();这个方法类似于刷新数据库
                getData();
            }
        });
        customViewTitle.setbacklisten(new CustomViewTitle.Myback() {
            @Override
            public void Setback() {
                Toast.makeText(TitleActivity.this, "我不好!", Toast.LENGTH_LONG).show();
                Intent intent=new Intent(TitleActivity.this,NextActivity.class);
                startActivity(intent);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(TitleActivity.this,select.get(position),Toast.LENGTH_SHORT).show();
            }
        });

        //清空数据库
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myUtils.clearDataBase();
                getData();
            }
        });

    }
    public void getData(){
        // 调用myUtils里的查询的方法
        select = myUtils.select();

        System.out.println(select);
        //适配器
        //得到输入的值后显示在listview上面
        ArrayAdapter adapter = new ArrayAdapter<String>(TitleActivity.this,android.R.layout.simple_list_item_1,android.R.id.text1,select);
        //设置适配器
        listView.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(TitleActivity.this,dianji.getText().toString(),Toast.LENGTH_SHORT).show();
    }
}
