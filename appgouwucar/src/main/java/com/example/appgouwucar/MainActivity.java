package com.example.appgouwucar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.appgouwucar.bean.LogBean;
import com.example.appgouwucar.precener.Logprecenter;
import com.example.appgouwucar.view.IShowlogVew;

public class MainActivity extends Activity implements IShowlogVew{
    EditText logname;
    EditText logpwd;
    Button log;
    Button zhuce;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logname=findViewById(R.id.name);
        logpwd=findViewById(R.id.pwd);
        zhuce=findViewById(R.id.zhuce);
        log=findViewById(R.id.log);
        final Logprecenter logprecenter=new Logprecenter(this,this);
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logprecenter.showdata();
            }
        });
        zhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    @Override
    public void setName(String name) {
        logname.setText(name);
    }

    @Override
    public String getName() {
        return logname.getText().toString();
    }

    @Override
    public void setpPwd(String pwd) {
        logpwd.setText(pwd);
    }

    @Override
    public String getPwd() {
        return logpwd.getText().toString();
    }

    @Override
    public void success(LogBean bean) {
        SharedPreferences user = getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = user.edit();
        edit.putString("uid",bean.getData().getUid()+"");
        edit.commit();
        Intent intent=new Intent(MainActivity.this,RecyclerActivity.class);
        startActivity(intent);
    }
}

