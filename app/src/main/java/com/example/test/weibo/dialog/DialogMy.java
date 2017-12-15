package com.example.test.weibo.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.test.BuildConfig;
import com.example.test.R;

import java.util.List;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by cyc20 on 2017/12/12.
 */

public class DialogMy extends Dialog implements View.OnClickListener {

    public DialogMy(@NonNull Context context) {
        super(context);
    }

    public DialogMy(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected DialogMy(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public static class Builder {
        private Activity context;
        private OnRadioButtonClickListener listener;
        private String buttonName;
        private float scale;
        private int screenWidth;
        @BindView(R.id.radio_button_group)
        RadioGroup radioGroup;
        @BindView(R.id.dialog_cancel)
        Button cancelButton;
        private List<String> group;

        public Builder(Activity context) {
            this.context = context;
            scale = context.getResources().getDisplayMetrics().density;
        }

        public Builder setRadioGroup(List<String> group) {
            this.group = group;
            return this;
        }

        public Builder setOnItemClickListener(OnRadioButtonClickListener listener) {
            this.listener = listener;
            return this;
        }

        public Builder setOnSelectedButton(String name) {
            this.buttonName = name;
            return this;
        }

        public DialogMy create() {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final DialogMy dialogMy = new DialogMy(context);
            dialogMy.setCanceledOnTouchOutside(true);
            dialogMy.setCancelable(true);
            View view = layoutInflater.inflate(R.layout.dialog_main, null);
            dialogMy.setContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            ButterKnife.bind(this, view);
            cancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialogMy.dismiss();
                }
            });
            if (group != null) {
                //    ButterKnife.bind(this, view);
                radioGroup.removeAllViews();
                for (String name : group) {
                    RadioButton radioButton = new RadioButton(context);
                    radioButton.setText(name);
                    if (name.equals(buttonName)) {
                        radioButton.setChecked(true);
                    }
                    radioGroup.addView(radioButton, new ViewGroup.LayoutParams(
                            RadioGroup.LayoutParams.MATCH_PARENT, RadioGroup.LayoutParams.WRAP_CONTENT));

                }
                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        RadioButton button = radioGroup.findViewById(i);
                        listener.onRadioButtonSelected(button.getText().toString());
                        dialogMy.dismiss();
                    }
                });
            }
            dialogMy.setContentView(view);
            Window dialogWindow = dialogMy.getWindow();
            WindowManager.LayoutParams layoutParams = dialogWindow.getAttributes();
            dialogWindow.setGravity(Gravity.CENTER);
            layoutParams.width = layoutParams.width / 2;
            layoutParams.height = layoutParams.height / 2;
            layoutParams.horizontalMargin = 20;
            // layoutParams.alpha = 0.8f;
            dialogWindow.setAttributes(layoutParams);
            return dialogMy;
        }
    }

    public interface OnRadioButtonClickListener {
        void onRadioButtonSelected(String name);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.dialog_cancel:
                dismiss();
                break;

            case R.id.group_manager:

                break;

            default:
                break;
        }
    }
}
