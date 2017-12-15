package com.example.test.weibo.emoji;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cyc20 on 2017/12/15.
 */

public class EmojiPagerAdapter extends FragmentPagerAdapter {

    private int PAGE_COUNT;
    private Context context;
    private EmojiTabPagerListener listener;
    private List<String> list=new ArrayList<>();
    public interface EmojiTabPagerListener{
        Fragment getFragment(int position);
    }
    public void setListener(EmojiTabPagerListener listener){
        this.listener=listener;
    }
    public EmojiPagerAdapter(FragmentManager manager, Context context,int count){
        super(manager);
        PAGE_COUNT=count;
        this.context=context;
    }

    @Override
    public Fragment getItem(int position) {
        return listener.getFragment(position);

    }

    @Override
    public CharSequence getPageTitle(int position) {
        for (int i=0;i<PAGE_COUNT;i++)
        list.add("biao");

        return list.get(position);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }
}
