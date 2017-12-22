package com.example.test.base.rxjava;

/**
 * Created by cyc20 on 2017/12/21.
 */

public interface ApiCallback<T> {
    void onSuccess(T model);
    void onFailure(int code,String msg);
    void onComplete();
}
