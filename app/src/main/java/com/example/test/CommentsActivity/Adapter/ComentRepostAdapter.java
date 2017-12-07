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
import com.example.test.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by cyc20 on 2017/11/29.
 */

public class ComentRepostAdapter extends RecyclerView.Adapter<ComentRepostAdapter.ViewHolder> {

    private ArrayList<String> messageArrayList=new ArrayList();
    private Context context;


    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView name_tv;
        CircleImageView pic_iv;
        TextView time_tv;
        TextView from_tv;
        TextView content_tv;
        TextView reposts_count_btn;
        TextView attitudes_count_btn;
        TextView comments_count_btn;
        GridLayout gridLayout;
        LinearLayout retweeted_content_main;
        TextView retweeted_content_tv;
        TextView retweeted_message_tv;
        GridLayout retweeted_gridlayout;

        public ViewHolder(View itemView) {
            super(itemView);
            name_tv=(TextView)itemView.findViewById(R.id.name_tv);
            pic_iv=(CircleImageView) itemView.findViewById(R.id.pic_iv);
            time_tv=(TextView)itemView.findViewById(R.id.time_tv);
            from_tv=(TextView)itemView.findViewById(R.id.from_tv);
            content_tv=(TextView)itemView.findViewById(R.id.content_tv);
            reposts_count_btn=(TextView) itemView.findViewById(R.id.repost_count);
            comments_count_btn=(TextView) itemView.findViewById(R.id.comments_count);
            attitudes_count_btn=(TextView) itemView.findViewById(R.id.attitudes_count);
            gridLayout=(GridLayout)itemView.findViewById(R.id.gridlayout_main);
            retweeted_content_main=(LinearLayout)itemView.findViewById(R.id.retweeted_container);
            retweeted_content_tv=(TextView)itemView.findViewById(R.id.retweeted_content_tv);
            retweeted_message_tv=(TextView)itemView.findViewById(R.id.retweeted_message_tv);
            retweeted_gridlayout=(GridLayout)itemView.findViewById(R.id.retweeted_gridlayout_main);
        }
    }
    public ComentRepostAdapter( Context context,ArrayList<String>messageArrayList){
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
          /*  Message message = (Message) messageArrayList.get(position);
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

            holder.reposts_count_btn.setClickable(true);
            holder.comments_count_btn.setClickable(true);
            holder.attitudes_count_btn.setClickable(true);

            if (message.getPic_urls().size()>0){
                holder.gridLayout.setVisibility(View.VISIBLE);
                updateViewGroup(message.getPic_urls(),holder.gridLayout);
                Log.d("pics","load");
            }
            if (message.getRetweeted_status()!=null){
                holder.retweeted_content_main.setVisibility(View.VISIBLE);
                holder.retweeted_content_tv.setText(message.getRetweeted_status().getText());
                holder.retweeted_message_tv.setText(message.getRetweeted_status().getAttitudes_count());
            }

        */}

    }

    @Override
    public int getItemCount() {
        return messageArrayList.size();
    }

    public void updateViewGroup(ArrayList<String> pic_urls, GridLayout gridlayout) {
        gridlayout.removeAllViews();//清空子视图 防止原有的子视图影响
        GridLayout.LayoutParams layoutParams;
        if (pic_urls.size()==4||pic_urls.size()==2){
            gridlayout.setColumnCount(2);
            layoutParams = new GridLayout.LayoutParams(new ViewGroup.LayoutParams(400,400));
        }else if (pic_urls.size()==1){
            gridlayout.setColumnCount(1);
            layoutParams = new GridLayout.LayoutParams(new ViewGroup.LayoutParams(600,400));
        }
        else {
            gridlayout.setColumnCount(3);
            layoutParams = new GridLayout.LayoutParams(new ViewGroup.LayoutParams(400,400));
        }
        int columnCount = gridlayout.getColumnCount();//得到列数

        //遍历集合 动态添加
        for (int i = 0, size = pic_urls.size(); i < size; i++) {

            GridLayout.Spec rowSpec = GridLayout.spec(i / columnCount);//行数
            GridLayout.Spec columnSpec = GridLayout.spec(i % columnCount, 1.0f);//列数 列宽的比例 weight=1

            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            ImageView imageView=(ImageView)inflater.inflate(R.layout.imageview,null);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

            Log.d("image.height",String.valueOf(imageView.getHeight()));
            //由于宽（即列）已经定义权重比例 宽设置为0 保证均分

            layoutParams.rowSpec = rowSpec;
            layoutParams.columnSpec = columnSpec;
            //layoutParams.height=300;
            //layoutParams.width=300;
            //layoutParams.setMargins(marginSize, marginSize, marginSize, marginSize);
            Glide.with(context)
                    .load(pic_urls.get(i))
                    .asBitmap().into(imageView);
            Log.d("gridlayoutwidth",String.valueOf(gridlayout.getWidth()));
            gridlayout.addView(imageView, layoutParams);
        }
    }
}
