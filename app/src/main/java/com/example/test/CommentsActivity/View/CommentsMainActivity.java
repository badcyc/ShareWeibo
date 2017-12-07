package com.example.test.CommentsActivity.View;

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
import android.support.v7.widget.Toolbar;
import android.view.WindowManager;

import com.example.test.BaseModel.Message;
import com.example.test.CommentsActivity.Adapter.CommentsTabPagerAdapter;
import com.example.test.R;

import java.util.Arrays;

/**
 * Created by cyc20 on 2017/12/8.
 */

public class CommentsMainActivity extends AppCompatActivity implements CommentsTabPagerAdapter.TabPagerListener{

    Toolbar toolbar;
    CollapsingToolbarLayout collapsingToolbar;
    TabLayout tabLayout;
    AppBarLayout appbar;
    ViewPager viewpager;

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

        Message message=(Message)getIntent().getSerializableExtra("message");
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbar);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        appbar = (AppBarLayout) findViewById(R.id.appbar);
        viewpager = (ViewPager) findViewById(R.id.viewpager);

        setTitle("返回");
        collapsingToolbar.setTitle("返回");
        collapsingToolbar.setExpandedTitleColor(Color.parseColor("#00ffffff"));//设置还没收缩时状态下字体颜色
        collapsingToolbar.setCollapsedTitleTextColor(Color.WHITE);//设置收缩后Toolbar上字体的
        adapter = new CommentsTabPagerAdapter(getSupportFragmentManager()
                , 3, Arrays.asList("美食", "电影", "玩乐", "评价"), this);
        adapter.setListener(this);
        viewpager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewpager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
    }

    @Override
    public Fragment getFragment(int position) {
        return ForwardedFragment.newInstance(position);
    }
}
