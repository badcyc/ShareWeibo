package com.example.test.View.MyView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.test.BaseModel.GroupMessage.GroupMessage;
import com.example.test.R;

import java.util.ArrayList;

/**
 * Created by cyc20 on 2017/12/6.
 */

public class MyDialog extends AlertDialog implements View.OnClickListener{
    Context context;
    RadioGroup radioGroup;
    RadioButton radioButton;
    Button groupManagerButton;
    Button cancelButton;
    ArrayList<GroupMessage> messages;
    protected MyDialog(Context context) {
        super(context);
    }
    protected MyDialog(Context context, ArrayList<GroupMessage>messages){
        super(context);
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
        initView();
        initData();
    }
    public void initView(){
        radioGroup=(RadioGroup)findViewById(R.id.radio_button_group);
        radioButton=(RadioButton)findViewById(R.id.radio_button_single);
        groupManagerButton=(Button)findViewById(R.id.group_manager);
        cancelButton=(Button)findViewById(R.id.dialog_cancel);
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
            radioGroup.addView(radioButton);
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
