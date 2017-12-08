package com.example.test.CommentsActivity.Presenter;

import android.util.Log;

import com.example.test.BaseModel.Message;
import com.example.test.BaseModel.User.User;
import com.example.test.CommentsActivity.Model.CommentData;
import com.example.test.CommentsActivity.Model.CommentUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.test.BaseModel.Utils.access_token;

/**
 * Created by cyc20 on 2017/12/8.
 */

public class GetData {
    public static synchronized ArrayList<CommentData> getCommentRepostData(String url,String id){
        final ArrayList<CommentData> messages=new ArrayList<>();
        OkHttpClient httpClient = new OkHttpClient();

        Log.d("access_send", access_token);
        HttpUrl.Builder httpBuilder = HttpUrl.parse(url).newBuilder();
        httpBuilder.addQueryParameter("access_token", access_token);
        httpBuilder.addQueryParameter("id", id);          //数量20
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
                        JSONArray jsonArray = jsonObject.getJSONArray("comments");

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            String create_at = jsonObject1.getString("created_at");
                            Log.d("created_at", create_at);

                            String id=jsonObject1.getString("id");
                            Log.d("id",id);


                            String floor_number=jsonObject1.getString("floor_number");
                            Log.d("floor_number",floor_number);
                            String content = jsonObject1.getString("text");
                            Log.d("content", content);


                            String from_url = jsonObject1.getString("source");
                            Log.d("from_url", from_url);

                            JSONObject user = jsonObject1.getJSONObject("user");

                            String user_id=user.getString("id");
                            String user_name = user.getString("name");
                            Log.d("user_name", user_name);


                            String avatar_hd = user.getString("avatar_hd");
                            Log.d("avatar_hd", avatar_hd);
                            User user1 = new User();
                            user1.setName(user_name);
                            user1.setAvatar_hd(avatar_hd);
                            CommentUser user2=new CommentUser(user_id,user_name,avatar_hd);
                            CommentData data=new CommentData(create_at,floor_number,content,from_url,user2);
                            messages.add(data);
                        }
                        // messageAdapter.notifyDataSetChanged();
                       // handler.sendEmptyMessage(1);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        return messages;
    }
}

