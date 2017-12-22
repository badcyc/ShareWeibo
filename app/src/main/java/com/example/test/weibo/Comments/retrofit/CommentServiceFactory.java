package com.example.test.weibo.Comments.retrofit;

import java.lang.reflect.Field;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by cyc20 on 2017/12/21.
 */

public class CommentServiceFactory {

    private static class SingletonHolder {
        private static final CommentServiceFactory INSTANCE = new CommentServiceFactory();
    }

    public static CommentServiceFactory getInstance() {
        return SingletonHolder.INSTANCE;
    }
    public <S> S createService(Class<S> serviceClass){
        String baseUrl="";
        try {
            Field field=serviceClass.getField("BASE_URL");
            baseUrl=(String) field.get(serviceClass);
        }catch (NoSuchFieldException e){
            e.printStackTrace();
        }catch (IllegalAccessException e){
            e.getMessage();
            e.printStackTrace();
        }
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(serviceClass);
    }

}
