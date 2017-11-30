package com.example.test.View.MyView;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by cyc20 on 2017/12/1.
 */

public class PicsView extends AppCompatImageView{
    public PicsView(Context context) {
        super(context);
    }
    public PicsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    public PicsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
