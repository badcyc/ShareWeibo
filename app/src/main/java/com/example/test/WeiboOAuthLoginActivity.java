package com.example.test;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.test.Model.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.example.test.Presenter.FragmentsGetData.GetLists.getUid;

/**
 * Created by cyc20 on 2017/11/28.
 */

public class WeiboOAuthLoginActivity extends AppCompatActivity {

        private static final String getCode_url="https://api.weibo.com/oauth2/authorize?" +
                "client_id=2748897403" +
                "&response_type=code" +
                "&redirect_uri=http://www.sina.com";
        private static final String get_accessToken_uri="https://api.weibo.com/oauth2/access_token";
        private String code;
        private String access_token;
    private WebView webView;
        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);
            webView=(WebView)findViewById(R.id.WebView);
            WebSettings webSettings = webView.getSettings();

//如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
            webSettings.setJavaScriptEnabled(true);
// 若加载的 html 里有JS 在执行动画等操作，会造成资源浪费（CPU、电量）
// 在 onStop 和 onResume 里分别把 setJavaScriptEnabled() 给设置成 false 和 true 即可

//支持插件

//设置自适应屏幕，两者合用
            webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
            webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小

//缩放操作
            webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
            webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
            webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件

//其他细节操作
            webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //关闭webview中缓存
            webSettings.setAllowFileAccess(true); //设置可以访问文件
            webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
            webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
            webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式

            webView.setWebViewClient(new WebViewClient(){


                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    if(url.contains("code")){
                        String code= Uri.parse(url).getQueryParameters("code").get(0);
                        getAccessToken(code);
                        getUid();
                    }
                    return super.shouldOverrideUrlLoading(view, url);
                }
            });
            webView.loadUrl(getCode_url);

        }
    private void getAccessToken(final String code){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.d("start","on");
                    OkHttpClient httpClient=new OkHttpClient();
                    RequestBody requestBody=new FormBody.Builder()
                            .add("client_id","2748897403")
                            .add("client_secret","48336becd31457235a2c0705d2429670")
                            .add("grant_type","authorization_code")
                            .add("redirect_uri","http://www.sina.com")
                            .add("code",code).build();
                    Request request=new Request.Builder()
                            .url(get_accessToken_uri)
                            .post(requestBody)
                            .build();

                    Response response = httpClient.newCall(request).execute();
                    //  Log.d("Webview", "getAccess_token: response Exception");
                    String responseData = response.body().string();
                    Log.d("responseData",responseData);
                    try {
                        JSONObject object = new JSONObject(responseData);
                        access_token = object.getString("access_token");
                        Utils.access_token=access_token;
                        Log.d("access_token",access_token);
                    }catch (JSONException e){
                        Log.d("access_token","JSON ERROR");
                        e.printStackTrace();
                    }
                    Intent intent=new Intent(WeiboOAuthLoginActivity.this,MainActivity.class);
                   // intent.putExtra("access_token",access_token);
                    startActivity(intent);
                }
                catch (IOException e){
                    e.printStackTrace();
                    Log.d("webview","getAccess_token exception");
                }

            }
        }).start();
    }

}
