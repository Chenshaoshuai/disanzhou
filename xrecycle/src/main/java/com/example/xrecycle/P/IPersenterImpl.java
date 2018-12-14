package com.example.xrecycle.P;

import com.example.xrecycle.CallBack.MyCallBack;
import com.example.xrecycle.M.IModelImpl;
import com.example.xrecycle.V.IView;

import java.util.Map;

public class IPersenterImpl implements IPersenter {
    private IView mIView;
    private IModelImpl iModel;

    public IPersenterImpl(IView mIView) {
        this.mIView = mIView;
        iModel = new IModelImpl();
    }

    @Override
    public void getRequest(String strUrl, Map<String, String> param, Class clazz) {
           iModel.getRequest(strUrl, param, clazz, new MyCallBack() {
               @Override
               public void onSuccess(Object data) {
                   mIView.onSuccess(data);
               }
           });
    }
    public void deteach(){
        iModel = null;
        mIView = null;
    }
}
