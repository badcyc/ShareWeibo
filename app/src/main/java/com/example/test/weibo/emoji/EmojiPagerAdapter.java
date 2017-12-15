package com.example.test.weibo.emoji;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by cyc20 on 2017/12/15.
 */

public class EmojiPagerAdapter extends FragmentPagerAdapter {


    private Context context;
    private EmojiTabPagerListener listener;
    public interface EmojiTabPagerListener{
        Fragment getFragment(int position);
    }
    public EmojiPagerAdapter(FragmentManager manager, Context context){
        super(manager);
    }

    @Override
    public Fragment getItem(int position) {
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return super.getPageTitle(position);
    }

    @Override
    public int getCount() {
        return 0;
    }
}
