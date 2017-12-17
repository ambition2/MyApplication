package com.example.webviewbanner.model;

import com.example.webviewbanner.bean.RecyclerBean;
import com.example.webviewbanner.okthhp.OnNetListener;

/**
 * Created by lenovo on 2017/12/5.
 */

public interface IShowrecycler {
    public void getrecycler( String pscid,OnNetListener<RecyclerBean> onNetListener);
}
