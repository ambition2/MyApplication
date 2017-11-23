package lanbo.lanbo20171120.model;

import java.util.HashMap;
import java.util.Map;

import lanbo.lanbo20171120.util.OkHttp3Utils;
import okhttp3.Callback;

/**
 * Created by asus on 2017/11/20.
 */

public class MyGetDdModel implements IGetDdModel {
    //重写抽象方法实现具体内容
    @Override
    public void GetNetData(String uid,String status,String page,Callback callback) {
        //实现post请求
        Map<String,String> map=new HashMap<>();
        map.put("uid",uid);
        map.put("status",status);
        map.put("page",page);
        OkHttp3Utils.doPost("https://www.zhaoapi.cn/product/getOrders?source=android",map,callback);
    }
}
