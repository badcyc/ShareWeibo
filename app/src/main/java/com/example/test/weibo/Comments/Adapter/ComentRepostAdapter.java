package com.example.test.weibo.Comments.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.test.BaseModel.Utils;
import com.example.test.weibo.Comments.Bean.CommentData;
import com.example.test.R;
import com.example.test.weibo.Comments.View.CommentsMainActivity;
import com.example.test.weibo.Comments.View.PostCommentActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by cyc20 on 2017/11/29.
 */

public class ComentRepostAdapter extends RecyclerView.Adapter<ComentRepostAdapter.ViewHolder> {

    private ArrayList<CommentData> commentDataArrayList;
    private Context context;


    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.comments_repost_name_tv)
        TextView comment_name_tv;
        @BindView(R.id.comment_pic_iv)
        CircleImageView comment_pic_iv;
        @BindView(R.id.comment_time_tv)
        TextView comment_time_tv;
        @BindView(R.id.comment_from_tv)
        TextView comment_from_tv;
        @BindView(R.id.comment_content_tv)
        TextView comment_content_tv;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

    public ComentRepostAdapter(Context context, ArrayList<CommentData> dataArrayList) {
        this.commentDataArrayList = dataArrayList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_repost_main, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        if (commentDataArrayList != null) {
          /*  Message message = (Message) messageArrayList.get(position);*/
            final CommentData message = commentDataArrayList.get(position);
            Log.d("contentText_adapter:", message.getContent());
            holder.comment_content_tv.setText(message.getContent());
            String source = Utils.getSource(message.getSource_url());
            if (source != null) {
                holder.comment_from_tv.setText(source);
                Log.d("source", source);
            }
            holder.comment_time_tv.setText(Utils.parseTime(message.getCreated_at()));
            Glide.with(context)
                    .load(message.getUser().getAvatar_hd())
                    .asBitmap()
                    .into(new SimpleTarget<Bitmap>(50, 50) {
                        @Override
                        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                            holder.comment_pic_iv.setImageBitmap(resource);
                        }
                    });
            // holder.pic_iv.setImageURI(Uri.parse(message.getPic_urls()));
            holder.comment_name_tv.setText(message.getUser().getName());


        }

    }

    @Override
    public int getItemCount() {
        return commentDataArrayList.size();
    }

}
