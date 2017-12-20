package com.example.dingdan.okhttp;

import java.util.Map;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by peng on 2017/12/1.
 */

public class OkHttpUtils {
    private static OkHttpUtils okHttpUtils;
    private final OkHttpClient client;

    private OkHttpUtils() {
        client = new OkHttpClient.Builder()
                //.addInterceptor(new MyInterceptor())
                .build();
    }

    public static OkHttpUtils getOkHttpUtils() {
        if (okHttpUtils == null) {
            synchronized (OkHttpUtils.class) {
                if (okHttpUtils == null) {
                    okHttpUtils = new OkHttpUtils();
                }
            }
        }
        return okHttpUtils;
    }

    /**
     * GET请求
     *
     * @param url
     * @param callback
     */
    public void doGet(String url, Callback callback) {
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(callback);
    }

    /**
     * POST请求
     *
     * @param url
     * @param params
     * @param callback
     */
    public void doPost(String url, Map<String, String> params, Callback callback) {
        if (params == null) {
            throw new RuntimeException("参数为空了");
        }
        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            builder.add(entry.getKey(), entry.getValue());
        }
        FormBody formBody = builder.build();
        Request request = new Request.Builder().url(url).post(formBody).build();
        client.newCall(request).enqueue(callback);
    }
}
