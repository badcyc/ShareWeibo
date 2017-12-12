package com.example.test.View;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;


import com.example.test.View.Adapters.AddIdAdapter;
import com.example.test.BaseModel.GetMessagesFromPhone;
import com.example.test.BaseModel.User.SavedUser;
import com.example.test.R;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by cyc20 on 2017/12/4.
 */

public class AddIdActivity extends AppCompatActivity implements View.OnClickListener {
    public static String directory;


    CircleImageView touxiang;

    ArrayList<SavedUser> savedUsers = new ArrayList<>();

    @BindView(R.id.add_id) Button addid_tv;
    @BindView(R.id.toolbar_addid) Toolbar toolbar;
    @BindView(R.id.addid_recycler_view) RecyclerView recyclerView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setContentView(R.layout.addid_activity_main);
        directory = getExternalFilesDir(null).getAbsolutePath() + "/jsoncache/";

        setSupportActionBar(toolbar);
        savedUsers = getSavedUsers();

        addid_tv.setClickable(true);
        addid_tv.setOnClickListener(this);
        AddIdAdapter addIdAdapter = new AddIdAdapter(savedUsers, AddIdActivity.this);
        recyclerView.setAdapter(addIdAdapter);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayout.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
    }

    public ArrayList<SavedUser> getSavedUsers() {
        ArrayList<SavedUser> savedUsers = new ArrayList<>();
        File file = new File(directory);
        if (file.exists()) {
            File[] listFile = file.listFiles();
            if (listFile.length > 0) {
                for (File eachFile : listFile) {
                    SavedUser user = GetMessagesFromPhone.getThisUserFormPhone(eachFile.getName());
                    savedUsers.add(user);
                }
            }
        }
        return savedUsers;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_id:
                addid();
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 101) {
            if (permissions[0].equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                addid();
            } else {

            }
        }
    }

    public void addid() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 101);
                Log.d("requestPermissions", "start");
                return;
            }
            Log.d("requestPermissions", "end");
        }
        Log.d("WeiboAuthActivity", "start");
        Intent intent = new Intent(AddIdActivity.this, WeiboOAuthLoginActivity.class);
        startActivity(intent);

    }
}
