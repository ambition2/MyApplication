package com.example.studyone.precenter;

import com.example.studyone.bean.OneDataBean;
import com.example.studyone.model.IShowModel;
import com.example.studyone.model.ShowDataModel;
import com.example.studyone.view.IShowDataView;

/**
 * Created by lenovo on 2017/11/28.
 */

public class ShowPrecenter {
      ShowDataModel model;
    IShowDataView view;
    public  ShowPrecenter( IShowDataView view){
        this.view=view;
        model=new ShowDataModel();
    }
    public  void  getdata(int page){
          model.getdata(page, new IShowModel() {
              @Override
              public void success(OneDataBean bean) {
                     view.success(bean);
              }

              @Override
              public void error(Exception e) {
                 view.error(e);
              }
          });
    }

    }

