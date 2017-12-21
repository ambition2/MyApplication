package com.example.dingdan.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.dingdan.R;
import com.example.dingdan.adaper.Myadaper;
import com.example.dingdan.bean.DinfdanBean;
import com.example.dingdan.precenter.ShowDataPrecenter;
import com.example.dingdan.view.IShowDataView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

/**
 * Created by lenovo on 2017/12/20.
 */

public class Frag_All extends Fragment implements IShowDataView{
    XRecyclerView xre;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.frag_all, null);
         xre=view.findViewById(R.id.xre);
        ShowDataPrecenter precenter=new ShowDataPrecenter(this);
        precenter.showlastdata("71","1");

        return view;
    }

    @Override
    public void showdata(DinfdanBean been) {
        System.out.println(been.toString());
        LinearLayoutManager manager=new LinearLayoutManager(getActivity());
        xre.setLayoutManager(manager);
        Myadaper myadaper=new Myadaper(getContext(),been.getData());
        xre.setAdapter(myadaper);

    }
}
