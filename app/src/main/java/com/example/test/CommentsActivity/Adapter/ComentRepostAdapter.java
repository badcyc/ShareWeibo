package com.example.test.CommentsActivity.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.GridLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.test.BaseModel.Message;
import com.example.test.BaseModel.Utils;
import com.example.test.CommentsActivity.Model.CommentData;
import com.example.test.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by cyc20 on 2017/11/29.
 */

public class ComentRepostAdapter extends RecyclerView.Adapter<ComentRepostAdapter.ViewHolder> {

    private ArrayList<CommentData> commentDataArrayList;
    private Context context;


    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView comment_name_tv;
        CircleImageView comment_pic_iv;
        TextView comment_time_tv;
        TextView comment_from_tv;
        TextView comment_content_tv;

        public ViewHolder(View itemView) {
            super(itemView);
            comment_name_tv = (TextView) itemView.findViewById(R.id.comments_repost_name_tv);
            comment_pic_iv = (CircleImageView) itemView.findViewById(R.id.comments_repost_image);
            comment_time_tv = (TextView) itemView.findViewById(R.id.comments_repost_time_tv);
            comment_from_tv = (TextView) itemView.findViewById(R.id.comments_repost_from_tv);
            comment_content_tv = (TextView) itemView.findViewById(R.id.comments_repost_content_tv);

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
            CommentData message = commentDataArrayList.get(position);
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
