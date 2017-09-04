package com.baway;

/**
 * Created by 李宗书 on 2017/8/8.
 */

public interface NetDataCallback2<T> {
    void success(T t);
    void fail(int code,String str);

}
