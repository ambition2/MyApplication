package com.example.logandrechmvp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.logandrechmvp.precenter.ZhecePercenter;
import com.example.logandrechmvp.view.IRegView;

public class ZheceActivity extends Activity implements IRegView{
 EditText zhucename;
    EditText zhucepwd;
    Button liji;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhece);
        initdata();
        final ZhecePercenter presenter=new ZhecePercenter(this,this);
       liji.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
                presenter.showzhecu();
           }
       });
    }

    private void initdata() {
        zhucename=findViewById(R.id.zhucename);
        zhucepwd=findViewById(R.id.zhucepwd);
        liji=findViewById(R.id.liji);
    }

    @Override
    public void setName(String name) {
         zhucename.setText(name);
    }

    @Override
    public String getName() {
        return zhucename.getText().toString();
    }

    @Override
    public void setpPwd(String pwd) {
    zhucepwd.setText(pwd);
    }

    @Override
    public String getPwd() {
        return zhucepwd.getText().toString();
    }
}
