package com.baway;

/**
 * Created by 李宗书 on 2017/8/2.
 */

public interface NetDataCallback {
    void err(int code,String str);
    void callback(String st);
}
