package com.example.test.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;


import com.example.test.Adapters.AddIdAdapter;
import com.example.test.Model.GetMessagesFromPhone;
import com.example.test.Model.User.ThisUser;
import com.example.test.Model.Utils;
import com.example.test.R;
import com.example.test.WeiboOAuthLoginActivity;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by cyc20 on 2017/12/4.
 */

public class AddIdActivity extends AppCompatActivity implements View.OnClickListener{

    CircleImageView touxiang;
    Toolbar toolbar;
    ArrayList<ThisUser> thisUsers=new ArrayList<>();
    RecyclerView recyclerView;
    TextView addid_tv;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        thisUsers=getThisUsers();
        setContentView(R.layout.addid_activity_main);
        toolbar=(Toolbar)findViewById(R.id.toolbar_addid);
        setSupportActionBar(toolbar);

        recyclerView=(RecyclerView)findViewById(R.id.addid_recycler_view);
        addid_tv=(TextView)findViewById(R.id.add_id);

        addid_tv.setClickable(true);

        AddIdAdapter addIdAdapter=new AddIdAdapter(thisUsers,AddIdActivity.this);
        recyclerView.setAdapter(addIdAdapter);
    }
    public ArrayList<ThisUser> getThisUsers(){
        ArrayList<ThisUser> thisUsers=new ArrayList<>();
        File file=new File(Utils.directory);
        File[] listFile=file.listFiles();
        if (listFile.length>0) {
            for (File eachFile : listFile) {
                ThisUser user=GetMessagesFromPhone.getThisUserFormPhone(eachFile.getName());
                thisUsers.add(user);
            }
        }
        return thisUsers;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.add_id:
                Intent intent=new Intent(AddIdActivity.this, WeiboOAuthLoginActivity.class);
                startActivity(intent);
                break;
        }
    }
}
