package lanbo.lanbo20171120.presenter;

import android.content.Context;

import com.google.gson.Gson;

import java.io.IOException;

import lanbo.lanbo20171120.bean.Bean;
import lanbo.lanbo20171120.model.MyCancleModel;
import lanbo.lanbo20171120.model.MyGetDdModel;
import lanbo.lanbo20171120.util.OnUiCallback;
import lanbo.lanbo20171120.view.IShowDdView;
import okhttp3.Call;

/**
 * Created by asus on 2017/11/20.
 */

public class MyDdPresenter {
    Context context;
    MyGetDdModel model;
    IShowDdView view;
    MyCancleModel model1;
    public MyDdPresenter(Context context, IShowDdView view) {
        this.context = context;
        this.view = view;
        model=new MyGetDdModel();
    }
    public void ddtopostgetdata(String stat,String page){
        model.GetNetData("71", stat,page, new OnUiCallback() {
            @Override
            public void onFailed(Call call, IOException e) {
            //失败回调
            }

            @Override
            public void onSuccess(String result) {
                //成功回调
                Gson gson=new Gson();
                Bean bean = gson.fromJson(result, Bean.class);
                view.ShowView(bean);
            }
        });
    }


}
