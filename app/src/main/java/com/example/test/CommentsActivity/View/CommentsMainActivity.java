package com.example.test.CommentsActivity.View;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.test.BaseModel.Message;
import com.example.test.BaseModel.Utils;
import com.example.test.CommentsActivity.Adapter.CommentsTabPagerAdapter;
import com.example.test.R;

import java.util.Arrays;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.test.WeiboMessages.AllWeiboMessages.Adapter.MessageAdapter.updateViewGroup;

/**
 * Created by cyc20 on 2017/12/8.
 */

public class CommentsMainActivity extends AppCompatActivity implements CommentsTabPagerAdapter.TabPagerListener{

    Toolbar toolbar;
    CollapsingToolbarLayout collapsingToolbar;
    TabLayout tabLayout;
    AppBarLayout appbar;
    ViewPager viewpager;
    Message message;

    TextView comment_name_tv;
    CircleImageView comment_pic_iv;
    TextView comment_time_tv;
    TextView comment_from_tv;
    TextView comment_content_tv;
    GridLayout comment_gridLayout;
    LinearLayout comment_retweeted_content_main;
    TextView comment_retweeted_content_tv;
    TextView comment_retweeted_message_tv;
    GridLayout comment_retweeted_gridlayout;


    private CommentsTabPagerAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comment_activity_main);
        message=(Message)getIntent().getSerializableExtra("message");
        toolbar = (Toolbar) findViewById(R.id.comment_toolbar);
        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.comment_collapsingToolbar);
        tabLayout = (TabLayout) findViewById(R.id.comment_tabLayout);
        appbar = (AppBarLayout) findViewById(R.id.comment_appbar);
        viewpager = (ViewPager) findViewById(R.id.comment_viewpager);

        setTitle("返回");
        collapsingToolbar.setTitle("返回");
        collapsingToolbar.setExpandedTitleColor(Color.parseColor("#00ffffff"));//设置还没收缩时状态下字体颜色
        collapsingToolbar.setCollapsedTitleTextColor(Color.WHITE);//设置收缩后Toolbar上字体的
        initView();
        adapter = new CommentsTabPagerAdapter(getSupportFragmentManager()
                , 3, message, this);
        adapter.setListener(this);
        viewpager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewpager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
    }

    private void initView(){
        comment_name_tv=(TextView)findViewById(R.id.comment_name_tv);
        comment_pic_iv=(CircleImageView) findViewById(R.id.comment_pic_iv);
        comment_time_tv=(TextView)findViewById(R.id.comment_time_tv);
        comment_from_tv=(TextView)findViewById(R.id.comment_from_tv);
        comment_content_tv=(TextView)findViewById(R.id.comment_content_tv);
        comment_gridLayout=(GridLayout)findViewById(R.id.comment_gridlayout_main);
        comment_retweeted_content_main=(LinearLayout)findViewById(R.id.comment_retweeted_container);
        comment_retweeted_content_tv=(TextView)findViewById(R.id.comment_retweeted_content_tv);
        comment_retweeted_message_tv=(TextView)findViewById(R.id.comment_retweeted_message_tv);
        comment_retweeted_gridlayout=(GridLayout)findViewById(R.id.comment_retweeted_gridlayout_main);
        if (message!=null) {
            comment_name_tv.setText(message.getUser().getName());
            Glide.with(CommentsMainActivity.this)
                    .load(message.getUser().getAvatar_hd())
                    .asBitmap()
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                            comment_pic_iv.setImageBitmap(resource);
                        }
                    });
            comment_time_tv.setText(Utils.parseTime(message.getCreated_at()));
            comment_from_tv.setText(Utils.getSource(message.getSource_url()));
            comment_content_tv.setText(message.getText());
           if (message.getPic_urls().size()>0){
                comment_gridLayout.setVisibility(View.VISIBLE);
                updateViewGroup(message.getPic_urls(),comment_gridLayout,CommentsMainActivity.this);
                Log.d("pics","load");
            }
            if (message.getRetweeted_status()!=null){
                comment_retweeted_content_main.setVisibility(View.VISIBLE);
                comment_retweeted_content_tv.setText(message.getRetweeted_status().getText());
 //               comment_retweeted_message_tv.setText(message.getRetweeted_status().getAttitudes_count());
            }


        }
    }
    @Override
    public Fragment getFragment(int position,String id) {
        return ForwardedFragment.newInstance(position,id);
    }
}
