package com.example.test.weibo.Utils;

import android.os.Handler;
import android.util.Log;

import com.example.test.BaseModel.GetMessagesFromPhone;
import com.example.test.BaseModel.Message;
import com.example.test.BaseModel.SaveMessagesToPhone;
import com.example.test.BaseModel.User.SavedUser;
import com.example.test.BaseModel.User.User;
import com.example.test.BaseModel.Utils;

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

import static com.example.test.BaseModel.Utils.access_token;

/**
 * Created by cyc20 on 2017/12/1.
 */

public class GetLists {
    public static ArrayList<Message> getList(final Handler handler, String url) {     //获取当前用户和关注用户最新微博
        final ArrayList<Message> messages = new ArrayList<>();
        OkHttpClient httpClient = new OkHttpClient();

        Log.d("access_send", access_token);
        HttpUrl.Builder httpBuilder = HttpUrl.parse(url).newBuilder();
        httpBuilder.addQueryParameter("access_token", access_token);
        httpBuilder.addQueryParameter("count", "20");          //数量20
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

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            String create_at = jsonObject1.getString("created_at");
                            Log.d("created_at", create_at);

                            String id = jsonObject1.getString("id");
                            Log.d("id", id);
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

                            Message retweetedMessage = null;
                            if (!jsonObject1.isNull("retweeted_status")) {
                                JSONObject retweetedObject = jsonObject1.getJSONObject("retweeted_status");
                                retweetedMessage = null;
                                if (retweetedObject != null) {
                                    String retweetedFrom_url = jsonObject1.getString("source");
                                    String retweetedCreate_at = jsonObject1.getString("created_at");
                                    String retweetedId = jsonObject1.getString("id");
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
                                    retweetedMessage = new Message(retweetedCreate_at, retweetedId, retweetContent, retweetedFrom_url, retweetedUser, reposts_count,
                                            retweetedComments_count, retweetedAttitudes_count, null);
                                    retweetedMessage.setPic_urls(retweetedPic_urls);
                                }
                            }
                            User user1 = new User();
                            user1.setName(user_name);
                            user1.setAvatar_hd(avatar_hd);
                            Message message = new Message(create_at, content, from_url, id, user1, reposts_count,
                                    comments_count, attitudes_count, retweetedMessage);
                            message.setPic_urls(pic_urls);
                            messages.add(message);
                        }
                        // messageAdapter.notifyDataSetChanged();
                        handler.sendEmptyMessage(1);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        return messages;
    }

    public static String getUid(String access_token) {       //用得到的access_token获得当前用户的uid
        String uid = null;
        try {
            OkHttpClient httpClient = new OkHttpClient();
            HttpUrl.Builder httpBuilder = HttpUrl.parse(Utils.getUidUrl).newBuilder();
            httpBuilder.addQueryParameter("access_token", access_token);
            Request request = new Request.Builder()
                    .url(httpBuilder.build())
                    .get()
                    .build();
            Response response = httpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                String responsedata = response.body().string();
                Log.d("responsedata", responsedata);
                try {
                    JSONObject jsonObject = new JSONObject(responsedata);
                    uid = jsonObject.getString("uid");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return uid;
    }

    public static SavedUser getUserMessages(String uid, String access_token) {
        SavedUser user = null;
        if (GetMessagesFromPhone.getThisUserFormPhone(uid) != null) {          //检查本地是否有内容
            user = GetMessagesFromPhone.getThisUserFormPhone(uid);
            Log.d("loadfromphone", "true");
        } else {
            try {
                OkHttpClient httpClient = new OkHttpClient();
                HttpUrl.Builder httpBuilder = HttpUrl.parse(Utils.getUserMessageUrl).newBuilder();
                httpBuilder.addQueryParameter("access_token", access_token);
                httpBuilder.addQueryParameter("uid", uid);
                Request request = new Request.Builder()
                        .url(httpBuilder.build())
                        .get()
                        .build();
                Response response = httpClient.newCall(request).execute();
                if (response.isSuccessful()) {
                    String responsedata = response.body().string();
                    Log.d("responsedata", responsedata);
                    try {
                        JSONObject jsonObject = new JSONObject(responsedata);
                        int mClass = jsonObject.getInt("class");
                        String screen_name = jsonObject.getString("screen_name");
                        int province = jsonObject.getInt("province");
                        int city = jsonObject.getInt("city");
                        String location = jsonObject.getString("location");
                        String description = jsonObject.getString("description");
                        String profile_image_url = jsonObject.getString("profile_image_url");
                        String cover_image_phone = jsonObject.getString("cover_image_phone");
                        int followers_count = jsonObject.getInt("followers_count");
                        int friends_count = jsonObject.getInt("friends_count");
                        int statuses_count = jsonObject.getInt("statuses_count");
                        int favourites_count = jsonObject.getInt("favourites_count");
                        String created_at = jsonObject.getString("created_at");
                        user = new SavedUser(uid, profile_image_url, screen_name, mClass, province, city, location,
                                description, cover_image_phone, followers_count, friends_count, statuses_count, favourites_count, created_at);


                        SaveMessagesToPhone.SaveThisUsersMessagesToPhone(uid, jsonObject);//保存json信息到内存里

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return user;
    }
}
