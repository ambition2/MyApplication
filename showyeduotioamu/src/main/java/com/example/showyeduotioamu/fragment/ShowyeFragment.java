package com.example.showyeduotioamu.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.showyeduotioamu.R;
import com.example.showyeduotioamu.adaper.RecyAdapter;
import com.example.showyeduotioamu.bean.ShouyeBean;
import com.example.showyeduotioamu.precenter.IShowyePrecenter;
import com.example.showyeduotioamu.precenter.ShowyePrecenter;
import com.example.showyeduotioamu.utils.BaseFragment;
import com.example.showyeduotioamu.view.IShowshouyeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2017/12/30.
 */

public class ShowyeFragment extends BaseFragment<ShowyePrecenter> implements IShowshouyeView {
   RecyclerView shouye_recy;
    ShowyePrecenter precenter;
    List<ShouyeBean.DataBean> list ;
    RecyAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.showye_frg, null);
        shouye_recy=view.findViewById(R.id.shouye_recy);
        precenter.showdata();
        LinearLayoutManager manager=new LinearLayoutManager(getActivity());
        shouye_recy.setLayoutManager(manager);
        return view;

    }

    @Override
    public void showdata(ShouyeBean bean) {
        if(list==null){
            list = new ArrayList<>();
        }
        list.add(bean.getData());
        adapter=new RecyAdapter(getActivity(),list);
        shouye_recy.setAdapter(adapter);

    }

    @Override
    protected void creatprecenter() {
     precenter=new ShowyePrecenter(getActivity(),this);
    }
}
