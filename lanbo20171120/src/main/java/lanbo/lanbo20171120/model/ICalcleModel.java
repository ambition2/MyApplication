package lanbo.lanbo20171120.model;

import okhttp3.Callback;

/**
 * Created by asus on 2017/11/20.
 */

public interface ICalcleModel {
    public void GetCancleData(String uid, String orderId, String status, Callback callback);
}
