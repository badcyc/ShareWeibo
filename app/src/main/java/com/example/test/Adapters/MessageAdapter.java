package com.example.test.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.test.Model.Message;
import com.example.test.Model.Utils;
import com.example.test.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by cyc20 on 2017/11/29.
 */

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    private ArrayList messageArrayList=new ArrayList();
    private Context context;
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView name_tv;
        CircleImageView pic_iv;
        TextView time_tv;
        TextView from_tv;
        TextView content_tv;
        Button reposts_count_btn;
        Button attitudes_count_btn;
        Button comments_count_btn;
        public ViewHolder(View itemView) {
            super(itemView);
            name_tv=(TextView)itemView.findViewById(R.id.name_tv);
            pic_iv=(CircleImageView) itemView.findViewById(R.id.pic_iv);
            time_tv=(TextView)itemView.findViewById(R.id.time_tv);
            from_tv=(TextView)itemView.findViewById(R.id.from_tv);
            content_tv=(TextView)itemView.findViewById(R.id.content_tv);
            reposts_count_btn=(Button)itemView.findViewById(R.id.reposts_count);
            comments_count_btn=(Button)itemView.findViewById(R.id.comments_count);
            attitudes_count_btn=(Button)itemView.findViewById(R.id.attitudes_count);
        }
    }
    public MessageAdapter(ArrayList<Message>messageArrayList, Context context){
        this.messageArrayList=messageArrayList;
        this.context=context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.messages_main,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if (messageArrayList!=null) {
            Message message = (Message) messageArrayList.get(position);
            Log.d("contentText_adapter:",message.getText());
            holder.content_tv.setText(message.getText());
            String source= Utils.getSource(message.getSource_url());
            if (source!=null) {
                holder.from_tv.setText(source);
                Log.d("source",source);
            }
            holder.time_tv.setText(Utils.parseTime(message.getCreated_at()));
            Glide.with(context)
                    .load(message.getUser().getAvatar_hd())
                    .asBitmap()
                    .into(new SimpleTarget<Bitmap>(50,50) {
                        @Override
                        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                            holder.pic_iv.setImageBitmap(resource);
                        }
                    });
           // holder.pic_iv.setImageURI(Uri.parse(message.getPic_urls()));
            holder.name_tv.setText(message.getUser().getName());
            holder.attitudes_count_btn.setText(String.valueOf(message.getAttitudes_count()));
            holder.comments_count_btn.setText(String.valueOf(message.getComments_count()));
            holder.reposts_count_btn.setText(String.valueOf(message.getReposts_count()));
        }
    }

    @Override
    public int getItemCount() {
        return messageArrayList.size();
    }
}
