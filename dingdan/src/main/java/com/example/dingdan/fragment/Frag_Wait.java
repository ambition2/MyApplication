package com.example.dingdan.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dingdan.R;

/**
 * Created by lenovo on 2017/12/20.
 */

public class Frag_Wait extends Fragment {
    Button button;
    TextView tvv;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.frag_wait, null);
        button=view.findViewById(R.id.butt);
        tvv=view.findViewById(R.id.tvv);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                builder.setIcon(R.mipmap.ic_launcher);//设置图标
                builder.setTitle("若想成功必先自宫");//设置对话框的标题
                builder.setMessage("你确定要自宫吗？");//设置对话框的内容
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {  //这个是设置确定按钮

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Toast.makeText(getActivity(), "自宫成功", Toast.LENGTH_SHORT).show();
                        tvv.setText("我就哈哈了");
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {  //取消按钮

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Toast.makeText(getActivity(), "取消自宫",Toast.LENGTH_SHORT).show();

                    }
                });
                AlertDialog b=builder.create();
                b.show();  //
            }
        });
        return view;
    }
}
