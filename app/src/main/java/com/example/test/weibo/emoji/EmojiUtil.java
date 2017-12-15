package com.example.test.weibo.emoji;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;
import okhttp3.Response;

import static com.example.test.navigation.accountmanagement.Activity.AddIdActivity.directory;

/**
 * Created by cyc20 on 2017/12/15.
 */

public class EmojiUtil {
    public List<EmojiIcon> parseEmojiData(String responseData) {
        List<EmojiIcon> emojiIcons = new ArrayList<>();
        String data = responseData;
        try {
            JSONArray array = new JSONArray(data);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                String phrase = object.getString("phrase");
                String type = object.getString("type");
                String url = object.getString("url");
                boolean hot = object.getBoolean("hot");
                boolean common = object.getBoolean("common");
                String category = object.getString("category");
                String icon = object.getString("icon");
                String value = object.getString("value");
                String picid = object.getString("picid");
                EmojiIcon emojiIcon = new EmojiIcon(phrase, type, url, hot, common, picid, icon, category, value);
                emojiIcons.add(emojiIcon);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return emojiIcons;
    }

    public void saveResponseDataToPhone(String name, String responseData) {

        String fileName = name;
        File myPath = new File(directory, fileName);
        if (myPath.exists()) {
            myPath.mkdir();
        } else {
            myPath.getParentFile().mkdirs();
            try {
                myPath.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                Log.d("createFile", "ERROR");
            }
        }
        try {
            OutputStream out = new FileOutputStream(myPath);
            Writer writer = new OutputStreamWriter(out);
            writer.write(responseData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String loadResponseDataFromPhone(String name){
        File myPath = new File(directory, name);
        StringBuilder dataBuffer=new StringBuilder();
        String data=null;
        try {
            InputStream inputStream=new FileInputStream(myPath);
            byte[] tempbytes=new byte[1024];
            while ((inputStream.read(tempbytes))!=-1){
                dataBuffer.append(tempbytes);
            }
           data=dataBuffer.toString();
        }catch (IOException e){
            e.printStackTrace();
        }
        return data;
    }
}
