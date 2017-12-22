package com.example.test.base.presenter;



/**
 * Created by cyc20 on 2017/12/21.
 */

public interface Presenter<V> {
    void attachView(V view);
    void detachView();
}
