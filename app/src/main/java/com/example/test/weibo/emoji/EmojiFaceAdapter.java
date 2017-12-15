package com.example.test.weibo.emoji;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.test.R;

/**
 * Created by cyc20 on 2017/12/15.
 */

public class EmojiFaceAdapter extends BaseAdapter {

    private Context context;
    public static int[] imageIds=new int[]{
            R.drawable.huanglianwx_org,R.drawable.ad_new0902_org,
            R.drawable.aliwanew_org,R.drawable.bba_org
    };
    public EmojiFaceAdapter(Context context){
        this.context=context;
    }

    @Override
    public int getCount() {
        return imageIds.length;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return imageIds[i];
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imageView;
        if (view==null){
            imageView=new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(85,85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }
        else {
            imageView=(ImageView)view;
        }
        imageView.setImageResource(imageIds[i]);
        imageView.setTag("["+i+"]");
        return imageView;
    }
}
