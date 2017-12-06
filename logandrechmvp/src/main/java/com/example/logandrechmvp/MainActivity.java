package com.example.logandrechmvp;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.logandrechmvp.precenter.LogPercenter;
import com.example.logandrechmvp.view.ILoginView;

public class MainActivity extends Activity implements ILoginView {
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
        final LogPercenter percenter=new LogPercenter(this,this);
   log.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View view) {
            percenter.Logshow();
       }
   });
        zhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,ZheceActivity.class);
                startActivity(intent);
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
}
