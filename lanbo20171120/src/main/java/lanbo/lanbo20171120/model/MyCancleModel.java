package lanbo.lanbo20171120.model;

import java.util.HashMap;
import java.util.Map;

import lanbo.lanbo20171120.util.OkHttp3Utils;
import okhttp3.Callback;

/**
 * Created by asus on 2017/11/20.
 */

public class MyCancleModel implements ICalcleModel {
    @Override
    public void GetCancleData(String uid,String orderId,String status,Callback callback) {
        Map<String,String> map=new HashMap<>();
        map.put("uid",uid);
        map.put("orderId",orderId);
        map.put("status",status);

        OkHttp3Utils.doPost("https://www.zhaoapi.cn/product/updateOrder",map,callback);
    }
}
