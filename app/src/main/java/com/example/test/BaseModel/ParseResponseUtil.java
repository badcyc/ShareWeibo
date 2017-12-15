package com.example.test.BaseModel;

import android.os.Handler;
import android.util.Log;

import com.example.test.BaseModel.User.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * Created by cyc20 on 2017/12/13.
 */

public class ParseResponseUtil {
    public static ArrayList<Message> parseWeiboResponse(String responsedata,Handler handler) {
        ArrayList<Message> messages=new ArrayList<>();
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

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return messages;
    }
}
