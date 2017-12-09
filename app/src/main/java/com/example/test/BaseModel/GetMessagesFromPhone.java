package com.example.test.BaseModel;

import com.example.test.BaseModel.User.SavedUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by cyc20 on 2017/12/4.
 */

public class GetMessagesFromPhone {
    public static SavedUser getThisUserFormPhone(String uid) {
        SavedUser user = null;
        File file = new File(Utils.directory, uid);
        if (file.exists()) {
            try {
                FileReader r = new FileReader(file);
                BufferedReader br = new BufferedReader(r);

                StringBuffer msg = new StringBuffer();
                String s;
                while ((s = br.readLine()) != null) {
                    msg = msg.append(s); //必须要加\n 否则全部数据变成一行
                }
                String data;
                data = msg.toString();
                try {


                    JSONObject jsonObject = new JSONObject(data);

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
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return user;
    }
}
