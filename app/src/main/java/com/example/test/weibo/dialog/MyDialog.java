package com.example.test.weibo.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.test.weibo.dialog.bean.GroupMessage;
import com.example.test.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by cyc20 on 2017/12/6.
 */

public class MyDialog extends AlertDialog implements View.OnClickListener{
    Context context;

    @BindView(R.id.radio_button_group)
    RadioGroup radioGroup;
    @BindView(R.id.radio_button_single)
    RadioButton radioButton;
    @BindView(R.id.group_manager)
    Button groupManagerButton;
    @BindView(R.id.dialog_cancel)
    Button cancelButton;
    ArrayList<GroupMessage> messages=new ArrayList<>();
    protected MyDialog(Context context) {
        super(context);
        GroupMessage message=new GroupMessage("全部微博",null);
        this.messages.add(message);
        message=new GroupMessage("我的微博",null);
        this.messages.add(message);
        message=new GroupMessage("朋友圈",null);
        this.messages.add(message);
    }
    protected MyDialog(Context context, ArrayList<GroupMessage>messages){
        this(context);
        this.messages=messages;
    }
    protected MyDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    protected MyDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_main);
        ButterKnife.bind(this);
        initData();
    }
    public void initData(){
        cancelButton.setOnClickListener(this);
        groupManagerButton.setOnClickListener(this);
        radioGroup.removeAllViews();
        for (int i=0;i<messages.size();i++){
            RadioGroup.LayoutParams layoutParams=new RadioGroup.LayoutParams(RadioGroup.LayoutParams.MATCH_PARENT,
                    RadioGroup.LayoutParams.WRAP_CONTENT);
            RadioButton radioButton=new RadioButton(context);
            radioButton.setText(messages.get(i).getGroupName());
            radioGroup.addView(radioButton,layoutParams);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.group_manager:

                break;
            case R.id.dialog_cancel:
                dismiss();
                break;

        }
    }
}
