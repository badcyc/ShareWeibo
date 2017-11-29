package com.example.test.View.Fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.test.Adapters.MessageAdapter;
import com.example.test.Model.Message;
import com.example.test.Model.User;
import com.example.test.Model.Utils;
import com.example.test.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by cyc20 on 2017/11/29.
 */

public class AllWeiboFragment extends Fragment {
    private static final String ARG_PARAM1="param1";
    private int progress;
    private Context context=null;
    public ArrayList<Message>lists=new ArrayList<>();
    //public TaskAdapter adapter;
    MessageAdapter adapter;
    private String mParam1;
    private String mParam2;
    public AllWeiboFragment(){

    }
    public static AllWeiboFragment newInstance(ArrayList<Message> lists) {
        AllWeiboFragment fragment = new AllWeiboFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1,lists);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onAttach(Context context) {
        this.context=context;

        super.onAttach(context);

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
     /*  if (lists==null){
            lists=new ArrayList<>();
        }
        if(savedInstanceState!=null){
            lists=(ArrayList)getArguments().getSerializable(ARG_PARAM1);
        }*/
        View view=inflater.inflate(R.layout.fragment_weibo,null);
        initDate();
        adapter=new MessageAdapter(lists,getActivity());
        RecyclerView recyclerView=(RecyclerView)view.findViewById(R.id.recycler_view);
        recyclerView.setAdapter(adapter);


        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(inflater.getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

//        ProgressBar progressBar=(ProgressBar)recyclerView.findViewById(R.id.progressBar);
        //      progressBar.setProgress(progress);
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }


    private void initDate() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient httpClient = new OkHttpClient();
                    String access_token=Utils.access_token;
                    Log.d("access_send", access_token);
                    /*RequestBody requestBody=new FormBody.Builder()
                            .add("access_token",access_token)
                            .add("count","1")
                            .build();*/
                    HttpUrl.Builder httpBuilder = HttpUrl.parse(Utils.getContentUrl).newBuilder();
                    httpBuilder.addQueryParameter("access_token", access_token);
                    httpBuilder.addQueryParameter("count", "10");
                    Request request = new Request.Builder()
                            .url(httpBuilder.build())
                            .get()
                            .build();
                    Response response = httpClient.newCall(request).execute();
                    String responsedata = response.body().string();
                    Log.d("responsedata", responsedata);
                    try {
                        JSONObject jsonObject = new JSONObject(responsedata);
                        JSONArray jsonArray = jsonObject.getJSONArray("statuses");
                        for(int i=1;i<=10;i++) {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(0);
                            String create_at = jsonObject1.getString("created_at");
                            Log.d("created_at", create_at);

                            // JSONObject jsonObject2=jsonArray.getJSONObject(5);
                            String content = jsonObject1.getString("text");
                            Log.d("content", content);

                            //JSONObject jsonObject3=jsonArray.getJSONObject(8);
                            String from_url = jsonObject1.getString("source");
                            Log.d("from_url", from_url);

                            JSONObject user = jsonObject1.getJSONObject("user");
                            //JSONObject nameObject=userArray.getJSONObject(4);
                            String user_name = user.getString("name");
                            Log.d("user_name", user_name);

                            //JSONObject picObject=jsonArray.getJSONObject(37);
                            String pic_url = user.getString("avatar_hd");
                            Log.d("pic_url", pic_url);
                            User user1=new User();
                            user1.setName(user_name);
                            Message message=new Message(create_at,content,from_url,pic_url,user1);
                            lists.add(message);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    //response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        ).start();
    }


    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

}
