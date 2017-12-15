package com.example.test.weibo.Comments.View;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.test.BaseModel.Message;
import com.example.test.BaseModel.Utils;
import com.example.test.weibo.Comments.Adapter.CommentsTabPagerAdapter;
import com.example.test.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.test.weibo.BaseAdapter.MessageAdapter.updateViewGroup;


/**
 * Created by cyc20 on 2017/12/8.
 */

public class CommentsMainActivity extends AppCompatActivity implements CommentsTabPagerAdapter.TabPagerListener {


    Message message;


    @BindView(R.id.comment_pic_iv)
    CircleImageView commentPicIv;
    @BindView(R.id.comment_name_tv)
    TextView commentNameTv;
    @BindView(R.id.comment_time_tv)
    TextView commentTimeTv;
    @BindView(R.id.comment_from_tv)
    TextView commentFromTv;
    @BindView(R.id.comment_content_tv)
    TextView commentContentTv;
    @BindView(R.id.comment_gridlayout_main)
    GridLayout commentGridlayoutMain;
    @BindView(R.id.comment_retweeted_content_tv)
    TextView commentRetweetedContentTv;
    @BindView(R.id.comment_retweeted_message_tv)
    TextView commentRetweetedMessageTv;
    @BindView(R.id.comment_retweeted_gridlayout_main)
    GridLayout commentRetweetedGridlayoutMain;
    @BindView(R.id.comment_retweeted_container)
    LinearLayout commentRetweetedContainer;
    @BindView(R.id.comment_toolbar)
    Toolbar commentToolbar;
    @BindView(R.id.comment_collapsingToolbar)
    CollapsingToolbarLayout commentCollapsingToolbar;
    @BindView(R.id.comment_tabLayout)
    TabLayout commentTabLayout;
    @BindView(R.id.comment_appbar)
    AppBarLayout commentAppbar;
    @BindView(R.id.comment_viewpager)
    ViewPager commentViewpager;
    @BindView(R.id.comment_post_comment_tv)
    TextView commentPostCommentTv;
    @BindView(R.id.comment_like_image_view)
    ImageView commentLikeImageView;
    @BindView(R.id.comments_reposts_image_view)
    ImageView commentRepostsImageView;

    private CommentsTabPagerAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comment_activity_main);
        ButterKnife.bind(this);
        message = (Message) getIntent().getParcelableExtra("message");
        setTitle("返回");
        commentCollapsingToolbar.setTitle("返回");
        commentCollapsingToolbar.setExpandedTitleColor(Color.parseColor("#00ffffff"));//设置还没收缩时状态下字体颜色
        commentCollapsingToolbar.setCollapsedTitleTextColor(Color.WHITE);//设置收缩后Toolbar上字体的
        initView();
        adapter = new CommentsTabPagerAdapter(getSupportFragmentManager()
                , 3, message, this);
        adapter.setListener(this);
        commentViewpager.setAdapter(adapter);
        commentTabLayout.setupWithViewPager(commentViewpager);
        commentTabLayout.setTabMode(TabLayout.MODE_FIXED);
        if (getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void initView() {
        if (message != null) {
            commentNameTv.setText(message.getUser().getName());
            Glide.with(CommentsMainActivity.this)
                    .load(message.getUser().getAvatar_hd())
                    .asBitmap()
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                            commentPicIv.setImageBitmap(resource);
                        }
                    });
            commentTimeTv.setText(Utils.parseTime(message.getCreated_at()));
          //  commentFromTv.setText(Utils.getSource(message.getSource_url()));
            commentContentTv.setText(message.getText());
            if (message.getPic_urls().size() > 0) {
                commentGridlayoutMain.setVisibility(View.VISIBLE);
                updateViewGroup(message.getPic_urls(), commentGridlayoutMain, CommentsMainActivity.this);
                Log.d("pics", "load");
            }
            if (message.getRetweeted_status() != null) {
                commentRetweetedGridlayoutMain.setVisibility(View.VISIBLE);
                commentRetweetedContentTv.setText(message.getRetweeted_status().getText());
                //               comment_retweeted_message_tv.setText(message.getRetweeted_status().getAttitudes_count());
            }

            commentPostCommentTv.setClickable(true);
            commentPostCommentTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(CommentsMainActivity.this, PostCommentActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putParcelable("message",message);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
            commentLikeImageView.setClickable(true);
            commentRepostsImageView.setClickable(true);

        }
    }

    @Override
    public Fragment getFragment(int position, String id) {
        return ForwardedFragment.newInstance(position, id);
    }
}
