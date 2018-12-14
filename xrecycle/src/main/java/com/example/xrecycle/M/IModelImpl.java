package com.example.xrecycle.M;

import com.example.xrecycle.CallBack.MyCallBack;
import com.example.xrecycle.M.IModel;
import com.example.xrecycle.okhttp.ICallBack;
import com.example.xrecycle.okhttp.OkHttp;

import java.util.Map;

public class IModelImpl implements IModel {
    @Override
    public void getRequest(String strUrl, Map<String, String> params, Class clazz, final MyCallBack callBack) {
        OkHttp.getInstance().getQueue(strUrl, params, clazz, new ICallBack() {
            @Override
            public void getResponse(Object obj) {
                callBack.onSuccess(obj);
            }

            @Override
            public void getFaile(Exception e) {
               callBack.onSuccess(e);
            }
        });
    }
}
