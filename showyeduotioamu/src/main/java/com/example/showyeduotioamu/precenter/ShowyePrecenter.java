package com.example.showyeduotioamu.precenter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.example.showyeduotioamu.bean.ShouyeBean;
import com.example.showyeduotioamu.model.ShowyeModel;
import com.example.showyeduotioamu.view.IShowshouyeView;

import java.lang.ref.SoftReference;

import rx.Observer;

/**
 * Created by lenovo on 2017/12/30.
 */

public class ShowyePrecenter implements IShowyePrecenter<IShowshouyeView> {
    Context context;
    ShowyeModel model;
    SoftReference<IShowshouyeView> reference;
    IShowshouyeView view;
    public ShowyePrecenter(Context context,IShowshouyeView view){
        this.view=view;
        this.context=context;
        model=new ShowyeModel();
        attach(view);
    }
    public void showdata(){
        model.showdata(new Observer<ShouyeBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ShouyeBean bean) {
                reference.get().showdata(bean);
            }


        });
    };

    @Override
    public void attach(IShowshouyeView view) {
        reference=new SoftReference<IShowshouyeView>(view);
    }

    @Override
    public void detach() {
         reference.clear();
    }
}
