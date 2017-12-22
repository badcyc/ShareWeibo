package com.example.test.base.ui;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.view.View;

import java.lang.reflect.Field;




/**
 * Created by cyc20 on 2017/12/19.
 */

public class AnnotateUtil {


    /**
     *
     * @param currentClass 当前类，一般为Activity或Fragment
     * @param sourceView  待绑定控件的直接或间接父类
     */
    public static void initBindView(Object currentClass, View sourceView){
        Field[] fields=currentClass.getClass().getDeclaredFields();
        if (fields!=null&&fields.length>0){
            for (Field field:fields){
                BindView bindView=field.getAnnotation(BindView.class);
                if (bindView!=null){
                    int viewId=bindView.id();
                    boolean clickLis=bindView.click();
                    try {
                        field.setAccessible(true);
                        if (clickLis){
                            sourceView.findViewById(viewId).setOnClickListener((View.OnClickListener)currentClass);
                        }
                        field.set(currentClass,sourceView.findViewById(viewId));
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     *  必须在setContentView()之后调用
     * @param activity
     */
    public static void initBindView(Activity activity){
        initBindView(activity,activity.getWindow().getDecorView());
    }

    public static void initBindView(View view){
        Context context=view.getContext();
        if (context instanceof Activity){
            initBindView((Activity)context);
        }else {
            throw new RuntimeException("View must into Activity");
        }
    }

    /**
     * 必须在setContentView()之后调用
     * @param fragment
     */
    public static void initBindView(Fragment fragment){
        initBindView(fragment,fragment.getActivity().getWindow().getDecorView());
    }
}
