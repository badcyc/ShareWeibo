package com.example.test.BaseModel;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.test.BaseModel.Utils.access_token;

/**
 * Created by cyc20 on 2017/12/13.
 */

public class OkHttpUtil {
    private static OkHttpUtil okHttpUtil=new OkHttpUtil();
    private OkHttpClient okHttpClient;
    private OkHttpUtil(){
        okHttpClient=new OkHttpClient();
    }
    public static OkHttpUtil getInstance(){
        return okHttpUtil;
    }
    public void getData(String url, HashMap<String,String>params, final OkHttpCallBack callBack){
        HttpUrl.Builder httpBuilder = HttpUrl.parse(url).newBuilder();
        httpBuilder.addQueryParameter("access_token", access_token);
        for (Map.Entry<String,String> entry:params.entrySet()){
            httpBuilder.addQueryParameter(entry.getKey(),entry.getValue());
        }
       // httpBuilder.addQueryParameter("count", "20");          //数量20
        final Request request = new Request.Builder()
                .url(httpBuilder.build())
                .get()
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if (response.isSuccessful())callBack.getData(response.body().string());
            }
        });

    }
   public static class Builder{
       private String url;
       private HashMap<String,String>params=new HashMap<>();
       private OkHttpCallBack callBack;
       public Builder setUrl(String url){
           this.url=url;
           return this;
       }
       public Builder setParams(HashMap<String,String> params){
           this.params=params;
           return this;
       }
       public Builder setCallBack(OkHttpCallBack callBack){
           this.callBack=callBack;
           return this;
       }
       public OkHttpUtil create(){
           OkHttpUtil okHttpUtil=OkHttpUtil.getInstance();
           okHttpUtil.getData(url,params,callBack);
           return okHttpUtil;
       }
   }

}
