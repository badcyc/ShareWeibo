package com.example.test.BaseModel;

import android.util.Log;


import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import static com.example.test.navigation.accountmanagement.Activity.AddIdActivity.directory;


/**
 * Created by cyc20 on 2017/12/4.
 */

public class SaveMessagesToPhone {

    public static void SaveThisUsersMessagesToPhone(String uid, JSONObject jsonObject) {

        String fileName = uid;
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
            writer.write(jsonObject.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
