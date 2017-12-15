package com.example.test.weibo.Comments.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.test.BaseModel.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cyc20 on 2017/12/6.
 */

public class CommentsTabPagerAdapter extends FragmentPagerAdapter {

    private int PAGE_COUNT;
    private Message message;
    private Context context;
    private TabPagerListener listener;
    List<String> list = new ArrayList<>();

    public interface TabPagerListener {
        Fragment getFragment(int position, String id);
    }

    public void setListener(TabPagerListener listener) {
        this.listener = listener;
    }

    public CommentsTabPagerAdapter(FragmentManager fm, int count, Message message, Context context) {
        super(fm);
        this.PAGE_COUNT = count;
        this.message = message;
        this.context = context;
        list = getList(message);
    }

    @Override
    public Fragment getItem(int position) {
        return listener.getFragment(position, message.getId());
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position);
    }

    public List<String> getList(Message message) {
        List<String> list = new ArrayList<>();
        list.add("转发(" + String.valueOf(message.getReposts_count()) + ")");
        list.add("评论(" + String.valueOf(message.getComments_count()) + ")");
        list.add("赞(" + String.valueOf(message.getAttitudes_count()) + ")");
        return list;
    }
}

