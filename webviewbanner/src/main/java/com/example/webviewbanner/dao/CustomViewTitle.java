package com.example.webviewbanner.dao;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.webviewbanner.R;

/**
 * Created by QinQinBaoBei on 2017/12/4.
 */

public class CustomViewTitle extends LinearLayout {

    private EditText editText;
    private Button btn;
    ImageView back;

    public CustomViewTitle(Context context) {
        this(context,null);
    }


    public CustomViewTitle(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }


    public CustomViewTitle(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //找到自定义xml
        View view = LayoutInflater.from(context).inflate(R.layout.custom_title_view,null);
        addView(view);
        //寻找控件的id
        editText = view.findViewById(R.id.edit_custom);
        btn = view.findViewById(R.id.search_custom);
        back = view.findViewById(R.id.back);
        back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                 myback.Setback();
            }
        });

        btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                  myListener.setListener(editText.getText().toString());
            }
        });
    }
    //定义接口
    MyListener myListener;
    public void getListener(MyListener myListener){
        this.myListener = myListener;
    }
   public interface MyListener{
        void setListener(String title);
   }
   Myback myback;
   public  void setbacklisten(Myback myback){
       this.myback=myback;
   }
   public interface Myback{
       void Setback();
   }
}
