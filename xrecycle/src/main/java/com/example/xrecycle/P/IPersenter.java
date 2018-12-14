package com.example.xrecycle.P;

import java.util.Map;

public interface IPersenter {
    void getRequest(String strUrl, Map<String, String> param, Class clazz);
}