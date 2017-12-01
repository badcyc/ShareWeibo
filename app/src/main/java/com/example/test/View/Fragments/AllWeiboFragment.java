package com.example.test.View.Fragments;

import android.app.Activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

import okhttp3.Call;
import okhttp3.Callback;
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
    private Handler handler;
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

        handler=new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(android.os.Message message) {
                adapter.notifyDataSetChanged();
                return true;
            }
        });
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
        adapter=new MessageAdapter(lists,context);
        Log.d("adapter:","finish");
        RecyclerView recyclerView=(RecyclerView)view.findViewById(R.id.recycler_view);
        recyclerView.setAdapter(adapter);
        Log.d("recyclerView:","finish");

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(inflater.getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }


    private void initDate() {
                    OkHttpClient httpClient = new OkHttpClient();
                    String access_token=Utils.access_token;
                    Log.d("access_send", access_token);
                    HttpUrl.Builder httpBuilder = HttpUrl.parse(Utils.getContentUrl).newBuilder();
                    httpBuilder.addQueryParameter("access_token", access_token);
                    httpBuilder.addQueryParameter("count", "10");
                    Request request = new Request.Builder()
                            .url(httpBuilder.build())
                            .get()
                            .build();
                    Call call = httpClient.newCall(request);
                    call.enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            e.printStackTrace();
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            if (response.isSuccessful()) {
                                String responsedata = response.body().string();
                                Log.d("responsedata", responsedata);
                                try {
                                    JSONObject jsonObject = new JSONObject(responsedata);
                                    JSONArray jsonArray = jsonObject.getJSONArray("statuses");

                                    for (int i = 0; i < 10; i++) {
                                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                        String create_at = jsonObject1.getString("created_at");
                                        Log.d("created_at", create_at);

                                        // JSONObject jsonObject2=jsonArray.getJSONObject(5);
                                        String content = jsonObject1.getString("text");
                                        Log.d("content", content);

                                        //JSONObject jsonObject3=jsonArray.getJSONObject(8);
                                        String from_url = jsonObject1.getString("source");
                                        Log.d("from_url", from_url);

                                        JSONArray pic_urlsArray = jsonObject1.getJSONArray("pic_urls");
                                        int k = pic_urlsArray.length();
                                        ArrayList<String> pic_urls = new ArrayList<>();
                                        for (int j = 0; j < k; j++) {
                                            pic_urls.add(pic_urlsArray.getJSONObject(j).getString("thumbnail_pic"));
                                        }
                                        String reposts_count = jsonObject1.getString("reposts_count");
                                        String comments_count = jsonObject1.getString("comments_count");
                                        String attitudes_count = jsonObject1.getString("attitudes_count");

                                        JSONObject user = jsonObject1.getJSONObject("user");
                                        //JSONObject nameObject=userArray.getJSONObject(4);
                                        String user_name = user.getString("name");
                                        Log.d("user_name", user_name);

                                        //JSONObject picObject=jsonArray.getJSONObject(37);
                                        String avatar_hd = user.getString("avatar_hd");
                                        Log.d("avatar_hd", avatar_hd);

                                        JSONObject retweetedObject = jsonObject1.getJSONObject("retweeted_status");
                                        Message retweetedMessage = null;
                                        if (retweetedObject != null) {
                                            String retweetedFrom_url = jsonObject1.getString("source");
                                            String retweetedCreate_at = jsonObject1.getString("created_at");
                                            String retweetContent = jsonObject1.getString("text");
                                            JSONArray retweetedPic_urlsArray = jsonObject1.getJSONArray("pic_urls");
                                            int retweetedLength = retweetedPic_urlsArray.length();
                                            ArrayList<String> retweetedPic_urls = new ArrayList<>();
                                            for (int j = 0; j < retweetedLength; j++) {
                                                retweetedPic_urls.add(pic_urlsArray.getJSONObject(j).getString("thumbnail_pic"));
                                            }
                                            String retweetedReposts_count = jsonObject1.getString("reposts_count");
                                            String retweetedComments_count = jsonObject1.getString("comments_count");
                                            String retweetedAttitudes_count = jsonObject1.getString("attitudes_count");
                                            JSONObject retweetedUserObject = jsonObject1.getJSONObject("user");
                                            //JSONObject nameObject=userArray.getJSONObject(4);
                                            String retweetedUser_name = user.getString("name");
                                            Log.d("user_name", user_name);

                                            //JSONObject picObject=jsonArray.getJSONObject(37);
                                            String retweetedAvatar_hd = user.getString("avatar_hd");
                                            Log.d("avatar_hd", avatar_hd);
                                            User retweetedUser = new User();
                                            retweetedUser.setAvatar_hd(retweetedAvatar_hd);
                                            retweetedUser.setName(retweetedUser_name);
                                            // retweetedUser.setCreated_at(retweetedCreate_at);
                                            retweetedMessage = new Message(retweetedCreate_at, retweetContent, retweetedFrom_url, retweetedUser, reposts_count,
                                                    retweetedComments_count, retweetedAttitudes_count, null);
                                            retweetedMessage.setPic_urls(retweetedPic_urls);
                                        }
                                        User user1 = new User();
                                        user1.setName(user_name);
                                        user1.setAvatar_hd(avatar_hd);
                                        Message message = new Message(create_at, content, from_url, user1, reposts_count,
                                                comments_count, attitudes_count, retweetedMessage);
                                        message.setPic_urls(pic_urls);
                                        lists.add(message);
                                    }
                                    handler.sendEmptyMessage(1);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });

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
