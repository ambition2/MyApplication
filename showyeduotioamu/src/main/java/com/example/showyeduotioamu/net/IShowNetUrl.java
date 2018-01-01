package com.example.showyeduotioamu.net;

import com.example.showyeduotioamu.bean.ShouyeBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by lenovo on 2017/12/30.
 */

public interface IShowNetUrl {
    String BASE1_URL = "http://result.eolinker.com";
    //http://result.eolinker.com/umIPmfS6c83237d9c70c7c9510c9b0f97171a308d13b611?uri=homepage
    @GET("/umIPmfS6c83237d9c70c7c9510c9b0f97171a308d13b611")
    Observable<ShouyeBean> getbean(@Query("uri") String uri);
}
