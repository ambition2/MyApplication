package lanbo.lanbo20171120.model;

import okhttp3.Callback;

/**
 * Created by asus on 2017/11/20.
 */

public interface IGetDdModel {
    //抽象方法获取网络数据
    public void GetNetData(String uid,String status,String page,Callback callback);
}
