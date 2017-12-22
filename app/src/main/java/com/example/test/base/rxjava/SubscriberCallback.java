package com.example.test.base.rxjava;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * Created by cyc20 on 2017/12/21.
 */

public class SubscriberCallback<T> extends Subscriber<T> {

    private ApiCallback<T> apiCallback;

    public SubscriberCallback(ApiCallback<T> apiCallback) {
        this.apiCallback = apiCallback;
    }

    @Override
    public void onCompleted() {
        apiCallback.onComplete();
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            int code = httpException.code();
            String msg = httpException.getMessage();
            if (code == 504) {
                msg = "网络不给力";
            }
            apiCallback.onFailure(code, msg);

        } else {
            apiCallback.onFailure(0, e.getMessage());
        }
        apiCallback.onComplete();
    }

    @Override
    public void onNext(T t) {
        apiCallback.onSuccess(t);
    }
}
