package com.example.xrecycle.M;

import com.example.xrecycle.CallBack.MyCallBack;

import java.util.Map;

public interface IModel {
    void getRequest(String strUrl, Map<String,String>params, Class clazz, MyCallBack callBack);
}
