package com.example.test.weibo.Comments.View;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.test.BaseModel.Message;
import com.example.test.R;
import com.example.test.weibo.Comments.Bean.CommentData;
import com.example.test.weibo.emoji.EmojiFaceAdapter;
import com.example.test.weibo.emoji.EmojiPagerAdapter;
import com.example.test.weibo.emoji.EmojiPagerFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by cyc20 on 2017/12/13.
 */

public class PostCommentActivity extends AppCompatActivity implements EmojiPagerAdapter.EmojiTabPagerListener {
    Message message;
    private EmojiPagerAdapter emojiPagerAdapter;
    private boolean emojiKeyboardState=false;
    private int keyheight;
    private int screenheight;
    private InputMethodManager imm;
    @BindView(R.id.post_comment_toolbar)
    Toolbar postCommentToolbar;
    @BindView(R.id.post_comment_Avatar)
    CircleImageView postCommentAvatar;
    @BindView(R.id.post_comment_name)
    TextView postCommentName;
    @BindView(R.id.post_comment_edit_text)
    EditText postCommentEditText;
    @BindView(R.id.post_comment_camera)
    ImageView postCommentCamera;
    @BindView(R.id.post_comment_call)
    ImageView postCommentCall;
    @BindView(R.id.post_comment_search)
    ImageView postCommentSearch;
    @BindView(R.id.post_comment_emotion)
    ImageView postCommentEmotion;
    @BindView(R.id.post_comment_send)
    ImageView postCommentSend;
    @BindView(R.id.emoji_linear_layout)
    LinearLayout emojiLinearLayout;
    @BindView(R.id.emoji_tab_layout)
    TabLayout emojiTabLayout;
    @BindView(R.id.emoji_view_pager)
    ViewPager emojiViewPager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_comment_main);
        ButterKnife.bind(this);
        setSupportActionBar(postCommentToolbar);
        imm = (InputMethodManager)getSystemService(this.INPUT_METHOD_SERVICE);
        screenheight=this.getWindowManager().getDefaultDisplay().getHeight();
        keyheight=screenheight/3;
        initData();
        initView();
    }

    public void initData(){
        message=getIntent().getParcelableExtra("message");
    }
    public void initView(){
        Glide.with(this)
                .load(message.getUser().getAvatar_hd())
                .asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                postCommentAvatar.setImageBitmap(resource);
            }
        });
        postCommentName.setText(message.getUser().getName());

    }
    @OnClick({R.id.post_comment_camera, R.id.post_comment_call, R.id.post_comment_search,
            R.id.post_comment_emotion, R.id.post_comment_send})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.post_comment_camera:
                break;
            case R.id.post_comment_call:
                break;
            case R.id.post_comment_search:
                break;
            case R.id.post_comment_emotion:
                if (!emojiKeyboardState) {
                    if (imm.isActive()){
                        imm.hideSoftInputFromWindow(this.getWindow().getDecorView().getWindowToken(),0);
                    }
                    emojiLinearLayout.setVisibility(View.VISIBLE);
                    emojiPagerAdapter = new EmojiPagerAdapter(getSupportFragmentManager(), this, 4);
                    emojiPagerAdapter.setListener(this);
                    emojiViewPager.setAdapter(emojiPagerAdapter);
                    emojiTabLayout.setupWithViewPager(emojiViewPager);
                    emojiTabLayout.setTabMode(TabLayout.MODE_FIXED);
                }else{
                    emojiLinearLayout.setVisibility(View.INVISIBLE);
                    emojiLinearLayout.setVisibility(View.GONE);
                }
                break;
            case R.id.post_comment_send:
                break;
        }
    }

    /*@Override
    public void onLayoutChange(View view, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom)
    {
        if (oldBottom!=0&&bottom!=0&&(oldBottom-bottom>keyheight)){
            keyboardState=true;
        }
    }*/

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public Fragment getFragment(int position) {
        return EmojiPagerFragment.newInstance(position);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_post_comment_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_timing_send:
                break;
            case R.id.action_timing_cancel:
                break;
            case R.id.action_weibo_from:
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
