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
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.test.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by cyc20 on 2017/12/12.
 */

public class DialogMy extends Dialog {

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
        private float scale;
        private int screenWidth;
        @BindView(R.id.radio_button_group)
        RadioGroup radioGroup;

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

        public DialogMy create() {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final DialogMy dialogMy = new DialogMy(context);
            dialogMy.setCanceledOnTouchOutside(true);
            dialogMy.setCancelable(true);
            View view = layoutInflater.inflate(R.layout.dialog_main, null);
            dialogMy.setContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            if (group != null) {
                ButterKnife.bind(this, view);
                radioGroup.removeAllViews();
                for (String name : group) {
                    RadioButton radioButton = new RadioButton(context);
                    radioButton.setText(name);
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
            dialogWindow.setGravity(Gravity.RIGHT | Gravity.TOP);
            layoutParams.width = layoutParams.width * 2 / 3;
            layoutParams.alpha = 0.8f;
            dialogWindow.setAttributes(layoutParams);
            return dialogMy;
        }
    }

    public interface OnRadioButtonClickListener {
        void onRadioButtonSelected(String name);
    }
}
